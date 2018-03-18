package io.github.cepr0.demo;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import java.sql.SQLException;

import static io.github.cepr0.demo.Gender.FEMALE;
import static io.github.cepr0.demo.Gender.MALE;
import static io.github.cepr0.demo.Role.ADMIN;
import static io.github.cepr0.demo.Role.USER;
import static java.util.Arrays.asList;

@SpringBootApplication
public class Application {

	private final ParentRepo parentRepo;

	public Application(ParentRepo parentRepo) {
		this.parentRepo = parentRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server h2Server() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
	}

	@EventListener
	public void onReady(ApplicationReadyEvent e) {
		parentRepo.saveAll(asList(
				new Parent("parent1", MALE, USER, ADMIN),
				new Parent("parent2", MALE, USER, ADMIN),
				new Parent("parent3", FEMALE, USER, ADMIN),
				new Parent("parent4", FEMALE, USER, ADMIN)
		));
	}
}