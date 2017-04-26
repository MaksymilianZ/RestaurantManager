package com.example.exception;

import com.example.controller.EventController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;

/**
 * Created by Maksymilian on 2017-04-26.
 */

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> exception(EntityNotFoundException e) {
        ModelAndView view = new ModelAndView();
        view.addObject(e.getMessage());
        return new ResponseEntity<Object>(view, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<Object> exception(EntityExistsException e) {
        ModelAndView view = new ModelAndView();
        view.addObject(e.getMessage());
        return new ResponseEntity<Object>(view, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<Object> exception(NoResultException e) {
        ModelAndView view = new ModelAndView();
        view.addObject(e.getMessage());
        return new ResponseEntity<Object>(view, HttpStatus.CONFLICT);
    }
}
