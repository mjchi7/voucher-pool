package com.boost.voucherpool.service;

import com.boost.voucherpool.constant.Parameter;
import com.boost.voucherpool.dao.RecipientDao;
import com.boost.voucherpool.dao.SpecialOfferDao;
import com.boost.voucherpool.data.Recipient;
import com.boost.voucherpool.data.SpecialOffer;
import com.boost.voucherpool.data.dto.SpecialOfferActivateDto;
import com.boost.voucherpool.data.dto.SpecialOfferDto;
import com.boost.voucherpool.exception.ProgrammingException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class SpecialOfferService {

    private final SpecialOfferDao specialOfferDao;

    private final RecipientDao recipientDao;

    private final VoucherCodeService voucherCodeService;

    public SpecialOfferService(SpecialOfferDao specialOfferDao,
                               RecipientDao recipientDao,
                               VoucherCodeService voucherCodeService) {
        this.specialOfferDao = Objects.requireNonNull(specialOfferDao);
        this.recipientDao = Objects.requireNonNull(recipientDao);
        this.voucherCodeService = Objects.requireNonNull(voucherCodeService);
    }

    public void create(SpecialOfferDto soDto) {
        if (soDto.getDiscountPercentage()
                .compareTo(Parameter.MIN_DISCOUNT_PCT) < 0 || soDto
                .getDiscountPercentage()
                .compareTo(Parameter.MAX_DISCOUNT_PCT) > 0) {
            // Error! Discount percentage cannot be less than 0.00 or greater
            // than 100.00
            throw new ProgrammingException("Discount percentage out of bound:" +
                    " " + soDto
                    .getDiscountPercentage());
        }
        if (LocalDate.now().isAfter(soDto.getExpiryDate())) {
            throw new ProgrammingException("Expiry date {" + soDto
                    .getExpiryDate() + "} is earlier than today {" + LocalDate
                    .now() + "}");
        }
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
