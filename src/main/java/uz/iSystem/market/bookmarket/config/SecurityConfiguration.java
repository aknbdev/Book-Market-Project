package uz.iSystem.market.bookmarket.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.iSystem.market.bookmarket.security.AuthEntryPoint;
import uz.iSystem.market.bookmarket.security.CustomUserDetailsService;
import uz.iSystem.market.bookmarket.security.JwtTokenFilter;


@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)

@Configuration @EnableWebSecurity

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;
    private final AuthEntryPoint authEntryPoint;
    private final JwtTokenFilter jwtTokenFilter;

    public SecurityConfiguration(CustomUserDetailsService customUserDetailsService, AuthEntryPoint authEntryPoint, JwtTokenFilter jwtTokenFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.authEntryPoint = authEntryPoint;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable();

        http.authorizeRequests()
                .antMatchers("api/**").permitAll()
                .antMatchers("user/**").hasRole("USER")
                .antMatchers("admin/**").hasRole("ADMIN")
                .anyRequest().permitAll();
        http.exceptionHandling().authenticationEntryPoint(authEntryPoint);

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}
