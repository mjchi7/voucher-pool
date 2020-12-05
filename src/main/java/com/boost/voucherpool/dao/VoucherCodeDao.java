package com.boost.voucherpool.dao;

import com.boost.voucherpool.data.VoucherCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoucherCodeDao extends JpaRepository<VoucherCode, Long> {

    Optional<VoucherCode> findByCode(String code);

    Optional<VoucherCode> findByRecipientEmail(String email);

    Optional<VoucherCode> findByCodeAndRecipientEmail(String code, String email);
}
