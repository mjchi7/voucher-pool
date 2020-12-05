package com.boost.voucherpool.unit;

import com.boost.voucherpool.dao.RecipientDao;
import com.boost.voucherpool.dao.SpecialOfferDao;
import com.boost.voucherpool.data.Recipient;
import com.boost.voucherpool.data.SpecialOffer;
import com.boost.voucherpool.data.dto.SpecialOfferDto;
import com.boost.voucherpool.service.SpecialOfferService;
import com.boost.voucherpool.service.VoucherCodeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SpecialOfferServiceTest {

    @Mock
    SpecialOfferDao specialOfferDao;

    @Mock
    RecipientDao recipientDao;

    @Mock
    VoucherCodeService voucherCodeService;

    SpecialOfferService specialOfferService;

    @Captor
    ArgumentCaptor<SpecialOffer> soCaptor;

    @BeforeEach
    public void setup() {
        List<Recipient> recipients = Arrays
                .asList(new Recipient(1L, "user1", "user1@gmail.com"),
                        new Recipient(1L, "user2", "user2@gmail.com"));
        when(recipientDao.findAll()).thenReturn(recipients);
        this.specialOfferService = new SpecialOfferService(specialOfferDao,
                recipientDao, voucherCodeService);
    }

    @Test
    public void create_validInput() {
        SpecialOfferDto specialOfferDto = new SpecialOfferDto("Special Offer " +
                "1", new BigDecimal("12.20"), LocalDate
                .of(2020, 12, 25));
        this.specialOfferService.create(specialOfferDto);
        verify(recipientDao, times(1)).findAll();
        verify(specialOfferDao, times(1)).save(soCaptor.capture());
        SpecialOffer capturedSo = soCaptor.getValue();
        assertEquals(specialOfferDto.getDiscountPercentage(), capturedSo
                .getDiscountPercentage());
        assertEquals(specialOfferDto.getName(), capturedSo.getName());
        verify(voucherCodeService, times(2)).create(any(), any(), any());
    }
}
