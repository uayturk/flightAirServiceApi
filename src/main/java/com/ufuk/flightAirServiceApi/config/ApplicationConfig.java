package com.ufuk.flightAirServiceApi.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Keeps the application configuration read from resources/application.properties.
 * <p>Uses lombok to generate getters/setters and ToString</p>
 */
@Component
@Getter
@Setter
@ToString
public class ApplicationConfig {

  @Value("${application.name}")
  private String applicationName;

  @Value("${build.version}")
  private String buildVersion;

  @Value("${platform}")
  private String platform;

  //@Value("${cors.allowed.origins:http://localhost:8085}") diyerek default bir değer de atanabilir.
  // :http://localhost:8085 iki noktadan sonrası default olarak veri atamak oluyor. Default atanmadan çalışmıyor,default http://localhost:8086 gibi bile olsa çalışıyor
  // yani herhangi birşey atanabilinir default'a. application.properties'de izin verilen http://localhost:8085 olması ,bu porttan gelenler için izinli. Fakat,
  // application.properties değişirse artık http://localhost:8085 request atamaz.
  @Value("${cors.allowed.origins:http://localhost:8086}") //We put the here allowed origin. (Buraya request atmasına izin vereceğimiz kaynağı koyduk ve configden okuyacagız)
  private String[] corsAllowedOrigins;

}