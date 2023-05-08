package com.srivn.works.smusers.controls;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srivn.works.smusers.db.dto.users.StaffInfo;
import com.srivn.works.smusers.exception.SMException;
import com.srivn.works.smusers.exception.SMMessage;
import com.srivn.works.smusers.services.StaffService;
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

@ExtendWith(SpringExtension.class)
@ActiveProfiles({"test"})
@WebMvcTest(StaffInfoControl.class)
class StaffInfoControlTest {

    @MockBean
    private StaffService staffService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;


    /*********************DATA*********************/
    String staffInfoJSON;


    @BeforeEach
    void init() {
        staffInfoJSON = "{ \"firstName\": \"Jane\", \"lastName\": \"Doe\", \"gender\": \"MALE\", \"userDOB\": \"MALE\", \"userType\": \"STAFF\", \"userEmail\": \"jane.doe@example.org\", \"dept\": \"ELEC\", \"title\": \"MR\" }";
    }

    @Test
    void test_addNewStaffInfo_P() throws Exception {
        //Arrange
        when(staffService.addNewStaffInfo(any(StaffInfo.class))).thenReturn(SMMessage.getSMMessage(AppMsg.Msg.MSG_ADD_001));
        //Act
        mockMvc.perform(post("/users/staff/ADD").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(staffInfoJSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.appCode").value("ADDED"));
        //Assert
        verify(staffService, times(1)).addNewStaffInfo(any(StaffInfo.class));
    }

    @Test
    void test_addNewStaffInfo_EX() throws Exception {
        //Arrange
        when(staffService.addNewStaffInfo(any(StaffInfo.class))).thenThrow(new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgP()));
        //Act
        mockMvc.perform(post("/users/staff/ADD").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(staffInfoJSON))
                .andExpect(status().isInternalServerError())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value("500"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Unknown Error, Please Try Again!"));
        //Assert
        verify(staffService, times(1)).addNewStaffInfo(any(StaffInfo.class));
    }

    @Test
    void test_updateStaffInfo_P() throws Exception {
        //Arrange
        when(staffService.updateStaffInfo(any(String.class),any(StaffInfo.class))).thenReturn(SMMessage.getSMMessage(AppMsg.Msg.MSG_UPDATE_003));
        //Act
        mockMvc.perform(put("/users/staff/UPDATE/jane.doe@example.org").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(staffInfoJSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.appCode").value("UPDATED"));
        //Assert
        verify(staffService, times(1)).updateStaffInfo(any(String.class), any(StaffInfo.class));
    }

    @Test
    void test_updateStaffInfo_EX() throws Exception {
        //Arrange
        when(staffService.updateStaffInfo(any(String.class), any(StaffInfo.class))).thenThrow(new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgP()));
        //Act
        mockMvc.perform(put("/users/staff/UPDATE/jane.doe@example.org").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(staffInfoJSON))
                .andExpect(status().isInternalServerError())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value("500"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Unknown Error, Please Try Again!"));
        //Assert
        verify(staffService, times(1)).updateStaffInfo(any(String.class) , any(StaffInfo.class));
    }


    @Test
    void test_deletStaffInfo_P() throws Exception {
        //Arrange
        when(staffService.deleteStaffInfo(any(String.class))).thenReturn(SMMessage.getSMMessage(AppMsg.Msg.MSG_DELETE_004));
        //Act
        mockMvc.perform(delete("/users/staff/DELETE/jane.doe@example.org").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.appCode").value("DELETED"));
        //Assert
        verify(staffService, times(1)).deleteStaffInfo(any(String.class));
    }

    @Test
    void test_deletStaffInfo_EX() throws Exception {
        //Arrange
        when(staffService.deleteStaffInfo(any(String.class))).thenThrow(new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgP()));
        //Act
        mockMvc.perform(delete("/users/staff/DELETE/jane.doe@example.org").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isInternalServerError())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value("500"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Unknown Error, Please Try Again!"));
        //Assert
        verify(staffService, times(1)).deleteStaffInfo(any(String.class));
    }

    @Test
    void test_getStaffInfoAll_P() throws Exception {
        //Arrange
        StaffInfo staffInfo01 = new StaffInfo("Jane", "Doe", "MALE", "1990-01-01", "STAFF", "jane.doe@example.org", "ELEC", "MR");
        StaffInfo staffInfo02 = new StaffInfo("Jenny", "Doe", "MALE", "1990-01-01", "STAFF", "jenny.doe@example.org", "ELEC", "MR");
        List<StaffInfo> isList = Arrays.asList(staffInfo01,staffInfo02 );
        when(staffService.getStaffInfoAll()).thenReturn(isList);
        //Act
        mockMvc.perform(get("/users/staff/ALL"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
        //Assert
        verify(staffService, times(1)).getStaffInfoAll();
    }

    @Test
    void test_getStaffInfoByEmail_P() throws Exception {
        //Arrange
        StaffInfo staffInfo = new StaffInfo("Jane", "Doe", "MALE", "1990-01-01", "STAFF", "jane.doe@example.org", "ELEC", "MR");
        when(staffService.getStaffInfoByEmail("jane.doe@example.org")).thenReturn(staffInfo);
        //Act
        mockMvc.perform(get("/users/staff/jane.doe@example.org"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userEmail").value("jane.doe@example.org"));
        //Assert
        verify(staffService, times(1)).getStaffInfoByEmail("jane.doe@example.org");
    }
}

