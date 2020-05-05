package pl.coderslab.drivertips;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.RedirectViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackages = "pl.coderslab.drivertips.*")
public class DriverTipsApplication implements WebMvcConfigurer {

	@Override
	public void addViewControllers (ViewControllerRegistry registry) {
		RedirectViewControllerRegistration r =
				registry.addRedirectViewController("/", "/swagger-ui.html");
	}

	public static void main(String[] args) {
		SpringApplication.run(DriverTipsApplication.class, args);
	}

}
