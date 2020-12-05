package com.boost.voucherpool.controller;

import com.boost.voucherpool.data.dto.SpecialOfferDto;
import com.boost.voucherpool.service.SpecialOfferService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class SpecialOfferController {

    private final static String path = "/offer";

    private final SpecialOfferService specialOfferService;

    public SpecialOfferController(SpecialOfferService specialOfferService) {
        this.specialOfferService = Objects.requireNonNull(specialOfferService);
    }

    @PostMapping(value = path, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody SpecialOfferDto specialOfferDto) {
        specialOfferService.create(specialOfferDto);
    }
}
