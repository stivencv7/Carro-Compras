package org.evelasco.service;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface ServiceLogin {
    Optional<String>username(HttpServletRequest request);
}
