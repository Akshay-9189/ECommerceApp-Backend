package com.csi.controller;

import com.csi.dto.AddUpdateUserRequest;
import com.csi.dto.LogInRequest;
import com.csi.dto.LogInResponse;
import com.csi.security.JwtUtil;
import com.csi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@CrossOrigin(origins = "http://localhost:8000", allowCredentials = "true")
@Slf4j
public class PublicController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> saveUser(@RequestBody AddUpdateUserRequest addUpdateUserRequest) {
        return new ResponseEntity<>(userService.saveUser(addUpdateUserRequest), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> generateToken(@RequestBody LogInRequest logInRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(logInRequest.getUserEmail(), logInRequest.getUserPassword()));
        } catch (UsernameNotFoundException ex) {
            log.warn("Incorrect Username Or Password" + ex.getMessage(), ex.getCause());
        }
        String accessToken = jwtUtil.generateToken(logInRequest.getUserEmail());
        return new ResponseEntity<>(new LogInResponse(accessToken, userService.getByUserEmail(logInRequest.getUserEmail())), HttpStatus.OK);
    }
}