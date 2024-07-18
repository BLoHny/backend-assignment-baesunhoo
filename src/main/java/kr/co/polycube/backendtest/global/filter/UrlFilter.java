package kr.co.polycube.backendtest.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
public class UrlFilter implements Filter {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Pattern DISALLOWED_CHARACTERS = Pattern.compile("[^a-zA-Z0-9?&=:/.]");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        String queryString = httpRequest.getQueryString();

        Map<String, Object> errorMessage = new HashMap<>();
        errorMessage.put("reason", "URL에 부적절한 요소가 감지 되었습니다.");

        if (containsSpecialCharacters(requestURI) || (queryString != null && containsSpecialCharacters(queryString))) {
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpResponse.setContentType("application/json");
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.getWriter().write(objectMapper.writeValueAsString(errorMessage));
            httpResponse.getWriter().flush();
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean containsSpecialCharacters(String input) {
        return DISALLOWED_CHARACTERS.matcher(input).find();
    }
}
