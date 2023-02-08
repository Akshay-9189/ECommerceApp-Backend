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

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Document("UserInfo")
public class UserInfo {

    @Id
    @MongoId(FieldType.STRING)
    private String userId;

    private String userFirstName;

    private String userLastName;

    private long userContactNumber;

    @Indexed(unique = true )
    private String userEmail;

    private String userPassword;

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
