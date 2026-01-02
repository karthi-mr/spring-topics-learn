package com.learn.security.auth;

import com.learn.security.user.Role;
import lombok.Builder;

@Builder
public record RegistrationRequest(
        String firstname,

        String lastname,

        String email,

        String password,

        Role role
) {
}
