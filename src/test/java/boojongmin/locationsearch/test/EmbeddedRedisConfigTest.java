package boojongmin.locationsearch.test;

import boojongmin.locationsearch.config.EmbeddedRedisConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * ZSet API 테스트를 위해 만든 테스트.
 * 삭제해도 무방.
 */
@RunWith(SpringRunner.class)
@ImportAutoConfiguration(classes = EmbeddedRedisConfigTest.Config.class)
@TestPropertySource("classpath:application.properties")
@Slf4j
public class EmbeddedRedisConfigTest {
    @Autowired
    RedisOperations<String, String> operations;

    @Test
    public void testRedisTemplate() {
        BoundZSetOperations<String, String> zset = operations.boundZSetOps("zset");
        zset.add("1", 1);
        zset.add("2", 2);
        zset.add("3", 3);
        zset.add("4", 4);
        zset.incrementScore("4", 1);
        zset.reverseRangeWithScores(0, 1).iterator()
                .forEachRemaining(x -> {
                    System.out.println(x.getValue() + "/" + x.getScore());
                });

    }

    @SpringBootApplication
    @Import(EmbeddedRedisConfig.class)
    static class Config {

    }


}
