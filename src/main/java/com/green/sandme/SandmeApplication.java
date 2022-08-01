package com.green.sandme;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity //시큐리티 필터 작동  
@SpringBootApplication
public class SandmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SandmeApplication.class, args);
	}
	
	@Bean
    SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        
        Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/*.xml");
        sessionFactory.setMapperLocations(res);
        
        return sessionFactory.getObject();
    }
	
	//비밀번호 해시처리(시큐리티는 비밀번호를 해시처리하지 않으면 가입불가)  
	@Bean 
	BCryptPasswordEncoder encoderPassword() { 
		return new BCryptPasswordEncoder(); 
	}
	  
	@Bean 
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 
		http.cors() //교차 출처 리소스 공유
			.configurationSource(corsConfigurationSource())
			.and().csrf().disable() //취약점 막기
			.authorizeRequests() //페이지 권한 설정
			.anyRequest().permitAll() //특정 권한 사용자만 접근가능한 리소스를 설정 그 외 나머지는 인증후 접근 가능
			.and()  //로그인 페이지와 기타 로그인 처리 및 성공 실패 처리를 사용 
			.formLogin() //따로 만든 로그인 페이지 사용할때 미설정시 시큐리티가 기본으로 제공하는 페이지 
			.loginPage("/loginForm")
			.usernameParameter("memberEmail") //로그인할 아이디
			.passwordParameter("memberPwd") //로그인할 비밀번호
			.loginProcessingUrl("/loginSuccess") //로그인폼 액션값과 일치
			.defaultSuccessUrl("/") //로그인이 성공했을 때 이동되는 페이지
			.failureUrl("/loginForm") //로그인이 실패했을 때 이동되는 페이지
			.permitAll()
			.and() 
			.logout() 
			.logoutUrl("/logout") //로그아웃
			.logoutSuccessUrl("/") //로그아웃 성공시 이동되는 페이지
			.invalidateHttpSession(true); //Http세션 초기화
		return http.build();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		
		configuration.addAllowedOrigin("*");
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		configuration.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
	}
}