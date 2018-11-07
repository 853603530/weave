package com.gl.weave;



import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**");
        web.ignoring().antMatchers("/images/**");
        web.ignoring().antMatchers("/sockjs/websocket/**");
        web.ignoring().antMatchers("/sockjs/websocket/**");
        web.ignoring().antMatchers("/client/**");
        
        web.ignoring().antMatchers("/webfront/login");	//配置访问该页面不会自动跳转到后台登录页面中
        web.ignoring().antMatchers("/webfront/results");
        web.ignoring().antMatchers("/webfront/password");
        web.ignoring().antMatchers("/webfront/**");	//配置访问该目录下的所有页面不会自动跳转到后台登录页面中
        web.ignoring().antMatchers("/front/**");	//配置controller下以/front/开头的所有方法不会被拦截
        web.ignoring().antMatchers("/project/**");	//配置controller下以/project/开头的所有方法不会被拦截
       
   }
    
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                        .antMatchers("/js/**").permitAll()
                        .antMatchers("/css/**").permitAll()
                        .antMatchers("/bootstrap/**").permitAll()
                        .antMatchers("/fonts/**").permitAll()
                        .antMatchers("/favicon.ico").permitAll()
                        .anyRequest().authenticated() //4
                        .and()
                        .formLogin()
                            .loginPage("/login")
                            .defaultSuccessUrl("/word")
                            .failureUrl("/login?error")
                            .permitAll() //登陆页面可任意访问
                        .and()
                        .logout().permitAll(); //注销页面可任意访问



    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");//注册访问/login转向login.html页面
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder().username("admin").password("123").roles("USER").build());
        return manager;
    }

    
    /*@Configuration
    protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
    	@Autowired
    	private SysUserMapper sysUserMapper;
    	
    	@Autowired
    	UserDetailService userDetailService;
    	
        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
        	auth.userDetailsService(userDetailService);
        	auth
            .inMemoryAuthentication()
            .withUser(sysUser.getLoginName()).password(sysUser.getPassword()).roles(sysUser.getUserName());
			

        }

    }*/
}