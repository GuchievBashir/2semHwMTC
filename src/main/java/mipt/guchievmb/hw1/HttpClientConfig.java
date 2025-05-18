package mipt.guchievmb.hw1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class HttpClientConfig {

  @Bean
  public RestTemplate restTemplate() {
    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    factory.setConnectTimeout(5_000); // 5 сек на установку соединения
    factory.setReadTimeout(5_000);    // 5 сек на получение ответа
    return new RestTemplate(factory);
  }

  @Bean
  public WebClient webClient(WebClient.Builder builder) {
    return builder
            .build();
  }
}

