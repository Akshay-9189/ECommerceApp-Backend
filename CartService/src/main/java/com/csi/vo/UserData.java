package com.csi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserData {

    private String userId;

    private String userFirstName;

    private String userLastName;

    private long userContactNumber;

    private String userEmail;
}