package br.com.picpay.mscontacts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.extern.slf4j.Slf4j;


@SpringBootApplication
@EntityScan(basePackages = "br.com.picpay.mscontacts.entities")
public class MsContactsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsContactsApplication.class, args);
	}

}
