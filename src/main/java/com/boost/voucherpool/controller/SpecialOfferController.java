package com.boost.voucherpool.controller;

import com.boost.voucherpool.data.dto.SpecialOfferDto;
import com.boost.voucherpool.service.SpecialOfferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

@RestController
public class SpecialOfferController {

    private final static String path = "/offer";

    private final SpecialOfferService specialOfferService;

    public SpecialOfferController(SpecialOfferService specialOfferService) {
        this.specialOfferService = Objects.requireNonNull(specialOfferService);
    }

    @PostMapping(value = path, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody SpecialOfferDto specialOfferDto) {
        specialOfferService.create(specialOfferDto);
    }
}
