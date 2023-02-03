package com.csi.model;

import com.csi.enums.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

//import javax.persistence.*;

//@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Document("UserInfo")
public class UserInfo {

//    @Id
//    @GeneratedValue
    @Id
    @MongoId(FieldType.STRING)
    private String userId;

    //  @Size(min = 2, max = 16, message = "Employee Name Should Be At Least 2 to 16 Character")
//    @Pattern(regexp = "^([A-Za-z]){2,16}$", message = "Special Character , Number And Space Is Not Allowed")
   // @Column(nullable = false)
    private String userFirstName;

    //@Size(min = 2, max = 16, message = "Employee Name Should Be At Least 2 to 16 Character")
//    @Pattern(regexp = "^([A-Za-z]){2,16}$", message = "Special Character , Number And Space Is Not Allowed")
   //@Column(nullable = false)
    private String userLastName;

    //@Column(nullable = false)
    // @Min(value = 555555555, message = "Phone Number should not be less than 10 Digits")
    // @Pattern(regexp = "($|[0-9]{10})")
    private long userContactNumber;


//    @Pattern(regexp = "^[a-z0-9+.]+@[a-z]+.[a-z]+$", message = "Please Enter Valid Emails Id")

   // @Column(unique = true, nullable = false)
    @Indexed(unique = true )
    private String userEmail;

    /* @NotBlank(message = "Please Enter Password")
     @Size(min = 8, max = 16, message = "Password Should Be At Least 8 to 12 Characters")
     @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
             message = "The Password Should Be At least one upper case English, At least one lower case English letter, " +
                     "At least one digit, At least one special character And Space Is Not Allowed")*/
   // @Column(nullable = false)
    private String userPassword;

    //@Enumerated(value = EnumType.STRING)
    @Field(targetType = FieldType.STRING)
    private Roles roles;


    @JsonIgnore
    public String getUserPassword() {
        return userPassword;
    }

    @JsonProperty
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
