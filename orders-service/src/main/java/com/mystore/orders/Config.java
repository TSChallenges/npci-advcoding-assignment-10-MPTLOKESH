package com.mystore.orders;

public class Config {

    // TODO: YOUR CODE HERE to create a Load Balanced Bean for RestTemplate
     @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
