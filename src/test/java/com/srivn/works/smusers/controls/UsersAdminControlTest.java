package com.srivn.works.smusers.controls;

import com.srivn.works.smusers.config.jwt.JwtRequestFilter;
import com.srivn.works.smusers.config.jwt.JwtTokenUtil;
import com.srivn.works.smusers.db.dto.users.StudentInfo;
import com.srivn.works.smusers.db.repo.personal.AddressInfoRepo;
import com.srivn.works.smusers.db.repo.personal.ContactInfoRepo;
import com.srivn.works.smusers.db.repo.users.UserInfoRepo;
import com.srivn.works.smusers.db.repo.users.UserLoginInfoRepo;
import com.srivn.works.smusers.db.repo.users.VerifTokenRepo;
import com.srivn.works.smusers.exception.SMException;
import com.srivn.works.smusers.exception.SMMessage;
import com.srivn.works.smusers.services.DataTransactionService;
import com.srivn.works.smusers.services.StudentService;
import com.srivn.works.smusers.services.UserAdminService;
import com.srivn.works.smusers.util.AppMsg;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.WebRequest;

import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles({"test"})
@WebMvcTest(UsersAdminControl.class)
@AutoConfigureMockMvc(addFilters = false)
class UsersAdminControlTest {

    @MockBean
    JwtTokenUtil jwtTokenUtil;

    @MockBean
    JwtRequestFilter jwtRequestFilter;

    @MockBean
    private  UserAdminService userAdminService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_confirmRegistration_P() throws Exception {
        //Arrange
        when(userAdminService.confirmRegistration(Locale.ENGLISH,"SampleTOKEN")).thenReturn(SMMessage.getSMMessage(AppMsg.Msg.MSG_ACTIVATED_005));
        //Act
        mockMvc.perform(get("/users/confirmRegistration?token=SampleTOKEN"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.appCode").value("ACTIVATED"));
        //Assert
        verify(userAdminService, times(1)).confirmRegistration(Locale.ENGLISH,"SampleTOKEN");
    }

}

