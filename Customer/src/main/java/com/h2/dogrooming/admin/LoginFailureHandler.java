package com.h2.dogrooming.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        if (exception instanceof AuthenticationServiceException) {
            request.setAttribute("LoginFailMessage", "존재하지 않는 사용자입니다.");

        } else if(exception instanceof BadCredentialsException) {
            request.setAttribute("LoginFailMessage", "아이디 혹은 비밀번호가 틀립니다.");

        } else if(exception instanceof LockedException) {
            request.setAttribute("LoginFailMessage", "잠긴 계정입니다.");

        } else if(exception instanceof DisabledException) {
            request.setAttribute("LoginFailMessage", "비활성화된 계정입니다.");

        } else if(exception instanceof AccountExpiredException) {
            request.setAttribute("LoginFailMessage", "만료된 계정입니다.");

        } else if(exception instanceof CredentialsExpiredException) {
            request.setAttribute("LoginFailMessage", "비밀번호가 만료되었습니다.");
        }

        // 로그인 페이지로 다시 포워딩
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
        dispatcher.forward(request, response);
    }
}
