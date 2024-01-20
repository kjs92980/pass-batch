package com.fastcampus.pass.repository.user;

import com.fastcampus.pass.config.TestBatchConfig;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("tc")
@Testcontainers
@ContextConfiguration(classes = {TestBatchConfig.class}) // BaseEntity의 createdAt, modifiedAt을 위함
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;


    @Test
    public void test_save() {
        // given
        UserEntity userEntity = new UserEntity();
        final String userId = "C100" + RandomStringUtils.randomNumeric(4);
        userEntity.setUserId(userId);
        userEntity.setUserName("김철수");
        userEntity.setStatus(UserStatus.ACTIVE);
        userEntity.setPhone("01033334444");
        userEntity.setMeta(Map.of("uuid", "abcd1234"));

        // when
        userRepository.save(userEntity);

        // then
        final Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
        assertTrue(optionalUserEntity.isPresent());
    }
}
