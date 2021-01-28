package com.backdev.happy.wblserver.app.user.service;


import com.backdev.happy.wblserver.app.user.model.UserVO;

public interface UserService {

    UserVO login(UserVO userVO);

    UserVO createUser(UserVO userVO);

    UserVO findUserByUserEmail(String userEmail);

}
