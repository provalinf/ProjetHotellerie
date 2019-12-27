package m2.info.config;

import m2.info.models.user.Authorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/admin/**").hasAuthority(Authorities.ADMIN.name())
                .antMatchers("/student/**").hasAuthority(Authorities.STUDENT.name())
                .antMatchers("/teacher/**").hasAuthority(Authorities.TEACHER.name())
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler(new AuthSuccessHandler());
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
        auth.inMemoryAuthentication().withUser("admin").password("mdp_admin").authorities(Authorities.ADMIN.name());
    }
}
