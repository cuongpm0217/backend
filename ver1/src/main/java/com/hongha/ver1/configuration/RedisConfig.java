package com.hongha.ver1.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.Scheduled;

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

	@Scheduled(cron = "0 0 0 1 * *")
	void cleanCache() {
		redisConnectionFactory().getConnection().serverCommands().flushAll();
	}
}
