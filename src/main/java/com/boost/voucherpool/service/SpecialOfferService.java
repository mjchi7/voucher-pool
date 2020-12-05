package com.boost.voucherpool.service;

import com.boost.voucherpool.dao.RecipientDao;
import com.boost.voucherpool.dao.SpecialOfferDao;
import com.boost.voucherpool.data.Recipient;
import com.boost.voucherpool.data.SpecialOffer;
import com.boost.voucherpool.data.dto.SpecialOfferActivateDto;
import com.boost.voucherpool.data.dto.SpecialOfferDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SpecialOfferService {

    private final SpecialOfferDao specialOfferDao;

    private final RecipientDao recipientDao;

    private final VoucherCodeService voucherCodeService;

    public SpecialOfferService(SpecialOfferDao specialOfferDao, RecipientDao recipientDao, VoucherCodeService voucherCodeService) {
        this.specialOfferDao = Objects.requireNonNull(specialOfferDao);
        this.recipientDao = Objects.requireNonNull(recipientDao);
        this.voucherCodeService = Objects.requireNonNull(voucherCodeService);
    }

    public void create(SpecialOfferDto soDto) {
        SpecialOffer so = new SpecialOffer();
        so.setDiscountPercentage(soDto.getDiscountPercentage());
        so.setName(soDto.getName());
        specialOfferDao.save(so);
        // Create voucher code for each recipient
        List<Recipient> recipients = recipientDao.findAll();
        for (Recipient recipient : recipients) {
            voucherCodeService.create(recipient, soDto.getExpiryDate(), so);
        }
    }
}
