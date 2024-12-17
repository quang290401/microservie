package com.example.api_gateway.restcontroller;

import com.example.api_gateway.common.JwtHelber;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ldap-auth")
public class LdapAuthController {


    @PostMapping()
    public String authenticate() {
       return "Hello";
    }
}
