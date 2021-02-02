package com.backdev.happy.wblserver.bakery.controller;

import com.backdev.happy.wblserver.bakery.domain.Bakery;
import com.backdev.happy.wblserver.bakery.dto.BakeryDto;
import com.backdev.happy.wblserver.bakery.repository.BakeryRepository;
import com.backdev.happy.wblserver.bakery.service.BakeryManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bakeries")
@RequiredArgsConstructor
public class BakeryController {

    private final BakeryManageService bakeryManageService;

    @PostMapping
    public ResponseEntity<String> createBakery(@RequestBody BakeryDto.CreateRequest request) {
        final Bakery bakery = bakeryManageService.createBakery(request);
        return new ResponseEntity<>("success to create bakery: "+bakery.toString(), HttpStatus.OK);
    }
}
