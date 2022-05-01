package admin.config.auth;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import admin.config.auth.custom.CustomOAuth2UserService;
import admin.domain.user.Role;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomOAuth2UserService customOAuth2UserService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.headers().frameOptions().disable()// for h2-console
			.and()
				.authorizeRequests() // to use antMatchers
				.antMatchers("/","/h2-console/**","/profile/","/css/**", "/images/**", "/js/**", "/a").permitAll()
				.antMatchers("/api/v1/**").hasRole(Role.USER.name()) // only User
				.anyRequest().authenticated()
			.and()
				.logout()
				.logoutSuccessUrl("/")
			.and()
				.oauth2Login()
					.userInfoEndpoint()
						.userService(customOAuth2UserService);
	}
}
