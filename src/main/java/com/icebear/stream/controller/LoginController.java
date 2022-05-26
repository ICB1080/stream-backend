package com.icebear.stream.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icebear.stream.entity.request.LoginRequestBody;
import com.icebear.stream.entity.response.LoginResponseBody;
import com.icebear.stream.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@RequestBody LoginRequestBody requestBody, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // check if userId and password can log in successfully
        String firstname = loginService.verifyLogin(requestBody.getUserId(), requestBody.getPassword());

        // if user ID and password are correct (session-based authentication)
        if (!firstname.isEmpty()) {
            // create a new session,
            HttpSession session = request.getSession();
            // put user ID as an attribute into the session object,
            session.setAttribute("user_id", requestBody.getUserId());
            // set the expiration time to 600 seconds.
            session.setMaxInactiveInterval(600);

            LoginResponseBody loginResponseBody = new LoginResponseBody(requestBody.getUserId(), firstname);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print(new ObjectMapper().writeValueAsString(loginResponseBody));
        } else {
            // otherwise, return Unauthorized error.
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}

