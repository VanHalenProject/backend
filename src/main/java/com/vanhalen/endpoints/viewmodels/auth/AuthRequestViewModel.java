package com.vanhalen.endpoints.viewmodels.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestViewModel {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;
}
