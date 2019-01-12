package atul.backend.jpahibernate.controller;

import atul.backend.jpahibernate.service_h2.impl.h2RequestServiceImpl;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

public class MyErrorController implements ErrorController {
    static final Logger logger = Logger.getLogger(MyErrorController.class.getName());
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public String handleError() {
        //do something like logging
        logger.info("Catched to error class");
        return "error";
    }
}
