package com.boost.voucherpool.exception;

public class InvalidVoucherCodeException extends RuntimeException {

    private String voucherCode;

    public InvalidVoucherCodeException(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    @Override
    public String toString() {
        return "Invalid voucher code. {" +
                "voucherCode='" + voucherCode + '\'' +
                '}';
    }
}
