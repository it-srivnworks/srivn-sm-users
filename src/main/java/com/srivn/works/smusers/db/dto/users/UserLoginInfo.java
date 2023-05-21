package com.srivn.works.smusers.db.dto.users;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Getter
@Setter
public class UserLoginInfo implements Serializable {

    private String email;
    private String password;
    private String token;

    public UserLoginInfo() {
        super();
    }

    @Builder
    public UserLoginInfo(String email, String password, String token) {
        this.email = email;
        this.password = password;
        this.token = token;
    }
}
