package com.boost.voucherpool.dao;

import com.boost.voucherpool.data.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipientDao extends JpaRepository<Recipient, Long> {

    Optional<Recipient> findByEmail(String email);
}
