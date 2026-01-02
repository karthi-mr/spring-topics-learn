package com.learn.security.auth;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
        String token
) {
}
