package com.boost.voucherpool.integration;

import com.boost.voucherpool.dao.RecipientDao;
import com.boost.voucherpool.dao.SpecialOfferDao;
import com.boost.voucherpool.dao.VoucherCodeDao;
import com.boost.voucherpool.data.Recipient;
import com.boost.voucherpool.data.SpecialOffer;
import com.boost.voucherpool.data.VoucherCode;
import com.boost.voucherpool.data.dto.SpecialOfferDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@Testcontainers
public class VoucherCodeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    RecipientDao recipientDao;

    @Autowired
    SpecialOfferDao specialOfferDao;

    @Autowired
    VoucherCodeDao voucherCodeDao;

    private final String voucherPath = "/voucher";

    @BeforeAll
    public void setup() {
        List<String> codes = Arrays
                .asList("testcode1", "testcode2", "testcode3", "testcode4");
        LocalDate expiryDate = LocalDate.of(9999, 12, 31);
        Recipient recipient1 = new Recipient(null, "user1", "user1@gmail.com");
        Recipient recipient2 = new Recipient(null, "user2", "user2@gmail.com");
        recipientDao.save(recipient1);
        recipientDao.save(recipient2);
        recipientDao.flush();
        SpecialOffer so1 = new SpecialOffer(null, "specialoffer1",
                new BigDecimal("10.00"));
        SpecialOffer so2 = new SpecialOffer(null, "specialoffer2",
                new BigDecimal("20.00"));
        specialOfferDao.save(so1);
        specialOfferDao.save(so2);
        specialOfferDao.flush();
        VoucherCode vc1 = new VoucherCode(null, recipient1, so1, codes
                .get(0), expiryDate, null);
        VoucherCode vc2 = new VoucherCode(null, recipient2, so1, codes
                .get(1), expiryDate, null);
        VoucherCode vc3 = new VoucherCode(null, recipient1, so2, codes
                .get(2), expiryDate, null);
        VoucherCode vc4 = new VoucherCode(null, recipient2, so2, codes
                .get(3), expiryDate, null);
        voucherCodeDao.save(vc1);
        voucherCodeDao.save(vc2);
        voucherCodeDao.save(vc3);
        voucherCodeDao.save(vc4);
        voucherCodeDao.flush();
    }

    @Test
    public void search_validData() throws Exception {
        MockHttpServletRequestBuilder req = MockMvcRequestBuilders
                .get(this.voucherPath).queryParam("email", "user1@gmail.com");

        mockMvc.perform(req).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
