package com.learn.security.auth;

import lombok.Builder;

@Builder
public record RegistrationRequest(
        String firstname,

        String lastname,

        String email,

        String password
) {
}
