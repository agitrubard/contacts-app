package dev.agitrubard.report.util;

import lombok.experimental.UtilityClass;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

@UtilityClass
public class CustomMockResultMatchersBuilders {

    public static StatusResultMatchers status() {
        return MockMvcResultMatchers.status();
    }

    public static JsonPathResultMatchers time() {
        return MockMvcResultMatchers.jsonPath("$.time");
    }

    public static JsonPathResultMatchers isSuccess() {
        return MockMvcResultMatchers.jsonPath("$.isSuccess");
    }

    public static JsonPathResultMatchers header() {
        return MockMvcResultMatchers.jsonPath("$.header");
    }

    public static JsonPathResultMatchers content() {
        return MockMvcResultMatchers.jsonPath("$.content");
    }

    public static JsonPathResultMatchers content(String path) {
        return MockMvcResultMatchers.jsonPath("$.content.".concat(path));
    }

    public static JsonPathResultMatchers content(int index, String path) {
        return MockMvcResultMatchers.jsonPath("$.content[" + index + "].".concat(path));
    }

    public static JsonPathResultMatchers contentSize() {
        return MockMvcResultMatchers.jsonPath("$.content.size()");
    }

    public static JsonPathResultMatchers contents(String path) {
        return MockMvcResultMatchers.jsonPath("$.content[*].".concat(path));
    }

}
