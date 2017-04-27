package com.example.exception;


import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;


/**
 * Created by Maksymilian on 2017-04-26.
 */

@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EntityExistsException.class)
    @ResponseBody
    ErrorInfo handleEntityExistRequest(HttpServletRequest req, Exception exception) {
        Logger logger = Logger.getRootLogger();
        logger.error("Handling EntityExistException. Response status: NOT_FOUND", exception);
        return new ErrorInfo(req.getRequestURL().toString(), exception);
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    ErrorInfo handleObjectNotFoundRequest(HttpServletRequest req, Exception exception) {
        Logger logger = Logger.getRootLogger();
        logger.error("Handling EntityNotFoundException. Response status: NOT_FOUND", exception);
        return new ErrorInfo(req.getRequestURL().toString(), exception);
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResultException.class)
    @ResponseBody
    ErrorInfo handleNoResultRequest(HttpServletRequest req, Exception exception) {
        Logger logger = Logger.getRootLogger();
        logger.error("Handling NoResultException. Response status: NOT_FOUND", exception);
        return new ErrorInfo(req.getRequestURL().toString(), exception);
    }
}