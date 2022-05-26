package com.icebear.stream.entity.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequestBody {
    private final String userId;
    private final String password;

    @JsonCreator
    // JsonCreator: convert a JSON to a class object
    public LoginRequestBody(@JsonProperty("user_id") String userId,
                            @JsonProperty("password") String password) {
        // when we log in, we send two information: user_id, password.
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}

