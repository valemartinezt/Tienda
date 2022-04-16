
package com.tienda;

import com.tienda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

 @Autowired
private UserService userDetailsService;

 @Bean
public BCryptPasswordEncoder passwordEncoder() {
return new BCryptPasswordEncoder();
}

 @Bean
DaoAuthenticationProvider authenticationProvider(){
DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
daoAuthenticationProvider.setUserDetailsService(this.userDetailsService);

 return daoAuthenticationProvider;
}
public SecurityConfig(UserService userPrincipalDetailsService) {
this.userDetailsService = userPrincipalDetailsService;
}

 @Override
protected void configure(AuthenticationManagerBuilder auth) {
auth.authenticationProvider(authenticationProvider());
}
//El siguiente método funciona para hacer la autenticación del usuario
@Override
protected void configure(HttpSecurity http) throws Exception {
http.authorizeRequests()
.antMatchers("/persona")
.hasRole("ADMIN")
.antMatchers("/personasN","/persona")
.hasAnyRole("USER", "VENDEDOR","ADMIN")
.antMatchers("/")
.hasAnyRole("USER", "VENDEDOR", "ADMIN")
.and()
.formLogin()
.loginPage("/")
.loginProcessingUrl("/signin").permitAll();
}
//El siguiente método funciona parsa realizar la autorización de accesos

}