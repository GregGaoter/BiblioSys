package com.dsi.bibliosys.biblioback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EntityScan("com.dsi.bibliosys.biblioback.data.entity")
public class BiblioBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiblioBackApplication.class, args);
	}

}
