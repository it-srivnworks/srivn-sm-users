package com.srivn.works.smusers.controls;

import com.srivn.works.smusers.config.GcpPubSubConfig.PubsubOutboundGateway;
import com.srivn.works.smusers.config.jwt.JwtRequestFilter;
import com.srivn.works.smusers.config.jwt.JwtTokenUtil;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@ActiveProfiles({"test"})
@WebMvcTest(WelcomeControl.class)
@AutoConfigureMockMvc(addFilters = false)
class WelcomeControlTest {

    @MockBean
    JwtTokenUtil jwtTokenUtil;

    @MockBean
    JwtRequestFilter jwtRequestFilter;

    @MockBean
    PubsubOutboundGateway messagingGateway;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_tester_P() throws Exception {
        // Arrange and Act
        mockMvc.perform(get("/welcome/tester").contentType(MediaType.APPLICATION_JSON))
                // Verify that the response is successful
                .andExpect(status().isOk())
                .andExpect(content().string("Howdy!!"));
    }

    @Test
    void test_testerPubSub_P() throws Exception {
        // Arrange and Act
        mockMvc.perform(get("/welcome/tester/pubsub?message=SampleMessaege").contentType(MediaType.APPLICATION_JSON))
                // Verify that the response is successful
                .andExpect(status().isOk())
                .andExpect(content().string("Howdy!!"));
    }
}

