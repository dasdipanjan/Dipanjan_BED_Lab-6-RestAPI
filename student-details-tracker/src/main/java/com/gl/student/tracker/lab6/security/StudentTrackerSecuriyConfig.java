package com.gl.student.tracker.lab6.security;

import com.gl.student.tracker.lab6.services.impl.AppUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * This class is the custom security configuration class. In this class we have defined different role based access and
 * authentication details.
 *
 * @author DIPANJAN DAS(212431882)
 * @version 1.0
 * @since 02-May-2023
 */
@Configuration
@EnableWebSecurity
public class StudentTrackerSecuriyConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    /**
     * Provides the object of {@link UserDetailsService}
     *
     * @return Object of {@link UserDetailsService}
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new AppUserDetailsServiceImpl();
    }

    /**
     * Provides the object of {@link BCryptPasswordEncoder}
     *
     * @return Object of {@link BCryptPasswordEncoder}
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Provides the object of {@link DaoAuthenticationProvider}
     *
     * @return Object of {@link DaoAuthenticationProvider}
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * This method is responsible to configure authentication part of this application.
     *
     * @param auth Object of {@link AuthenticationManagerBuilder}
     * @throws Exception when there is problem.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    /**
     * This method is responsible to define different type of role based access of web pages in Student details tracker application.
     *
     * @param http Object of {@link HttpSecurity} class.
     * @throws Exception throws when there is problem related to access of resources.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/student/update/**", "/student/delete/**").hasAuthority("ADMIN")
                .antMatchers("/", "/student/create", "/student/savestudent", "/student/list", "/student/error").hasAnyAuthority("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginProcessingUrl("/login").successForwardUrl("/student/list").permitAll()
                .and()
                .logout().logoutSuccessUrl("/login").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/student/error")
                .and()
                .cors().and().csrf().disable();
    }


}
