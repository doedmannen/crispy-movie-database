package com.example.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class MyWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailService myUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and()

                .authorizeRequests()

                .antMatchers(HttpMethod.GET, "/api/movies/**").permitAll()

                .antMatchers("/api/**").hasRole("USER")

                .antMatchers("/**").permitAll()

                .and().formLogin().permitAll().defaultSuccessUrl("/", true)
                .and().logout().permitAll().logoutSuccessUrl("/")
                .and().csrf().disable()
        ;

//        http.authorizeRequests().antMatchers("/api/**").hasRole("USER").and().formLogin();

//        http.authorizeRequests()
//                .antMatchers(HttpMethod.GET,"/api/users/*").hasRole("USER")
//                .antMatchers(HttpMethod.GET,"/api/**").permitAll()
//                .antMatchers("/api/**").hasRole("USER")
//                .and().formLogin();

        // Anything that does not start with /api
//                .regexMatchers("^(?!\/api\/).+").hasRole("USER")
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(myUserDetailsService)
                .passwordEncoder(myUserDetailsService.getEncoder());
    }
}