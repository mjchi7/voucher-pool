package com.boost.voucherpool.controller;

import com.boost.voucherpool.dao.VoucherCodeDao;
import com.boost.voucherpool.data.VoucherCode;
import com.boost.voucherpool.data.dto.VoucherCodeUsageDto;
import com.boost.voucherpool.service.VoucherCodeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
public class VoucherCodeController {

    private static final String path = "/voucher";

    private final VoucherCodeService voucherCodeService;

    public VoucherCodeController(VoucherCodeService voucherCodeService) {
        this.voucherCodeService = Objects.requireNonNull(voucherCodeService);
    }

    @PutMapping(value = path + "/use", consumes = MediaType.APPLICATION_JSON_VALUE)
    public VoucherCode use(@Valid @RequestBody VoucherCodeUsageDto voucherCodeUsageDto) {
        return voucherCodeService.use(voucherCodeUsageDto);
    }

    @GetMapping(value = path, consumes = MediaType.APPLICATION_JSON_VALUE)
    public VoucherCode search(@RequestParam String email) {
        return voucherCodeService.findByRecipientEmail(email);
    }
}
