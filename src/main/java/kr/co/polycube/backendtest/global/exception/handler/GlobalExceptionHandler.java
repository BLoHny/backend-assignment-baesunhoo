package kr.co.polycube.backendtest.global.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.polycube.backendtest.global.exception.BasicException;
import kr.co.polycube.backendtest.global.exception.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> globalExceptionHandler(HttpServletRequest request, BasicException e) {

        printError(request, e.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(e.getErrorCode().getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorMessage> noHandlerFoundException(NoHandlerFoundException e) {

        return new ResponseEntity<>(new ErrorMessage("요청하신 URL에 대한 Controller가 존재 하지 않습니다."), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorMessage> validateException(MethodArgumentNotValidException e) {

        BindingResult bindingResult = e.getBindingResult();
        return new ResponseEntity<>(new ErrorMessage(bindingResult.getFieldErrors().getFirst().getDefaultMessage()), HttpStatus.BAD_REQUEST);
    }

    private void printError(HttpServletRequest request, String message) {
        log.error(request.getRequestURI());
        log.error(message);
    }
}
