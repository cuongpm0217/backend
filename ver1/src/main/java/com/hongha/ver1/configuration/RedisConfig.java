package com.hongha.ver1.configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.Scheduled;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Configuration
public class RedisConfig {
	@Value("${spring.data.redis.host}")
	private String redisHost;
	@Value("${spring.data.redis.password}")
	private String redisPassword;
	@Value("${spring.data.redis.port}")
	private int redisPort;

	@Bean
	LettuceConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
		config.setHostName(redisHost);
		config.setPassword(redisPassword);
		config.setPort(redisPort);
//		LettuceClientConfiguration clientConfiguration = LettuceClientConfiguration.builder().useSsl()
//				.disablePeerVerification().build();
//		LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(config, clientConfiguration);
		LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(config);

		return lettuceConnectionFactory;
	}

	@Bean
	RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();

		template.setConnectionFactory(redisConnectionFactory());

		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));

		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));

		template.afterPropertiesSet();
		return template;
	}

	@Bean
	ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
		objectMapper.enable(DeserializationFeature.ACCEPT_FLOAT_AS_INT);
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ISO_DATE_TIME));
		simpleModule.addDeserializer(LocalDateTime.class,
				new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
		objectMapper.registerModule(simpleModule);
		return objectMapper;
	}

	@Scheduled(cron = "0 0 0 1 * *")
	void cleanCache() {
		redisConnectionFactory().getConnection().serverCommands().flushAll();
	}
}
