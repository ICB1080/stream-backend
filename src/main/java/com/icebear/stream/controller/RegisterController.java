package com.icebear.stream.controller;

import com.icebear.stream.entity.db.User;
import com.icebear.stream.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@RequestBody User user, HttpServletResponse response) throws IOException {
        if (!registerService.register(user)) {
            // fail to register, return 409
            // (if success, automatically return 200)
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }
}
