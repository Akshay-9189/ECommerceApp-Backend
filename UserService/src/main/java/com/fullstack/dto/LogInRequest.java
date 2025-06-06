package com.fullstack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class LogInRequest {

    /*
        @Pattern(regexp = "^[a-z0-9+.]+@[a-z]+.[a-z]+$", message = "Please Enter Valid Emails Id")
    */
    private String userEmail;

    /*    @NotBlank(message = "Please Enter Password")
        @Size(min = 8, max = 16, message = "Password Should Be At Least 8 to 12 Characters")*/
    private String userPassword;
}
