package com.github.ryuzu.TestWebServer.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ryuzu.TestWebServer.redis.database.Member;
import com.github.ryuzu.TestWebServer.redis.database.RedisRepository;
import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class RedisConfiguration {
//    private final RedisRepository memberRepo;

//    @Bean
//    public CommandLineRunner loadTestData() {
//        return args -> {
//            // remove all companies
//            memberRepo.deleteAll();
//
//            // Create a couple of `Company` domain entities
//            var redis = Member.of("Id", "DisplayName", "HashedPassword", 1);
//
//            // save companies to the database
//            memberRepo.save(redis);
//        };
//    }

//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost", 6379));
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(factory);
//
//        var jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        template.setKeySerializer(stringRedisSerializer);
//        template.setHashKeySerializer(stringRedisSerializer);
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//        template.setHashValueSerializer(jackson2JsonRedisSerializer);
//        template.afterPropertiesSet();
//        return template;
//    }

    @Bean
    JedisPoolConfig jedisPoolConfig() {
        return new JedisPoolConfig();
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {

        RedisStandaloneConfiguration hostConfig = new RedisStandaloneConfiguration();

        JedisClientConfiguration.JedisClientConfigurationBuilder builder = JedisClientConfiguration.builder();
        var clientConfig = builder
                .usePooling()
                .poolConfig(jedisPoolConfig)
                .build();

        return new JedisConnectionFactory(hostConfig, clientConfig);
    }
}
