package BADA_dom_kultury.SpringApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(getPasswordEncoder().encode("user"))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(getPasswordEncoder().encode("admin"))
                .roles("ADMIN")
                .and()
                .withUser("worker") // Przykładowe konto pracownika
                .password(getPasswordEncoder().encode("worker"))
                .roles("WORKER");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/index", "/events", "/register").permitAll()
                .antMatchers("/resources/static/**").permitAll()
                .antMatchers("/main").authenticated()
                .antMatchers("/main_admin").access("hasRole('ADMIN')")
                .antMatchers("/main_worker").access("hasAnyRole('WORKER', 'ADMIN')")
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .antMatchers("/worker/**").access("hasAnyRole('WORKER', 'ADMIN')")
                .antMatchers("/manage-events").access("hasAnyRole('WORKER', 'ADMIN')")
                .antMatchers("/main_user").access("hasRole('USER')")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/main")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/index")
                .logoutSuccessUrl("/index")
                .permitAll();
    }
}
