package productinventoryAPI;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class ProductInventorySecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		//setting our authentication
		auth.inMemoryAuthentication().withUser("pradeep").password("pradeep").roles("TRAINEE")
		.and()
		.withUser("pradeep1").password("pradeep1").roles("ASSOCIATE")
		.and()
		.withUser("pradeep2").password("pradeep2").roles("ADMIN");
	
	}
	
	@Bean 
	public PasswordEncoder getPasswordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("addProduct/").hasAnyRole("ADMIN")
		.antMatchers("updateProduct/").hasAnyRole("ADMIN")
		.antMatchers("deleteProduct").hasRole("ADMIN")
		.antMatchers("/product/*/*", "/product/*/*/*","/product/*").hasAnyRole("ASSOCIATE", "ADMIN")
		.antMatchers("/product/list").hasAnyRole("TRAINEE","ASSOCIATE","ADMIN")
		.antMatchers("/home").permitAll()
		.and().httpBasic();
	}
	
	
	
}
