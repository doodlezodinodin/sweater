package ua.nure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Autowired
    public WebSecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //Авторизация
                    .antMatchers("/", "/registration").permitAll() //Для этого пути разрешен полный доступ
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
        auth.jdbcAuthentication()
                .dataSource(dataSource) //Для того чтоб менджер мог входить в БД и искать пользователей и их роли
                .passwordEncoder(NoOpPasswordEncoder.getInstance()) //Шифрование паролей
                .usersByUsernameQuery("select username, password, active from usr where username=?")
                .authoritiesByUsernameQuery("select u.username, ur.roles from usr u inner join user_role ur on u.id = ur.user_id where u.username=?");
    }
}