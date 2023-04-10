package com.csi.controller;

import com.csi.dto.AddUpdateUserRequest;
import com.csi.model.UserInfo;
import com.csi.service.UserService;
import com.csi.service.impl.CustomUserDetailService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:8000", allowCredentials = "true")
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    // @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/")
    public ResponseEntity<List<UserInfo>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<UserInfo> getUser(@PathVariable String userId) {
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserInfo> updateUser(@PathVariable String userId, @RequestBody AddUpdateUserRequest addUpdateUserRequest) {
        return new ResponseEntity<>(userService.updateUser(userId, addUpdateUserRequest), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User Deleted Successfully ", HttpStatus.OK);
    }

    @GetMapping("/user-details/{userEmail}")
    public ResponseEntity<UserDetails> getUserDetails(@PathVariable String userEmail) {
        return new ResponseEntity<>(customUserDetailService.loadUserByUsername(userEmail), HttpStatus.OK);
    }

/*    @PatchMapping("/change-role/{userId}")
    public ResponseEntity<?> updateRole(@PathVariable String userId) {
        UserInfo info = userService.getUser(userId);
        if (info.getRoles().equals(Roles.USER)) {
            info.setRoles(Roles.ADMIN);
            userService.saveUser(info);
        } else if (info.getRoles().equals(Roles.ADMIN)) {
            info.setRoles(Roles.USER);
            userService.saveUser(info);
        }
        return new ResponseEntity<>(info, HttpStatus.OK);
    }*/
}