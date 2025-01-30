package dev.agitrubard.contact;

import dev.agitrubard.contact.model.response.CustomErrorResponse;
import dev.agitrubard.contact.model.response.CustomSuccessResponse;
import dev.agitrubard.contact.util.CustomMockResultMatchersBuilders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@Component
@RequiredArgsConstructor
public class CustomMockMvc {

    private final MockMvc mockMvc;

    public ResultActions perform(final MockHttpServletRequestBuilder mockHttpServletRequestBuilder,
                                 final CustomSuccessResponse<?> mockResponse) throws Exception {

        return mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(CustomMockResultMatchersBuilders.time()
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.isSuccess()
                        .isBoolean())
                .andExpect(CustomMockResultMatchersBuilders.isSuccess()
                        .value(mockResponse.getIsSuccess()));
    }

    public ResultActions perform(final MockHttpServletRequestBuilder mockHttpServletRequestBuilder,
                                 final CustomErrorResponse mockErrorResponse) throws Exception {

        return mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(CustomMockResultMatchersBuilders.time()
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.isSuccess()
                        .isBoolean())
                .andExpect(CustomMockResultMatchersBuilders.isSuccess()
                        .value(mockErrorResponse.getIsSuccess()))
                .andExpect(CustomMockResultMatchersBuilders.header()
                        .isString())
                .andExpect(CustomMockResultMatchersBuilders.header()
                        .value(mockErrorResponse.getHeader()))
                .andExpect(CustomMockResultMatchersBuilders.content()
                        .doesNotHaveJsonPath());
    }

}
