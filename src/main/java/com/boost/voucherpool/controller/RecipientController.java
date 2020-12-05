package com.boost.voucherpool.controller;

import com.boost.voucherpool.data.Recipient;
import com.boost.voucherpool.service.RecipientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class RecipientController {

    private static final String path = "/recipient";

    private final RecipientService recipientService;

    public RecipientController(RecipientService recipientService) {
        this.recipientService = Objects.requireNonNull(recipientService);
    }

    @PostMapping(value = path, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Recipient recipient) {
        this.recipientService.create(recipient);
    }
}
