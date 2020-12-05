package com.boost.voucherpool.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialOfferDto {

    private String name;

    private BigDecimal discountPercentage;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiryDate;
}
