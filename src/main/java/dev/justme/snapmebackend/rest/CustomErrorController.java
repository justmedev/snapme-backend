package dev.justme.snapmebackend.rest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@RestController
public class CustomErrorController implements ErrorController {
    private static class Error {
        public String message;

        public Error(String message) {
            this.message = message;
        }
    }
    @RequestMapping("/error")
    public Error handleError(HttpServletRequest request) {
        return new Error((String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
    }
}