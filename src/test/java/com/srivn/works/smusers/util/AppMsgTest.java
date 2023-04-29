package com.srivn.works.smusers.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class AppMsgTest {
    @Test
    void test_Err_getMsgP() {
        // Arrange, Act and Assert
        assertEquals("Unknown Error, Please Try Again!", AppMsg.Err.ERR_UKN_000.getMsgP("Params"));
    }

    @Test
    void test_Msg_getMsgP() {
        // Arrange, Act and Assert
        assertEquals("Howdy!", AppMsg.Msg.MSG_OK_000.getMsgP("Params"));
    }

    @Test
    void test_Err_getMsg() {
        assertEquals("Unknown Error, Please Try Again!", AppMsg.Err.ERR_UKN_000.getMsg());
    }

    @Test
    void test_Msg_getMsg() {
        assertEquals("Howdy!", AppMsg.Msg.MSG_OK_000.getMsg());    }

    @Test
    void test_Err_valueOf() {
        // Arrange and Act
        AppMsg.Err actualValueOfResult = AppMsg.Err.valueOf("ERR_UKN_000");

        // Assert
        assertEquals("UNKNOWN", actualValueOfResult.getCode());
        assertEquals("Unknown Error, Please Try Again!", actualValueOfResult.getMsg());
    }

    @Test
    void test_Msg_valueOf() {
        // Arrange and Act
        AppMsg.Msg actualValueOfResult = AppMsg.Msg.valueOf("MSG_OK_000");

        // Assert
        assertEquals("OK", actualValueOfResult.getCode());
        assertEquals("Howdy!", actualValueOfResult.getMsg());
    }

}

