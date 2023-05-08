package com.srivn.works.smusers.exception;

import com.srivn.works.smusers.util.AppMsg;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SMExceptionTest {
    @Test
    void test_getSMException() {
        // Arrange and Act
        SMException actualSMException = SMException.getSMException(AppMsg.Err.ERR_UKN_000, "Xtra String");
        // Assert
        assertEquals("Unknown Error, Please Try Again!", actualSMException.getMessage());
        assertEquals("UNKNOWN", actualSMException.getExType());
    }
}

