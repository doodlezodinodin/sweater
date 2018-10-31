package ua.nure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import ua.nure.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Autowired
    public WebSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //Авторизация
                    .antMatchers("/", "/registration", "/static/**").permitAll() //Для этого пути разрешен полный доступ
                    .anyRequest().authenticated() //Для всех остальных требуем авторизацию
                .and()
                    .formLogin() //Включаем форм логин
                    .loginPage("/login") //страница логина находится на таком мэпинге
                    .permitAll() //разрещаем пользоваться этим всем
                .and()
                    .logout() //включаем logout
                    .permitAll(); //разрещаем пользоваться этим всем
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance()); //Шифрование паролей

    }
}