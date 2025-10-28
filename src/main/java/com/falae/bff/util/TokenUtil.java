package com.falae.bff.util;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;   // ✅ IMPORT CORRETO

@Component
public class TokenUtil {
    public String extractAuthHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return header != null ? header : "";
    }
}
