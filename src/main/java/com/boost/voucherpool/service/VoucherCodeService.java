package com.boost.voucherpool.service;

import com.boost.voucherpool.dao.VoucherCodeDao;
import com.boost.voucherpool.data.Recipient;
import com.boost.voucherpool.data.SpecialOffer;
import com.boost.voucherpool.data.VoucherCode;
import com.boost.voucherpool.data.dto.VoucherCodeUsageDto;
import com.boost.voucherpool.exception.InvalidVoucherCodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class VoucherCodeService {

    private final VoucherCodeDao voucherCodeDao;

    private final Logger logger = LoggerFactory.getLogger(VoucherCodeService.class);

    public VoucherCodeService(VoucherCodeDao voucherCodeDao) {
        Objects.requireNonNull(voucherCodeDao);
        this.voucherCodeDao = voucherCodeDao;
    }

    public void create(Recipient recipient, LocalDate expiryDate,
                       SpecialOffer specialOffer) {
        VoucherCode voucherCode = new VoucherCode();

        voucherCode.setExpiryDate(expiryDate);
        voucherCode.setRecipient(recipient);
        voucherCode.setUsageDate(null);
        voucherCode.setSpecialOffer(specialOffer);
        voucherCode.setCode(this.getNewRandomCode());
        voucherCodeDao.save(voucherCode);
    }

    public VoucherCode use(VoucherCodeUsageDto voucherCodeUsageDto) {
        Optional<VoucherCode> optionalVC = voucherCodeDao
                .findByCodeAndRecipientEmail(voucherCodeUsageDto
                        .getCode(), voucherCodeUsageDto.getEmail());
        VoucherCode vc = optionalVC.get();
        // If used, or expired, throw invalid error
        if (vc.getUsageDate() != null || LocalDate.now().isAfter(vc.getExpiryDate())) {
            throw new InvalidVoucherCodeException(vc.getCode());
        }
        vc.setUsageDate(LocalDate.now());
        voucherCodeDao.save(vc);
        return vc;
    }

    public List<VoucherCode> findByRecipientEmail(String email) {
        logger.info("Email query: " + email);
        List<VoucherCode> vc = voucherCodeDao.findByRecipientEmail(email);
        return vc;
    }

    private String getNewRandomCode() {
        return UUID.randomUUID().toString();
    }
}
