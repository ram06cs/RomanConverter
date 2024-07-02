package org.romanconverter.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.romanconverter.service.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@RunWith(SpringRunner.class)
@WebMvcTest(RomanConversionController.class)
@ActiveProfiles("test")
public class RomanConversionControllerTest {

    @MockBean
    private ConversionService conversionService;

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(username = "validInputUser")
    @Test
    public void testControllerConvertValidNumber() throws Exception {
        when(conversionService.numberToRomanNumeral(5L)).thenReturn("V");

        mockMvc.perform(get("/romannumeral?query=5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.input", is(5)))
                .andExpect(jsonPath("$.romanResult", is("V")))
                .andExpect(jsonPath("$.errorInfo", is(nullValue())));
    }

    @WithMockUser(username = "invalidInputUser")
    @Test
    public void testControllerConvertInvalidInput() throws Exception {
        mockMvc.perform(get("/romannumeral?query=a"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.input", is(nullValue())))
                .andExpect(jsonPath("$.romanResult", is(nullValue())))
                .andExpect(jsonPath("$.errorInfo.code", is(102)))
                .andExpect(jsonPath("$.errorInfo.message", is("Invalid number format")));
    }

    @WithMockUser(username = "emptyInputUser")
    @Test
    public void testControllerConvertEmptyInput() throws Exception {
        mockMvc.perform(get("/romannumeral?query="))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.input", is(nullValue())))
                .andExpect(jsonPath("$.romanResult", is(nullValue())))
                .andExpect(jsonPath("$.errorInfo.code", is(100)))
                .andExpect(jsonPath("$.errorInfo.message", is("Input cannot be empty")));
    }

    @WithMockUser(username = "outOfRangeInputUser")
    @Test
    public void testControllerConvertOutOfRangeInput() throws Exception {
        mockMvc.perform(get("/romannumeral?query=3000000000"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.input", is(3000000000L)))
                .andExpect(jsonPath("$.romanResult", is(nullValue())))
                .andExpect(jsonPath("$.errorInfo.code", is(101)))
                .andExpect(jsonPath("$.errorInfo.message", is("Input is out of acceptable range. The valid range is from 1 to 2200000000")));
    }

    @WithMockUser(username = "zeroInputUser")
    @Test
    public void testControllerConvertZeroInput() throws Exception {
        mockMvc.perform(get("/romannumeral?query=0"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.input", is(0)))
                .andExpect(jsonPath("$.romanResult", is(nullValue())))
                .andExpect(jsonPath("$.errorInfo.code", is(101)))
                .andExpect(jsonPath("$.errorInfo.message", is("Input is out of acceptable range. The valid range is from 1 to 2200000000")));
    }

    @WithMockUser(username = "vinculumUTF8User")
    @Test
    public void testControllerConvertUpperBoundaryInput() throws Exception {
        String vinculumString = "D\\u0305\\u0305\"";  // vinculum UTF-8 string
        when(conversionService.numberToRomanNumeral(2200000000L)).thenReturn(vinculumString);

        mockMvc.perform(get("/romannumeral?query=2200000000"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.input", is(2200000000L)))
                .andExpect(jsonPath("$.romanResult", is(vinculumString)))
                .andExpect(jsonPath("$.errorInfo", is(nullValue())));
    }
}
