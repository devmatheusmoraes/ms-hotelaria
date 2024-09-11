package br.com.infnet.hoteis.disponibilidade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DisponibilidadeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisponibilidadeApplication.class, args);
	}

}
