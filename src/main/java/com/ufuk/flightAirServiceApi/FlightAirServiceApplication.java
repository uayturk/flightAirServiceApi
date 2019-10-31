package com.ufuk.flightAirServiceApi;

import com.ufuk.flightAirServiceApi.config.ApplicationConfig;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class FlightAirServiceApplication {

  private final ApplicationConfig applicationConfig;

  public FlightAirServiceApplication(ApplicationConfig applicationConfig) {
    this.applicationConfig = applicationConfig;
  }


  public static void main(String[] args) {
    SpringApplication.run(FlightAirServiceApplication.class,args);
  }

  @Bean
  Docket swaggerSpringMvcPlugin() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .directModelSubstitute(LocalDateTime.class, String.class)
        .directModelSubstitute(LocalDate.class, String.class)
        .select().apis(RequestHandlerSelectors.basePackage("com.ufuk"))
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title(applicationConfig.getApplicationName())
        .description("<i>Current platform: </i><b>" + applicationConfig.getPlatform() + "</b>")
        .version(applicationConfig.getBuildVersion())
        .build();
  }

}
