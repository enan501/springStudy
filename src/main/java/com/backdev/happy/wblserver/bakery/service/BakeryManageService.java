package com.backdev.happy.wblserver.bakery.service;

import com.backdev.happy.wblserver.bakery.domain.Bakery;
import com.backdev.happy.wblserver.bakery.dto.BakeryDto;
import com.backdev.happy.wblserver.bakery.repository.BakeryRepository;

public interface BakeryManageService {
    Bakery createBakery(final BakeryDto.CreateRequest dto);
}
