package com.boost.voucherpool.handler;

import com.boost.voucherpool.data.dto.APIErrorData;
import com.boost.voucherpool.exception.EmailAlreadyExistException;
import com.boost.voucherpool.exception.InvalidVoucherCodeException;
import com.boost.voucherpool.exception.ProgrammingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;
import java.util.UUID;

import static com.boost.voucherpool.constant.Message.NO_SUCH_ELEMENT_MSG;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public APIErrorData handleNoSuchElementException(NoSuchElementException exp, WebRequest req) {
        APIErrorData errData = getBaseAPIErrorData();
        errData.setStatus(HttpStatus.NOT_FOUND.value());
        errData.setMessage(NO_SUCH_ELEMENT_MSG);
        logger.error(errData.toString());
        return errData;
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIErrorData handleEmailAlreadyExistException(EmailAlreadyExistException exp, WebRequest req) {
        APIErrorData errData = getBaseAPIErrorData();
        errData.setStatus(HttpStatus.BAD_REQUEST.value());
        errData.setMessage(exp.toString());
        logger.error(errData.toString());
        return errData;
    }

    @ExceptionHandler(InvalidVoucherCodeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public APIErrorData handleInvalidVoucherCodeException(InvalidVoucherCodeException exp) {
        APIErrorData errData = getBaseAPIErrorData();
        errData.setStatus(HttpStatus.NOT_FOUND.value());
        errData.setMessage(exp.toString());
        logger.error(errData.toString());
        return errData;
    }

    @ExceptionHandler(ProgrammingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public APIErrorData handleProgrammingException(ProgrammingException exp) {
        APIErrorData errData = getBaseAPIErrorData();
        errData.setMessage("Internal Server Error");
        errData.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        logger.error(errData.toString());
        return errData;
    }

    private APIErrorData getBaseAPIErrorData() {
        APIErrorData errData = new APIErrorData();
        errData.setExceptionId(this.getXcpId());
        return errData;
    }

    private String getXcpId() {
        return UUID.randomUUID().toString();
    }
}
