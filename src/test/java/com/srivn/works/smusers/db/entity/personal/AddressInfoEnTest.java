package com.srivn.works.smusers.db.entity.personal;

import com.srivn.works.smusers.db.entity.util.ClsnEn;
import com.srivn.works.smusers.db.entity.util.ClsnValEn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressInfoEnTest {

    ClsnValEn country;
    AddressInfoEn actualAddressInfoEn1;
    AddressInfoEn actualAddressInfoEn2;
    AddressInfoEn actualAddressInfoEn3;
    @BeforeEach
    void init(){
        country = new ClsnValEn(100, "INDIA", new ClsnEn(1, "COUNTRY"));
        actualAddressInfoEn1 = new AddressInfoEn("42", "Street", "Oxford", "MD", country, "21654");
        actualAddressInfoEn2 = new AddressInfoEn("42", "Street", "Oxford", "MD", country, "21654");
        actualAddressInfoEn3 = new AddressInfoEn("43", "Street", "Oxford", "MD", country, "21654");
    }

    @Test
    void test_constructor_N() {
        // Arrange and Act
        AddressInfoEn actualAddressInfoEn = new AddressInfoEn();

        // Assert
        assertNull(actualAddressInfoEn.getZipCode());
        assertNull(actualAddressInfoEn.getStreet());
        assertNull(actualAddressInfoEn.getState());
        assertNull(actualAddressInfoEn.getHouseNumber());
        assertNull(actualAddressInfoEn.getCountry());
        assertNull(actualAddressInfoEn.getCity());
    }

    @Test
    void test_constructor_P() {
        // Assert
        assertEquals("21654", actualAddressInfoEn1.getZipCode());
        assertEquals("Street", actualAddressInfoEn1.getStreet());
        assertEquals("MD", actualAddressInfoEn1.getState());
        assertEquals("42", actualAddressInfoEn1.getHouseNumber());
        ClsnValEn country2 = actualAddressInfoEn1.getCountry();
        assertEquals("Oxford", actualAddressInfoEn1.getCity());
        assertEquals("COUNTRY", actualAddressInfoEn1.getCountry().getClsn().getClsnKey());
        assertEquals("INDIA", actualAddressInfoEn1.getCountry().getValue());
    }

    @Test
    void test_equals_N() {
        // Arrange, Act and Assert
        assertNotEquals(actualAddressInfoEn1, actualAddressInfoEn3);
    }

    @Test
    void test_equals_P() {
        assertEquals(actualAddressInfoEn1, actualAddressInfoEn2);
    }

}

