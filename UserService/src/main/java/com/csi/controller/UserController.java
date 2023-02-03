package com.csi.controller;

import com.csi.enums.Roles;
import com.csi.model.UserInfo;
import com.csi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(value = "*")
public class UserController {

    @Autowired
    UserService userService;

   // @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/")
    public ResponseEntity<?> getAllUser() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getUser(@PathVariable String userId) {
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody UserInfo userInfo) {
        return new ResponseEntity<>(userService.updateUser(userId, userInfo), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User Deleted Successfully ", HttpStatus.OK);
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

