package telran.spring.message.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication()
            .withUser("user").password("{noop}" + System.getenv("UPWD")).roles("USER")
            .and()
            .withUser("admin").password("{noop}" + System.getenv("APWD")).roles("USER", "ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
            .httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/rest/send").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/rest/get-types").authenticated()
            .and()
            .csrf().disable()
            .formLogin().disable()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		;
	}

	@Bean
	public UserDetailsService userDetailsService()
	{
		User.UserBuilder users = User.withDefaultPasswordEncoder();

		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

		manager.createUser(
			users.username("user").password(System.getenv("UPWD")).roles("USER").build()
		);

		manager.createUser(
			users.username("admin").password(System.getenv("APWD")).roles("USER", "ADMIN").build()
		);

		return manager;
	}
}