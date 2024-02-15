package ru.electroshop.apigateway;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiGatewayController {
    @RequestMapping("/fallback")
    public ResponseEntity<String> answering(String message) {
        return ResponseEntity.ok("Something went wrong" + message);}
}
