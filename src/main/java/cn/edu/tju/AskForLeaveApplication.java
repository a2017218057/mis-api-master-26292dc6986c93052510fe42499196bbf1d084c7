package cn.edu.tju;
import cn.edu.tju.dao.LeaveAppRepo;
import cn.edu.tju.dao.StaffRepo;
import cn.edu.tju.dao.UserRepo;
import cn.edu.tju.filter.CorsFilter;
import cn.edu.tju.model.LeaveApplication;
import cn.edu.tju.model.Staff;
import cn.edu.tju.model.User;
import org.joda.time.DateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.google.gson.Gson;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;

@SpringBootApplication
public class AskForLeaveApplication{

	public static void main(String[] args) {
		SpringApplication.run(AskForLeaveApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner demo(UserRepo userRepo, StaffRepo staffRepo, LeaveAppRepo leaveAppRepo) {
//		return (args) -> {
//
//			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//			String password = passwordEncoder.encode("123456");
//			userRepo.save(new User("Jack", password));
//			userRepo.save(new User("Alice", password));
//
//			int[] leaveDetail = new int[3660];
//			leaveDetail[0] = 0;	// leaveDetail[0] is the day 2016-01-01
//
//			for (int i = 2; i < leaveDetail.length; i += 7) {
//				leaveDetail[i] = 9;
//				leaveDetail[i-1] = 9;
//			}
//			if ((leaveDetail.length - 1) % 7 == 2)	leaveDetail[leaveDetail.length - 1] = 9;
//
//			leaveDetail[5] = 1;
//			leaveDetail[6] = 1;
//
//			Gson gson = new Gson();
//			String leaveDetailJS = gson.toJson(leaveDetail);
//
//			staffRepo.save(new Staff("Jack", "Jack", 1,12, 10, "dev", "Alice", "Alice", "" + leaveDetailJS));
//			staffRepo.save(new Staff("Alice", "Alice", 2,12, 10, "dev", "Bob", "Bob", "" + leaveDetailJS));
//
//		};
//	}

	@Bean
	public FilterRegistrationBean testFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new CorsFilter());
		registration.addUrlPatterns("/*");
		registration.addInitParameter("paramName", "paramValue");
		registration.setName("CorsFilter");
		registration.setOrder(1);
		return registration;
	}
	/*@Bean
	public EmbeddedServletContainerFactory servletContainerFactory() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				securityConstraint.addCollection(collection);
				context.addConstraint(securityConstraint);
			}
		};
		tomcat.addAdditionalTomcatConnectors(connector());
		return tomcat;
	}
	@Bean
	public Connector connector(){
		Connector connector=new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(8080);
		connector.setSecure(false);
		connector.setRedirectPort(8443);
		return connector;
	}*/



}
