/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ca.amextra.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GatewayConfig {

	@Autowired
	private JwtAuthenticationFilter filter;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes().route("catalogos", r -> r.path("/catalogos/**").filters(f -> f.filter(filter)).uri("http://127.0.0.1:9020/"))
				.route("agenda", r -> r.path("/agenda/**").filters(f -> f.filter(filter)).uri("http://127.0.0.1:9021/"))
				.route("login", r -> r.path("/login/**").filters(f -> f.filter(filter)).uri("http://127.0.0.1:9000/"))
				.route("clientes", r -> r.path("/clientes/**").filters(f -> f.filter(filter)).uri("http://127.0.0.1:9021/"))
				.route("sms", r -> r.path("/envio/**").filters(f -> f.filter(filter)).uri("http://127.0.0.1:9022/"))
				.route("curp", r -> r.path("/valida/**").filters(f -> f.filter(filter)).uri("http://127.0.0.1:9002/"))
				.route("ocrine", r -> r.path("/ocr/**").filters(f -> f.filter(filter)).uri("http://127.0.0.1:9005/"))
				.route("solicitud", r -> r.path("/solicitud/**").filters(f -> f.filter(filter)).uri("http://127.0.0.1:9023/"))
				.route("consultacp", r -> r.path("/servicio/consultaCP/**").filters(f -> f.filter(filter)).uri("http://127.0.0.1:9012/")).build();
	}

}
