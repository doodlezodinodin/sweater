package ua.nure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //Авторизация
                    .antMatchers("/").permitAll() //Для этого пути разрешен полный доступ
                    .anyRequest().authenticated() //Для всех остальных требуем авторизацию
                .and()
                    .formLogin() //Включаем форм логин
                    .loginPage("/login") //страница логина находится на таком мэпинге
                    .permitAll() //разрещаем пользоваться этим всем
                .and()
                    .logout() //включаем logout
                    .permitAll(); //разрещаем пользоваться этим всем
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() { //Этот метод создает в памяти Мэнеджер который обслуживает записи учетные
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("u")
                        .password("p")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}