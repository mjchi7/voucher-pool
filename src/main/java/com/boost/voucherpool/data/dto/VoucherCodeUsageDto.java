package com.boost.voucherpool.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoucherCodeUsageDto {

    @NotBlank
    private String email;

    @NotBlank
    private String code;
}
