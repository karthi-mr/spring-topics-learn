package com.learn.security.demo;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin")
public class AdminController {

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("GET:: admin controller");
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    @Hidden
    public ResponseEntity<String> post() {
        return ResponseEntity.ok("POST:: admin controller");
    }

    @PutMapping
    @PreAuthorize("hasAuthority('admin:update')")
    @Hidden
    public ResponseEntity<String> put() {
        return ResponseEntity.ok("PUT:: admin controller");
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:delete')")
    @Hidden
    public ResponseEntity<String> delete() {
        return ResponseEntity.ok("DELETE:: admin controller");
    }
}
