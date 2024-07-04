package org.romanconverter.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.romanconverter.enums.ErrorCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ConversionIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(username = "emptyInputUser")
    @Test
    public void testIntegrationConvertEmptyInput() throws Exception {
        mockMvc.perform(get("/romannumeral?query="))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(ErrorCodeEnum.INPUT_EMPTY.getDescription())));
    }

    @WithMockUser(username = "invalidInputUser")
    @Test
    public void testIntegrationConvertInvalidInput() throws Exception {
        mockMvc.perform(get("/romannumeral?query=notanumber"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(ErrorCodeEnum.INVALID_FORMAT.getDescription())));
    }

    @WithMockUser(username = "outOfRangeUser")
    @Test
    public void testIntegrationConvertOutOfRange() throws Exception {
        mockMvc.perform(get("/romannumeral?query=3000000000"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(ErrorCodeEnum.OUT_OF_RANGE.getDescription())));
    }

    @WithMockUser(username = "zeroInputUser")
    @Test
    public void testIntegrationConvertZeroInput() throws Exception {
        mockMvc.perform(get("/romannumeral?query=0"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(ErrorCodeEnum.OUT_OF_RANGE.getDescription())));
    }

    @WithMockUser(username = "validInputUser")
    @Test
    public void testIntegrationConvertValidNumber() throws Exception {
        mockMvc.perform(get("/romannumeral?query=88"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("LXXXVIII")));
    }
}
