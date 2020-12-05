package com.boost.voucherpool.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIErrorData {

    private String exceptionId;

    private Integer status;

    private String message;

    private Object details;
}
