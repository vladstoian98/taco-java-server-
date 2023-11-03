package com.example.tacocloudapplication.controller;

import com.example.tacocloudapplication.table.util.AuthenticationRequest;
import com.example.tacocloudapplication.table.util.AuthenticationResponse;
import com.example.tacocloudapplication.table.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

//@Slf4j
//@Controller
//@RequestMapping("/login")
//public class LoginController {
//
//    @GetMapping
//    public String login(Model model, HttpSession session) {
//        AuthenticationException exception =
//                (AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
//
//        if (exception != null) {
//            model.addAttribute("error", "Unable to login. Check your username and password.");
//            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
//        }
//
//        return "login";
//    }
//
//}

@Slf4j
@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    private AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    private UserDetailsService userDetailsService;

    public LoginController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        System.out.println("Generated JWT:" + jwt);

        boolean isValidToken = jwtUtil.validateToken(jwt, userDetails);

        System.out.println(isValidToken);

        if (isValidToken) {
            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        } else {
            // Handle token validation failure
            throw new Exception("Token validation failed");
        }
    }

}




