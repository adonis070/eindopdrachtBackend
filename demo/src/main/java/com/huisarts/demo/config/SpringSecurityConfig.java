package com.huisarts.demo.config;

import com.huisarts.demo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public CustomUserDetailsService customUserDetailsService;


    //Hier vertellen we dat de AuthenticationManagerBuilder auth gebruikt moet maken van mijn customUserDetailsService
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }


    //@Bean is een springframework import die ervoor zorgt dat de methode als een bean wordt verwerkt en teruggestuurd als een bean value
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /** met deze configure methode wordt de parameter HttpSecurity http meegegeven
     *  hierbij is http een object van de klasse HttpSecurity
     * HttpSecurity is een import van het spring security framework
     *  in deze methode wordt de http security geconfigureerd met verschillended methodes
     *  hier word de sessie management bepaald exceptie handelingen
     * ook worden verschillende paths geauthorizeerd
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/admin/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/authenticated/**").authenticated()
                .antMatchers(HttpMethod.GET, "/users/**").hasRole("USER") // per user authorization in UserService
                .anyRequest().permitAll()
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

}
