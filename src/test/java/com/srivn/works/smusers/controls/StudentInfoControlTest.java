package com.srivn.works.smusers.controls;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srivn.works.smusers.config.jwt.JwtRequestFilter;
import com.srivn.works.smusers.config.jwt.JwtTokenUtil;
import com.srivn.works.smusers.db.dto.users.StudentInfo;
import com.srivn.works.smusers.exception.SMException;
import com.srivn.works.smusers.exception.SMMessage;
import com.srivn.works.smusers.services.StudentService;
import com.srivn.works.smusers.util.AppMsg;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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
@WebMvcTest(StudentInfoControl.class)
@AutoConfigureMockMvc(addFilters = false)
class StudentInfoControlTest {

    @MockBean
    JwtTokenUtil jwtTokenUtil;

    @MockBean
    JwtRequestFilter jwtRequestFilter;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    /*********************DATA*********************/
    String studentInfoJSON;

    @BeforeEach
    void init() {
        studentInfoJSON = "{ \"firstName\": \"fname\", \"lastName\": \"lname\", \"gender\": \"MALE\", \"userDOB\": \"2011-01-01\", \"userType\": \"STUDENT\", \"userEmail\": \"jane.doe@example.org\", \"pguardian\": \"primguardian.doe@example.org\", \"sguardian\": \"secguardian.doe@example.org\" }";
    }

    @Test
    void test_addNewStudentInfo_P() throws Exception {
        //Arrange
        when(studentService.addNewStudentInfo(any(StudentInfo.class))).thenReturn(SMMessage.getSMMessage(AppMsg.Msg.MSG_ADD_001));
        //Act
        mockMvc.perform(post("/users/student/ADD").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(studentInfoJSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.appCode").value("ADDED"));
        //Assert
        verify(studentService, times(1)).addNewStudentInfo(any(StudentInfo.class));
    }

    @Test
    void test_addNewStaffInfo_EX() throws Exception {
        //Arrange
        when(studentService.addNewStudentInfo(any(StudentInfo.class))).thenThrow(new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgP()));
        //Act
        mockMvc.perform(post("/users/student/ADD").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(studentInfoJSON))
                .andExpect(status().isInternalServerError())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value("500"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Unknown Error, Please Try Again!"));
        //Assert
        verify(studentService, times(1)).addNewStudentInfo(any(StudentInfo.class));
    }

    @Test
    void test_updateStudentInfo_P() throws Exception {
        //Arrange
        when(studentService.updateStudentInfo(any(String.class),any(StudentInfo.class))).thenReturn(SMMessage.getSMMessage(AppMsg.Msg.MSG_UPDATE_003));
        //Act
        mockMvc.perform(put("/users/student/UPDATE/jane.doe@example.org").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(studentInfoJSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.appCode").value("UPDATED"));
        //Assert
        verify(studentService, times(1)).updateStudentInfo(any(String.class), any(StudentInfo.class));
    }

    @Test
    void test_updateStudentInfo_EX() throws Exception {
        //Arrange
        when(studentService.updateStudentInfo(any(String.class), any(StudentInfo.class))).thenThrow(new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgP()));
        //Act
        mockMvc.perform(put("/users/student/UPDATE/jane.doe@example.org").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(studentInfoJSON))
                .andExpect(status().isInternalServerError())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value("500"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Unknown Error, Please Try Again!"));
        //Assert
        verify(studentService, times(1)).updateStudentInfo(any(String.class) , any(StudentInfo.class));
    }


    @Test
    void test_deletStudentInfo_P() throws Exception {
        //Arrange
        when(studentService.deleteStudentInfo(any(String.class))).thenReturn(SMMessage.getSMMessage(AppMsg.Msg.MSG_DELETE_004));
        //Act
        mockMvc.perform(delete("/users/student/DELETE/jane.doe@example.org").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.appCode").value("DELETED"));
        //Assert
        verify(studentService, times(1)).deleteStudentInfo(any(String.class));
    }

    @Test
    void test_deletStudentInfo_EX() throws Exception {
        //Arrange
        when(studentService.deleteStudentInfo(any(String.class))).thenThrow(new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgP()));
        //Act
        mockMvc.perform(delete("/users/student/DELETE/jane.doe@example.org").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isInternalServerError())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value("500"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Unknown Error, Please Try Again!"));
        //Assert
        verify(studentService, times(1)).deleteStudentInfo(any(String.class));
    }

    @Test
    void test_getStudentInfoAll_P() throws Exception {
        //Arrange
        StudentInfo studentInfo = new StudentInfo("Jane", "Doe", "Gender", "User DOB", "User Type","jane.doe@example.org", "primguardian.doe@example.org", "secguardian.doe@example.org");
        StudentInfo studentInfo01 = new StudentInfo("Jenny", "Doe", "Gender", "User DOB", "User Type","jenny.doe@example.org", "primguardian.doe@example.org", "secguardian.doe@example.org");
        List<StudentInfo> isList = Arrays.asList(studentInfo,studentInfo01 );
        when(studentService.getStudentInfoAll()).thenReturn(isList);
        //Act
        mockMvc.perform(get("/users/student/ALL"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
        //Assert
        verify(studentService, times(1)).getStudentInfoAll();
    }

    @Test
    void test_getStudentInfoByEmail_P() throws Exception {
        //Arrange
        StudentInfo studentInfo = new StudentInfo("Jane", "Doe", "Gender", "User DOB", "User Type","jane.doe@example.org", "primguardian.doe@example.org", "secguardian.doe@example.org");
        when(studentService.getStudentInfoByEmail("jane.doe@example.org")).thenReturn(studentInfo);
        //Act
        mockMvc.perform(get("/users/student/jane.doe@example.org"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userEmail").value("jane.doe@example.org"));
        //Assert
        verify(studentService, times(1)).getStudentInfoByEmail("jane.doe@example.org");
    }
}

