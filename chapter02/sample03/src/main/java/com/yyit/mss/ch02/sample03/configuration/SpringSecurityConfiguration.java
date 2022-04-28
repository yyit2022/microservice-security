package com.yyit.mss.ch02.sample03.configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author yyit
 **/
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String SECURED_READ_SCOPE = "SCOPE_read";

    private static final String SECURED_WRITE_SCOPE = "SCOPE_write";

    private static final String SECURED_PATTERN_WRITE = "/orders/**";

    private static final String SECURED_PATTERN_READ = "/orders/{id}";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests()
                .antMatchers(SECURED_PATTERN_WRITE).hasAuthority(SECURED_WRITE_SCOPE)
                .antMatchers(SECURED_PATTERN_READ).hasAuthority(SECURED_READ_SCOPE)
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer().jwt();
        // @formatter:on
    }

}
