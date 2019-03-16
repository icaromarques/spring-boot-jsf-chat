package br.com.icaro.chat.config;

import java.security.Principal;

public final class User implements Principal {

    private final String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}