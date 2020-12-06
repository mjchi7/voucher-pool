package com.boost.voucherpool.unit;

import com.boost.voucherpool.dao.VoucherCodeDao;
import com.boost.voucherpool.data.Recipient;
import com.boost.voucherpool.data.SpecialOffer;
import com.boost.voucherpool.data.VoucherCode;
import com.boost.voucherpool.data.dto.VoucherCodeUsageDto;
import com.boost.voucherpool.exception.InvalidVoucherCodeException;
import com.boost.voucherpool.service.VoucherCodeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
public class VoucherCodeServiceTest {

    @Mock
    VoucherCodeDao voucherCodeDao;

    VoucherCodeService voucherCodeService;

    @Captor
    ArgumentCaptor<VoucherCode> vcCaptor;

    @BeforeEach
    public void setup() {
        voucherCodeService = new VoucherCodeService(voucherCodeDao);
    }

    @Test
    public void create_validData() {
        Recipient recipient = new Recipient(1L, "user1", "user1@gmail.com");
        LocalDate expiryDate = LocalDate.of(9999, 12, 31);
        SpecialOffer specialOffer = new SpecialOffer(1L, "special offer 1",
                new BigDecimal("13.30"));
        voucherCodeService.create(recipient, expiryDate, specialOffer);

        verify(voucherCodeDao, times(1)).save(vcCaptor.capture());
        VoucherCode capturedVc = vcCaptor.getValue();
        assertEquals(recipient, capturedVc.getRecipient());
        assertEquals(specialOffer, capturedVc.getSpecialOffer());
        assertEquals(expiryDate, capturedVc.getExpiryDate());
        assertNull(capturedVc.getUsageDate());
        assertNotNull(capturedVc.getCode());
    }

    @Test
    public void use_validData() {
        String code = "testcode";
        String testemail = "testemail";
        LocalDate expiryDate = LocalDate.of(9999, 12, 31);
        Recipient mockedRecipient = new Recipient(1L, "testuser", "testemail");
        SpecialOffer mockedSpecialOffer = new SpecialOffer(1L, "special " +
                "offer", new BigDecimal("13.3"));

        when(voucherCodeDao.findByCodeAndRecipientEmail(code, testemail))
                .thenReturn(Optional
                        .of(new VoucherCode(1L, mockedRecipient,
                                mockedSpecialOffer, code, expiryDate, null)));

        VoucherCodeUsageDto voucherCodeUsageDto =
                new VoucherCodeUsageDto(testemail, code);

        VoucherCode usedVc = this.voucherCodeService.use(voucherCodeUsageDto);
        verify(voucherCodeDao, times(1)).save(usedVc);
        assertNotNull(usedVc.getUsageDate());
        assertEquals(code, usedVc.getCode());
        assertEquals(expiryDate, usedVc.getExpiryDate());
        assertEquals(mockedRecipient, usedVc.getRecipient());
        assertEquals(mockedSpecialOffer, usedVc.getSpecialOffer());
    }

    @Test
    public void use_expiredVoucher() {
        String code = "testcode";
        String testemail = "testemail";
        LocalDate expiryDate = LocalDate.of(2001, 3, 15);
        Recipient mockedRecipient = new Recipient(1L, "testuser", "testemail");
        SpecialOffer mockedSpecialOffer = new SpecialOffer(1L, "special " +
                "offer", new BigDecimal("13.3"));

        when(voucherCodeDao.findByCodeAndRecipientEmail(code, testemail))
                .thenReturn(Optional
                        .of(new VoucherCode(1L, mockedRecipient,
                                mockedSpecialOffer, code, expiryDate, null)));

        VoucherCodeUsageDto voucherCodeUsageDto =
                new VoucherCodeUsageDto(testemail, code);

        assertThrows(InvalidVoucherCodeException.class,
                () -> this.voucherCodeService
                .use(voucherCodeUsageDto));
        verify(voucherCodeDao, never()).save(any());
    }

    @Test
    public void use_usedVoucher() {
        String code = "testcode";
        String testemail = "testemail";
        LocalDate expiryDate = LocalDate.now();
        Recipient mockedRecipient = new Recipient(1L, "testuser", "testemail");
        SpecialOffer mockedSpecialOffer = new SpecialOffer(1L, "special " +
                "offer", new BigDecimal("13.3"));

        when(voucherCodeDao.findByCodeAndRecipientEmail(code, testemail))
                .thenReturn(Optional
                        .of(new VoucherCode(1L, mockedRecipient,
                                mockedSpecialOffer, code, expiryDate, LocalDate
                                .now())));

        VoucherCodeUsageDto voucherCodeUsageDto =
                new VoucherCodeUsageDto(testemail, code);

        assertThrows(InvalidVoucherCodeException.class,
                () -> this.voucherCodeService
                .use(voucherCodeUsageDto));
        verify(voucherCodeDao, never()).save(any());
    }

    @Test
    public void search_validData() {
        String code = "testcode";
        String testemail = "testemail";
        LocalDate expiryDate = LocalDate.now();
        Recipient mockedRecipient = new Recipient(1L, "testuser", "testemail");
        SpecialOffer mockedSpecialOffer1 = new SpecialOffer(1L, "special " +
                "offer", new BigDecimal("13.3"));
        SpecialOffer mockedSpecialOffer2 = new SpecialOffer(2L, "special " +
                "offer 2", new BigDecimal("13.3"));

        VoucherCode mockedVc1 = new VoucherCode(1L, mockedRecipient,
                mockedSpecialOffer1, code, expiryDate,
                LocalDate.now());
        VoucherCode mockedVc2 = new VoucherCode(2L, mockedRecipient,
                mockedSpecialOffer2, code + "2", expiryDate, LocalDate
                .now());
        List<VoucherCode> mockedVcs = Arrays.asList(mockedVc1, mockedVc2);

        when(voucherCodeDao.findByRecipientEmail(testemail))
                .thenReturn(mockedVcs);

        List<VoucherCode> vcs = voucherCodeService.findByRecipientEmail(testemail);
        assertIterableEquals(mockedVcs, vcs);
        verify(voucherCodeDao, times(1)).findByRecipientEmail(testemail);
    }
}
