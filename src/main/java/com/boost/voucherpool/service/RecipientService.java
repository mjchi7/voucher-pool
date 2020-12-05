package com.boost.voucherpool.service;

import com.boost.voucherpool.dao.RecipientDao;
import com.boost.voucherpool.data.Recipient;
import com.boost.voucherpool.exception.EmailAlreadyExistException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class RecipientService {

    private final RecipientDao recipientDao;

    public RecipientService(RecipientDao recipientDao) {
        this.recipientDao = Objects.requireNonNull(recipientDao);
    }

    public Recipient create(Recipient recipient) {
        Optional<Recipient> existingRecipient = recipientDao.findByEmail(recipient.getEmail());
        if (existingRecipient.isPresent()) {
            throw new EmailAlreadyExistException(recipient.getEmail());
        }
        recipientDao.save(recipient);
        return recipient;
    }
}
