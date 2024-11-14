package com.jhpark.schedulerdevelop.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class LoginFilter implements Filter {

    private static final String[] FILTER_LIST = {"/", "/users/signup", "/login"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (!isFilterList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);
            if (session == null || session.getAttribute("sessionKey") == null) {
                throw new RuntimeException("로그인 해주세요");
            }
        }

        chain.doFilter(request, response);
    }

    /**
     *
     * @param requestURI
     * @return FILTER_LIST 안에 있는 URI 와 requestURI 가 일치하면 true 불일치하면 false 반환
     */
    private boolean isFilterList(String requestURI) {
        return PatternMatchUtils.simpleMatch(FILTER_LIST, requestURI);
    }
}
