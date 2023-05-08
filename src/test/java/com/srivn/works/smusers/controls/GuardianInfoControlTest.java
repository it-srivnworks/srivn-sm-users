package com.srivn.works.smusers.controls;

import com.srivn.works.smusers.db.dto.users.GuardianInfo;
import com.srivn.works.smusers.exception.SMException;
import com.srivn.works.smusers.exception.SMMessage;
import com.srivn.works.smusers.services.GuardianService;
import com.srivn.works.smusers.util.AppMsg;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles({"test"})
@ExtendWith(SpringExtension.class)
@WebMvcTest(GuardianInfoControl.class)
class GuardianInfoControlTest {

    @MockBean
    GuardianService guardianService;

    @Autowired
    private MockMvc mockMvc;

    String guardianInfoJSON;

    @BeforeEach
    void init() {
        guardianInfoJSON = "{ \"firstName\": \"Jane\", \"lastName\": \"Doe\", \"gender\": \"MALE\", \"userDOB\": \"MALE\", \"userType\": \"STAFF\", \"userEmail\": \"jane.doe@example.org\", \"dept\": \"ELEC\", \"title\": \"MR\" }";
    }

    @Test
    void test_addNewGuardianInfo_P() throws Exception {
        //Arrange
        when(guardianService.addNewGuardianInfo(any(GuardianInfo.class))).thenReturn(SMMessage.getSMMessage(AppMsg.Msg.MSG_ADD_001));
        //Act
        mockMvc.perform(post("/users/guardians/ADD").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(guardianInfoJSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.appCode").value("ADDED"));
        //Assert
        verify(guardianService, times(1)).addNewGuardianInfo(any(GuardianInfo.class));
    }

    @Test
    void test_addNewGuardianInfo_EX() throws Exception {
        //Arrange
        when(guardianService.addNewGuardianInfo(any(GuardianInfo.class))).thenThrow(new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgP()));
        //Act
        mockMvc.perform(post("/users/guardians/ADD").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(guardianInfoJSON))
                .andExpect(status().isInternalServerError())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value("500"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Unknown Error, Please Try Again!"));
        //Assert
        verify(guardianService, times(1)).addNewGuardianInfo(any(GuardianInfo.class));
    }

    @Test
    void test_updateGuardianInfo_P() throws Exception {
        //Arrange
        when(guardianService.updateGuardianInfo(any(String.class),any(GuardianInfo.class))).thenReturn(SMMessage.getSMMessage(AppMsg.Msg.MSG_UPDATE_003));
        //Act
        mockMvc.perform(put("/users/guardians/UPDATE/jane.doe@example.org").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(guardianInfoJSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.appCode").value("UPDATED"));
        //Assert
        verify(guardianService, times(1)).updateGuardianInfo(any(String.class),any(GuardianInfo.class));
    }

    @Test
    void test_updateGuardianInfo_EX() throws Exception {
        //Arrange
        when(guardianService.updateGuardianInfo(any(String.class),any(GuardianInfo.class))).thenThrow(new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgP()));
        //Act
        mockMvc.perform(put("/users/guardians/UPDATE/jane.doe@example.org").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(guardianInfoJSON))
                .andExpect(status().isInternalServerError())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value("500"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Unknown Error, Please Try Again!"));
        //Assert
        verify(guardianService, times(1)).updateGuardianInfo(any(String.class),any(GuardianInfo.class));
    }

    @Test
    void test_deletStaffInfo_P() throws Exception {
        //Arrange
        when(guardianService.deleteGuardianInfo(any(String.class))).thenReturn(SMMessage.getSMMessage(AppMsg.Msg.MSG_DELETE_004));
        //Act
        mockMvc.perform(delete("/users/guardians/DELETE/jane.doe@example.org").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.appCode").value("DELETED"));
        //Assert
        verify(guardianService, times(1)).deleteGuardianInfo(any(String.class));
    }

    @Test
    void test_deletStaffInfo_EX() throws Exception {
        //Arrange
        when(guardianService.deleteGuardianInfo(any(String.class))).thenThrow(new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgP()));
        //Act
        mockMvc.perform(delete("/users/guardians/DELETE/jane.doe@example.org").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isInternalServerError())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value("500"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Unknown Error, Please Try Again!"));
        //Assert
        verify(guardianService, times(1)).deleteGuardianInfo(any(String.class));
    }

    @Test
    void test_getGuardianInfoAll_P() throws Exception {
        //Arrange
        GuardianInfo guardianInfo01 = new GuardianInfo("Jane", "Doe", "MALE", "1990-01-01", "STAFF", "jane.doe@example.org");
        GuardianInfo guardianInfo02 = new GuardianInfo("Jenny", "Doe", "MALE", "1990-01-01", "STAFF", "je.doe@example.org");
        List<GuardianInfo> isList = Arrays.asList(guardianInfo01,guardianInfo02);
        when(guardianService.getGuardianInfoAll()).thenReturn(isList);
        //Act
        mockMvc.perform(get("/users/guardians/ALL"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
        //Assert
        verify(guardianService, times(1)).getGuardianInfoAll();
    }

    @Test
    void test_getGuardianInfoByEmail_P() throws Exception {
        //Arrange
        GuardianInfo guardianInfo01 = new GuardianInfo("Jane", "Doe", "MALE", "1990-01-01", "STAFF", "jane.doe@example.org");
        when(guardianService.getGuardianInfoByEmail("jane.doe@example.org")).thenReturn(guardianInfo01);
        //Act
        mockMvc.perform(get("/users/guardians/jane.doe@example.org"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userEmail").value("jane.doe@example.org"));
        //Assert
        verify(guardianService, times(1)).getGuardianInfoByEmail("jane.doe@example.org");
    }
}

