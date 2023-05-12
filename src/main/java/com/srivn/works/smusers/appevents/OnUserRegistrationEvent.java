package com.srivn.works.smusers.appevents;

import com.srivn.works.smusers.db.entity.users.UserLoginInfoEn;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@ToString
@Getter
@Setter
public class OnUserRegistrationEvent extends ApplicationEvent {
    private UserLoginInfoEn userLoginInfoEn;

    public OnUserRegistrationEvent(UserLoginInfoEn userLoginInfoEn) {
        super(userLoginInfoEn);
        this.userLoginInfoEn = userLoginInfoEn;
    }
}
