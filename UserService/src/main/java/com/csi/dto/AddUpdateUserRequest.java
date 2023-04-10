package com.csi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUpdateUserRequest {

    private String userFirstName;

    private String userLastName;

    private long userContactNumber;

    private String userEmail;

    private String userPassword;
}