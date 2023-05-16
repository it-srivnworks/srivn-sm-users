package com.srivn.works.smusers.db.dto.users;

import com.srivn.works.smusers.db.dto.personal.AddressInfo;
import com.srivn.works.smusers.db.dto.personal.ContactInfo;
import com.srivn.works.smusers.db.dto.personal.HealthInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class StudentInfo extends UserInfo {

    private ContactInfo contactInfo;

    private ContactInfo emergencyContact;

    private AddressInfo addressInfo;

    private HealthInfo healthInfo;

    private String pguardian;

    private String sguardian;

    public StudentInfo() {super();
    }

    public StudentInfo(String firstName, String lastName, String gender, String userDOB, String userType, String userEmail, String pguardian, String sguardian) {
        super(firstName, lastName, gender, userDOB, userType, userEmail);
        this.pguardian = pguardian;
        this.sguardian = sguardian;
    }
}
