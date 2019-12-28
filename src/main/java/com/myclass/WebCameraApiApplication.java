package com.myclass;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
@SpringBootApplication
public class WebCameraApiApplication implements Filter {
	
	
	

	public static void main(String[] args) {
		SpringApplication.run(WebCameraApiApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer configurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**").allowedOrigins("*").allowedMethods("PUT", "GET", "POST", "DELETE")
						.allowCredentials(false).maxAge(3600);
			}
		};
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		
		
		String action = req.getServletPath();

		System.out.println(action);
		// Trường hợp request yêu cầu vào trang login
		if (action.equals("/admin/login")) {

			chain.doFilter(request, response);
			return;
		}

		// Trường hợp client (chưa đăng nhập lần nào) gửi request muốn vào các trang
		// khác mà không thông qua login thì chuyển hướng qua login
		// check login

		if (session.getAttribute("USER_LOGIN") == null) {
			// chuyển hướng về trang đăng nhập
			resp.sendRedirect(req.getContextPath() + "/admin/login?error=123");
			return;
		} else {
			// Trường hợp client (đã đăng nhập) muốn vào các trang khác thì accept
			chain.doFilter(request, response);
		}
		

	}

	
}
