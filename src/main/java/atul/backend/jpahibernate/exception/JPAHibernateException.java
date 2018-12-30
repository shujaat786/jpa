package atul.backend.jpahibernate.exception;

import atul.backend.jpahibernate.constants.Const;
import atul.backend.jpahibernate.model.ErrorDetails;
import atul.backend.jpahibernate.model.ResponseBody;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JPAHibernateException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final ResponseBody responseBody = new ResponseBody();

    public JPAHibernateException(String message) {
        super(message);
    }

    public JPAHibernateException(String message, HttpStatus httpStatus) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setCode(Const.Status.ERROR);
        errorDetails.setErrors(message);
        errorDetails.setHttpStatus(httpStatus);
        List<ErrorDetails> detailsList = new ArrayList<>();
        detailsList.add(errorDetails);
        responseBody.setErrorDetails(detailsList);
    }
}
