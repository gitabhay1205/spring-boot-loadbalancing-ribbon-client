package com.boot.lodbalanced;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;

public class RibbonConfiguration {
	
	@Autowired
	IClientConfig ribbonClientConfig;
	
	 @Bean
	 public IRule ribbonRule(IClientConfig clientConfig) {
	        return new AvailabilityFilteringRule();
	    }
	 
	 //This class is not necessary to right, if you remove this class than also code will work
	 //fine, it is just to set rules like AvailabilityFilteringRule, RoundRobinRule, WeightedResponseTimeRule;

}
