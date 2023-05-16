package com.srivn.works.smusers.util;

import com.srivn.works.smusers.exception.SMException;
import com.srivn.works.smusers.services.UserAdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.crypto.spec.SecretKeySpec;

import java.security.MessageDigest;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {AppUtil.class})
@ActiveProfiles({"test"})
@ExtendWith(SpringExtension.class)
class AppUtilTest {

    String secretStr;
    String usertxt;
    String encrytpedTxt;
    String strForHex;
    String hextoStr;


    @BeforeEach
    void atStart() {
        secretStr = "mysecret";
        usertxt = "myToken";
        encrytpedTxt = "21EKMhoe60seO1oe5jkx8g==";
        strForHex = "21EKMhoe60seO1oe5jkx8g==";
        hextoStr = "3231454b4d686f65363073654f316f65356a6b7838673d3d";
    }

    @Test
    static void test_generatePwd() {
        String generatedPwd = AppUtil.generatePwd();
        assertEquals(12, generatedPwd.length());
        assertEquals(false, generatedPwd.contains("_"));
    }

    @Test
    public void test_GetKey_P() {
        // Arrange
        byte[] expectedKeyBytes = {-60, -6, -69, -84, -5, -37, 97, 70, -120, -56, -76, -29, -72, -68, -12, 23};
        // Act
        SecretKeySpec actualKey = AppUtil.getKey(secretStr);
        // Assert
        assertNotNull(actualKey);
        assertEquals("AES",actualKey.getAlgorithm());
    }

    @Test
    public void test_EncryptToken_P() {
        // Act
        String encryptedToken = AppUtil.encryptToken(usertxt, secretStr);
        // Assert
        assertNotNull(encryptedToken);
        assertEquals(encrytpedTxt,encryptedToken);
    }

    @Test
    public void test_EncryptToken_EX() {
        usertxt = null;
        // Act
        SMException exception = assertThrows(SMException.class, () -> AppUtil.encryptToken(usertxt, secretStr));
        // Assert
        assertEquals("UNKNOWN", exception.getExType());
    }

    @Test
    public void test_decryptToken() {
        // Act
        String deryptedToken = AppUtil.decryptToken(encrytpedTxt, secretStr);
        // Assert
        assertNotNull(deryptedToken);
        assertEquals(usertxt,deryptedToken);
    }

    @Test
    public void test_DcryptToken_EX() {
        encrytpedTxt = null;
        // Act
        SMException exception = assertThrows(SMException.class, () -> AppUtil.decryptToken(encrytpedTxt, secretStr));
        // Assert
        assertEquals("UNKNOWN", exception.getExType());
    }

    @Test
    public void test_convertStringToHex() {
        // Act
        String deryptedToken = AppUtil.convertStringToHex(strForHex);
        // Assert
        assertNotNull(deryptedToken);
        assertEquals(hextoStr,deryptedToken);
    }

    @Test
    public void test_convertHexToString() {
        // Act
        String deryptedToken = AppUtil.convertHexToString(hextoStr);
        // Assert
        assertNotNull(deryptedToken);
        assertEquals(strForHex,deryptedToken);
    }
}

