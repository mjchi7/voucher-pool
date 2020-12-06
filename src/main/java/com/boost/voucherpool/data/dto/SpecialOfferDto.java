package com.boost.voucherpool.data.dto;

import com.boost.voucherpool.constant.Parameter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialOfferDto {

    @NotBlank
    private String name;

    @DecimalMax(value = "100.0", inclusive = true)
    @DecimalMin(value = "0.00", inclusive = true)
    @Digits(integer=3, fraction=2)
    private BigDecimal discountPercentage;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiryDate;
}
