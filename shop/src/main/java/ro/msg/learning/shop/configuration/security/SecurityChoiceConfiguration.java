package ro.msg.learning.shop.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityChoiceConfiguration extends WebSecurityConfigurerAdapter {
    private static final String PRODUCTS_ALL_MATCHER_URL = "/products/**";
    private static final String ORDERS = "/orders";
    private static final String LOGIN = "/login";
    private static final String LOGIN_ERROR = "/login-error";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String PRODUCTS_ROOT_URL = "/products";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    @Value("${security.choice}")
    private ESecurityPicker securityPicker;
    @Autowired
    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        switch (securityPicker) {
            case BASIC_SECURITY:
                implementBasicHttpSecurity(http);
                httpHandlerForFilterAndToken(http);
                break;
            case FORM_SECURITY:
                implementLoginFormSecurity(http);
                httpHandlerForFilterAndToken(http);
                break;
            case NO_SECURITY:
                implementNoSecurity(http);
                break;
            default:
                break;
        }
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser(USER)
                .password(passwordEncoder().encode(PASSWORD))
                .authorities(ROLE_ADMIN);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private void generateCsrfToken(HttpSecurity http) throws Exception {
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

    private void implementNoSecurity(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }

    private void implementLoginFormSecurity(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(PRODUCTS_ALL_MATCHER_URL).authenticated()
                .antMatchers(HttpMethod.POST, ORDERS).authenticated()
                .antMatchers(LOGIN).permitAll()
                .and()
                .formLogin()
                .loginPage(LOGIN)
                .defaultSuccessUrl(PRODUCTS_ROOT_URL, true)
                .failureUrl(LOGIN_ERROR);
    }

    private void implementBasicHttpSecurity(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(PRODUCTS_ALL_MATCHER_URL).authenticated()
                .antMatchers(HttpMethod.POST, ORDERS).authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint);
    }

    private void httpHandlerForFilterAndToken(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
        generateCsrfToken(http);
    }
}
