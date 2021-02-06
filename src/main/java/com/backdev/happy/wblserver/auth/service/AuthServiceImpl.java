package com.backdev.happy.wblserver.auth.service;

import com.backdev.happy.wblserver.auth.domain.dto.SigninDTO;
import com.backdev.happy.wblserver.auth.domain.dto.SignupDTO;
import com.backdev.happy.wblserver.auth.domain.dto.TokenDTO;
import com.backdev.happy.wblserver.auth.domain.entity.Role;
import com.backdev.happy.wblserver.auth.domain.entity.UserRole;
import com.backdev.happy.wblserver.auth.domain.repository.RoleRepository;
import com.backdev.happy.wblserver.auth.domain.repository.UserRepository;
import com.backdev.happy.wblserver.auth.domain.entity.Member;
import com.backdev.happy.wblserver.auth.domain.repository.UserRoleRepository;
import com.backdev.happy.wblserver.auth.exception.DuplicatedNameException;
import com.backdev.happy.wblserver.auth.exception.UserNotFoundException;
import com.backdev.happy.wblserver.auth.exception.WrongPasswordException;
import com.backdev.happy.wblserver.global.util.JwtUtil;
import com.backdev.happy.wblserver.global.util.RedisUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final JwtUtil jwtUtil;
    private final RedisUtil redisUtil;

    @Override
    public void checkDuplicated(SignupDTO signupDTO) {
        if(userRepository.existsByName(signupDTO.getName()))
            throw new DuplicatedNameException("name", signupDTO.getName());
        if(userRepository.existsByPhone(signupDTO.getPhone()))
            throw new DuplicatedNameException("phone", signupDTO.getPhone());
    }

    @Override
    public void createUser(SignupDTO signupDTO) {
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseGet(()->roleRepository.save(new Role("ROLE_USER")));
        Member member = signupDTO.toEntity(passwordEncoder);
        userRepository.save(member);
        userRoleRepository.save(new UserRole(member, userRole));
    }

    @Override
    public Member signin(SigninDTO signinDTO){
        Member member = userRepository.findByName(signinDTO.getName()).orElseThrow(UserNotFoundException::new);
        if(!passwordEncoder.matches(signinDTO.getPassword(), member.getPassword()))
            throw new WrongPasswordException();
        return member;
    }

    @Override
    public TokenDTO getToken(Member member) {
        String accessToken = jwtUtil.generateToken(member.getUsername());
        String refreshToken = jwtUtil.generateRefreshToken(member.getUsername());
        redisUtil.setDataExpire(refreshToken, member.getUsername(), JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);
        return new TokenDTO(accessToken, refreshToken);
    }
}