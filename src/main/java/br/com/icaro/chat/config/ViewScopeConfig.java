package br.com.icaro.chat.config;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ViewScopeConfig {

    @Bean
    public static CustomScopeConfigurer customScopeConfigurer() {
        Map<String, Object> scopes = new HashMap<>();
        scopes.put("view", new ViewScope());

        CustomScopeConfigurer customScopeConfigurer = new CustomScopeConfigurer();
        customScopeConfigurer.setScopes(scopes);

        return customScopeConfigurer;
    }
}