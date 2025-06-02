package br.com.movieflix.repository;

import br.com.movieflix.controller.request.UserRequest;
import br.com.movieflix.entity.User;
import br.com.movieflix.mapper.UserMapper;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@ActiveProfiles ("test")
class UserRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Testa se o usuário é salvo com sucesso")
    void findUserByEmail() {

        String email = "diego@email.com";

        UserRequest userRequest = new UserRequest("Diego", email, "123456");
        createUser(userRequest);

        Optional<UserDetails> result = userRepository.findUserByEmail(email);

        assertThat(result.isPresent()).isTrue();

    }

    @Test
    @DisplayName("Testa se o usuário não é encontrado pelo email")
    void findUserByEmailNotFound() {

        String email = "diego@email.com";
        Optional<UserDetails> result = userRepository.findUserByEmail(email);
        assertThat(result.isEmpty()).isTrue();

    }


    private User createUser(UserRequest userRequest){
        User user = UserMapper.toUser(userRequest);
        entityManager.persist(user);
        return user;
    }
}