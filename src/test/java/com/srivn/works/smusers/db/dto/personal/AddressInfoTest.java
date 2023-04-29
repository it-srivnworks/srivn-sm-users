package com.srivn.works.smusers.db.dto.personal;

import com.srivn.works.smusers.db.entity.personal.AddressInfoEn;
import com.srivn.works.smusers.db.entity.util.ClsnValEn;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;


class AddressInfoTest {

    AddressInfo addressInfo1;
    AddressInfo addressInfo2;
     AddressInfo addressInfo3;

    @BeforeEach
    void init() {
        addressInfo1 = new AddressInfo("42", "Street", "Oxford", "MD", "INDIA", "21654");;
        addressInfo2 = new AddressInfo("42", "Street", "Oxford", "MD", "INDIA", "21654");
        addressInfo3 = new AddressInfo("43", "Street", "Oxford", "MD", "INDIA", "21654");

    }

    @Test
    void test_constructor() {
        // Arrange
        // Assert
        assertEquals("21654", addressInfo1.getZipCode());
        assertEquals("Street", addressInfo1.getStreet());
        assertEquals("MD", addressInfo1.getState());
        assertEquals("42", addressInfo1.getHouseNumber());
        assertEquals("Oxford", addressInfo1.getCity());
        assertEquals("INDIA", addressInfo1.getCountry());
    }

    @Test
    void test_equals_N() {
        // Assert
        assertNotEquals(addressInfo1, addressInfo3);
    }

    @Test
    void test_equals_P() {
        // Assert
        assertEquals(addressInfo1, addressInfo2);
    }
}

