package xyz.hlafaille.seizuretracker.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;

import java.io.IOException;

public class InterceptorRestResponseBuilder {
    /**
     * Populates an HttpServletResponse with the required information to make it a "REST" response
     * @param servletResponse HttpServletResponse from the controller method
     * @param responseDto Populated instance of T to use for response payload
     */
    public static <T> void populateServletResponseAsRest(@NonNull HttpServletResponse servletResponse, T responseDto, @NonNull Integer statusCode) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        servletResponse.setHeader("content-type", "application/json");
        servletResponse.setStatus(statusCode);
        servletResponse.getWriter().write(mapper.writeValueAsString(responseDto));
    }

}
