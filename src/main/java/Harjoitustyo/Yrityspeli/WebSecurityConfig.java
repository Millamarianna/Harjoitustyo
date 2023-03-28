package Harjoitustyo.Yrityspeli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import Harjoitustyo.Yrityspeli.service.UserDetailServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig  {
	
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
	private static final AntPathRequestMatcher[] WHITE_LIST_URLS = {
            new AntPathRequestMatcher("/api/**"),
            new AntPathRequestMatcher("/h2-console/**"),
			new AntPathRequestMatcher("/data/**"),
            new AntPathRequestMatcher("/css/**"),
            new AntPathRequestMatcher("/signup/**"),
            new AntPathRequestMatcher("/saveuser/**")
    };
	
	private static final AntPathRequestMatcher[] USER_LIST_URLS = {
            new AntPathRequestMatcher("/data/**"),
            new AntPathRequestMatcher("/company/**")
    };
	
	private static final AntPathRequestMatcher[] ADMIN_LIST_URLS = {
			new AntPathRequestMatcher("/data/**"),
            new AntPathRequestMatcher("/company/**")
    };
	
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	.requestMatchers(ADMIN_LIST_URLS).hasAuthority("ADMIN")
        	.requestMatchers(USER_LIST_URLS).hasAuthority("USER")
        	.requestMatchers(WHITE_LIST_URLS).permitAll() 
        	.anyRequest().authenticated()
        	.and()
        	.headers().frameOptions().disable()
        	.and()
      .formLogin()
      	  .loginPage("/login")
          .defaultSuccessUrl("/index", true)
          .permitAll()
          .and()
      .logout()
          .permitAll()
          .and()
      .httpBasic();
        
        http.cors().and().csrf().disable();
          
      return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
