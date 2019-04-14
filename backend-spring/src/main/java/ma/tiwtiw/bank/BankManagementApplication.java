package ma.tiwtiw.bank;

import ma.tiwtiw.bank.service.RightService;
import ma.tiwtiw.bank.service.RoleService;
import ma.tiwtiw.bank.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class BankManagementApplication extends SpringBootServletInitializer implements
    CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(BankManagementApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(BankManagementApplication.class);
  }

  @Override
  public void run(String... args) {
  }
}
