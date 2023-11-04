package my.edu.sunway.wbrms.wbrmsfooddelivery.api;

import my.edu.sunway.wbrms.wbrmsfooddelivery.exception.NotFoundDeliveryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DeliveryControllerAdvice {

    @ExceptionHandler(NotFoundDeliveryException.class)
    ProblemDetail handleNotFoundReservation(NotFoundDeliveryException notFoundDeliveryException) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, notFoundDeliveryException.getMessage()
        );
        problemDetail.setTitle("Delivery with provided id is not existing");
        return problemDetail;
    }
}
