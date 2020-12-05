package com.boost.voucherpool.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialOfferActivateDto {

    private String name;

    private Date expiryDate;
}
