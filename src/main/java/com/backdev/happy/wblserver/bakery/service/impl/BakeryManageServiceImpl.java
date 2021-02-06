package com.backdev.happy.wblserver.bakery.service.impl;

import com.backdev.happy.wblserver.bakery.domain.Bakery;
import com.backdev.happy.wblserver.bakery.dto.BakeryDto;
import com.backdev.happy.wblserver.bakery.repository.BakeryRepository;
import com.backdev.happy.wblserver.bakery.service.BakeryManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BakeryManageServiceImpl implements BakeryManageService {

    private final BakeryRepository bakeryRepository;

    @Override
    public Bakery createBakery(BakeryDto.CreateRequest dto) {
        return bakeryRepository.save(dto.toEntity());
    }
}
