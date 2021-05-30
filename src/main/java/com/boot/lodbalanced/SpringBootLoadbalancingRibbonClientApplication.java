package com.boot.lodbalanced;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@RibbonClient(name="clientService", configuration=RibbonConfiguration.class)
public class SpringBootLoadbalancingRibbonClientApplication {
	
	@Autowired
	private RestTemplate template;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLoadbalancingRibbonClientApplication.class, args);
	}
	
	@RequestMapping(value = "/")
    public String home() {
      return "home";
    }
	
	@GetMapping("/client-app")
	public String callService()
	{
		// /service is endpoint of spring-boot-loadbalanced-service application 
		// clientService is Ribbon client application for this service, see application.properties
		String url = "http://clientService/service";
		return template.getForObject(url, String.class); 
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}

}
