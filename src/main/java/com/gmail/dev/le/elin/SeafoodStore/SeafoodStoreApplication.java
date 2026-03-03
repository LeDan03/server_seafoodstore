package com.gmail.dev.le.elin.SeafoodStore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.gmail.dev.le.elin.SeafoodStore.user.UserMapper;
import com.gmail.dev.le.elin.SeafoodStore.user.UserRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class SeafoodStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeafoodStoreApplication.class, args);
	}
}