package com.thoughtworks.agenciacompromisso.settings;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ChannelSecurityConfigurer;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${settings.app.environment}")
    private String environment;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        ChannelSecurityConfigurer<HttpSecurity>.RequiresChannelUrl requiresChannelUrl = httpSecurity
                .requiresChannel()
                .antMatchers("/**");

        httpSecurity
                .cors()
                .and()
                .csrf()
                .disable();

        if (environment.equals("production")) {
            requiresChannelUrl.requiresSecure();
        } else {
            requiresChannelUrl.requiresInsecure();
        }
    }
}