package com.csi.dto;

import com.csi.model.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class LogInResponse {

    private String jwtToken;

    private UserInfo userInfo;
}
