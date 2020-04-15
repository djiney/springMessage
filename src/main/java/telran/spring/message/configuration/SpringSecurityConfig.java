package telran.spring.message.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
	private static final String USER_PASSWORD = "57hvy3958b73";
	private static final String ADMIN_PASSWORD = "4yj95h3y593";

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication()
            .withUser("user").password("{noop}" + USER_PASSWORD).roles("USER")
            .and()
            .withUser("admin").password("{noop}" + ADMIN_PASSWORD).roles("USER", "ADMIN");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
            .httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/rest/send").hasRole("USER")
            .antMatchers(HttpMethod.GET, "/rest/get-types").hasRole("ADMIN")
            .and()
            .csrf().disable()
            .formLogin().disable();
	}

	@Bean
	public UserDetailsService userDetailsService()
	{
		User.UserBuilder users = User.withDefaultPasswordEncoder();

		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

		manager.createUser(
			users.username("user").password(USER_PASSWORD).roles("USER").build()
		);

		manager.createUser(
			users.username("admin").password(ADMIN_PASSWORD).roles("USER", "ADMIN").build()
		);

		return manager;
	}
}