package org.gs.tokenexchange.controller;

import org.gs.tokenexchange.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenExchangeController {
    private final int cutLength = "Bearer ".length();
    private final Logger logger = LoggerFactory.getLogger(TokenExchangeController.class.getName());

    @GetMapping(value = "exchange", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<String> handleRequest(@RequestHeader String authorization) {

        if (authorization == null || !authorization.substring(0, cutLength).equalsIgnoreCase("BEARER ")) {
            String message = "no authorization bearer header";
            logger.error(message);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        try {
            String resultingJwt = JwtUtils.removeSign(authorization.substring(cutLength));
            logger.info("incoming: {}, outgoing: {}", authorization, resultingJwt);
            return new ResponseEntity<>(resultingJwt, HttpStatus.OK);
        } catch (Exception ex) {
            String message = ex.getMessage();
            logger.error(message);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

    }
}
