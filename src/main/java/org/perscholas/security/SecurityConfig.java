package org.perscholas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final AppUserDetailsService appUserDetailsService;

  //    public DaoAuthenticationProvider daoAuthenticationProvider

  public SecurityConfig(AppUserDetailsService appUserDetailsService) {

    this.appUserDetailsService = appUserDetailsService;
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {

    httpSecurity
        .csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/console/**")
        .hasAuthority("ROLE_ADMIN")
        .antMatchers("/", "/home")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/")
        .usernameParameter("email")
        .passwordParameter("password")
        .loginProcessingUrl("/login")
        .defaultSuccessUrl("/register")
        .failureUrl("/home?error=true")
        .permitAll()
        .and()
        .logout()
        .invalidateHttpSession(true)
        .clearAuthentication(true)
        .deleteCookies()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/")
        .permitAll();
  }

  @Bean
  public PasswordEncoder encoder() {

    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.userDetailsService(appUserDetailsService).passwordEncoder(encoder());
  }

  @Override
  public void configure(WebSecurity webSecurity) throws Exception {

    webSecurity
        .ignoring()
        .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/imgs/**");
  }
}
