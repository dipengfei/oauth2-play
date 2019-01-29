package me.danielpf.oauth2play.resserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

@RestController("/res")
public class ResController {

    @GetMapping
    public Map<String, String> res(Principal principal) {

        return Collections.singletonMap("principal", principal.getName());
    }
}
