package com.srivn.works.smusers.controls;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
class WelcomeControlTest {

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
}

