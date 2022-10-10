package com.gfg.movieshark.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/**
 * Till Spring Security 4
 *  to provide the configuration one needs to extends WebSecurityConfigurerAdapter.
 *  and override the configuration of the HttpSecurity , PasswordEncoder and UserDetailsService , AuthorizationManager
 *
 *  After Spring security 5
 *
 *  To provide the configuration one needs to create a bean for FilterChain, PasswordEncoder, UserDetailsService and AuthorityProvider
 *
 * BLOG: https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
 * */

/***
 * Types of authentication
 *
 *  1. username&Password
 *  2. LdapAuthentication
 *  3.JdbcAuthentication
 *  check Authentication class for different types of authentication
 * */



@Configuration
public class SecurityConfiguration {

   /* @Value("${admin.authority}")
    private String ADMIN_AUTH;*/

    private final String ADMIN_AUTH="ADMIN";
    private final String USER_AUTH="USER";

    @Bean
    public PasswordEncoder getMyPasswordEncoder(){
        return  NoOpPasswordEncoder.getInstance();
    }


    /**
     *  Domain will be common for all the type of the users.  api-> localhost:8080/
     *  1. we need to add filter/matchers to map APIs with the authorities
     *  by providing a custom bean of SecurityFilterChain
     *  ex:
     *      /admin/greet -> authority -> ADMIN
     *      /admin/movie/add -> authority -> ADMIN
     *      /admin/movie/get -> authority -> ADMIN
     *      /movie/add -> authority -> ADMIN ( no restriction on  name)
     *
     *      /user/review/add -> authority -> USER
     *      /user/review/get -> authority -> USER
     * */

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
/*
*CSRF -> Cross-Site request forgery
* XSS -> Cross-site Script
*
*
* */

        /**
         * antMatchers will accept the path and regex after it or the direct path for single api
         *Ex: localhost:8080/main/greet
         * following antmatchers will work
         *  /main/greet**
         *  /main/**
         *  **greet**
         *
         * */
        httpSecurity
                .csrf().disable() //***csrf needs to be disabled if directly hitting POST api from unknown sources
                .authorizeRequests()
                //.antMatchers("/movie/add").hasAuthority(ADMIN_AUTH)
                .antMatchers("/**").permitAll();
        return httpSecurity.build();
    }



}


/*
For all the steps here, the default definition already exists in Spring Security

* 1. get the credentials from the user  ///Spring --> AuthenticationManager (authenticate)
* 2. get the creds from the DB /// Spring --> UserDetailsService     /// already done--> sign up first, would username and password  which we store for firstime in DB.
* 3. compare the creds  /// Spring --> passwordEncoder ( BcryptEncoder NoOpPasswordEncoder)
* 4. if Matches then return true or session ID // FilterChain ( basic filtration -> authentication , second -> authorization ++ custom filters)
* 5. else return the invalid creds //FilterChain
*
*
* */
/**
 * Revise oAuth 2.0 -> logging fb,twiiter,github
 *
 * ---Authentication start -----
 * Step1:  register an app with oauth providers
 * Step2:  get client id and secret from the oauth providers
 * Step3:  On login click, app should redirect to the 3rd party auth provider server.
 * Step4:  on success, oauth provider return scope items( user details, emails, username, profilepic, birthdate)
 * Step5:  either store the user details with us in DB( optional )
 * --- Authentication over -----
 *
 * ---Authorization start-----
 * Step6: read a roles from the attributes provided by oatuh or assign a default role.
 * Step7: authorize the apis based on roles
 * --Authorization over------
 *
 * Regular flow.
 *
 * **/

