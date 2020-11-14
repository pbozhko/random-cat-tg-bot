// package by.bozhko.tg.bot.config;
//
// import by.bozhko.tg.bot.security.AuthProviderService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.boot.autoconfigure.security.SecurityProperties;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
// @Configuration
// @EnableWebSecurity
// @RequiredArgsConstructor
// public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//     private final AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;
//     private final AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;
//     private final AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;
//     private final Http401UnauthorizedEntryPoint authenticationEntryPoint;
//     private final AuthProviderService authProvider;
//     private final SecurityProperties securityProperties;
//     private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
//
//     @Override
//     protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
//
//         authenticationManagerBuilder.authenticationProvider(authProvider);
//     }
//
//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         String[] permited = new String[securityProperties.getIgnored().size()];
//         securityProperties.getIgnored().toArray(permited);
//
//         http
//             .csrf().disable()
//             .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
//             .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//             .authorizeRequests()
//             .antMatchers("/api/authenticate").permitAll()
//             .antMatchers("/api/user").permitAll()
//             .antMatchers("/").permitAll()
//             .antMatchers("/favicon.ico").permitAll()
//             .anyRequest().authenticated()
//             .and()
//             .formLogin()
//             .loginProcessingUrl("/api/authentication")
//             .successHandler(ajaxAuthenticationSuccessHandler)
//             .failureHandler(ajaxAuthenticationFailureHandler)
//             .usernameParameter("username")
//             .passwordParameter("password")
//             .and()
//             .logout()
//             .logoutUrl("/api/logout")
//             .logoutSuccessHandler(ajaxLogoutSuccessHandler)
//             .invalidateHttpSession(true)
//             .deleteCookies("JSESSIONID");
//
//         http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
//         http.headers().cacheControl();
//     }
//
//     @Bean
//     public ShaPasswordEncoder sha() {
//
//         return new ShaPasswordEncoder(256);
//     }
// }
