package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
public class UserTest {
    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void deleteAll() {
        userRepository.deleteAll();
    }

    @Test
    void save() {
        User expected = JAVAJIGI;
        User actual = userRepository.save(expected);
        assertAll(
                () -> Assertions.assertThat(actual.getId()).isNotNull(),
                () -> Assertions.assertThat(actual.getPassword()).isEqualTo(expected.getPassword()),
                () -> Assertions.assertThat(actual.getEmail()).isEqualTo(expected.getEmail()),
                () -> Assertions.assertThat(actual.getUserId()).isEqualTo(expected.getUserId()),
                () -> Assertions.assertThat(actual.getName()).isEqualTo(expected.getName())
        );
    }

    @Test
    void findById() {
        User expected = userRepository.save(JAVAJIGI);
        User actual1 = userRepository.findById(expected.getId()).get();
        assertAll(
                () -> Assertions.assertThat(actual1.getId()).isNotNull(),
                () -> Assertions.assertThat(actual1.getPassword()).isEqualTo(expected.getPassword()),
                () -> Assertions.assertThat(actual1.getEmail()).isEqualTo(expected.getEmail()),
                () -> Assertions.assertThat(actual1.getUserId()).isEqualTo(expected.getUserId()),
                () -> Assertions.assertThat(actual1.getName()).isEqualTo(expected.getName())
        );
    }

    @Test
    void findByUserId() {
        User expected = userRepository.save(JAVAJIGI);
        User actual2 = userRepository.findByUserId(expected.getUserId()).get();
        assertAll(
                () -> Assertions.assertThat(actual2.getId()).isNotNull(),
                () -> Assertions.assertThat(actual2.getPassword()).isEqualTo(expected.getPassword()),
                () -> Assertions.assertThat(actual2.getEmail()).isEqualTo(expected.getEmail()),
                () -> Assertions.assertThat(actual2.getUserId()).isEqualTo(expected.getUserId()),
                () -> Assertions.assertThat(actual2.getName()).isEqualTo(expected.getName())
        );
    }

    @Test
    void delete() {
        User user = userRepository.save(JAVAJIGI);
        userRepository.deleteById(user.getId());

        assertThat(userRepository.findByUserId(user.getUserId())).isNotPresent();
    }

}