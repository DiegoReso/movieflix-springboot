package br.com.movieflix.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.movieflix.entity.User;
import br.com.movieflix.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User testUser;
    private String rawPassword;
    private String encodedPassword;

    @BeforeEach

    void setUp() {
        rawPassword = "senhaOriginal";
        encodedPassword = "senhaCriptografada";

        testUser = new User();
        testUser.setName("Teste User");
        testUser.setEmail("teste@email.com");
        testUser.setPassword(rawPassword);
    }

    @Test
    @DisplayName( "Testa se o usuário é salvo com a senha criptografada")
    void whenSaveUser_thenPasswordIsEncodedAndUserIsSaved() {

        when(passwordEncoder.encode(any(String.class))).thenReturn(encodedPassword);

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User userBeingSaved = invocation.getArgument(0);
            userBeingSaved.setPassword(encodedPassword);
            return userBeingSaved;
        });


        User savedUser = userService.save(testUser);

        verify(passwordEncoder).encode(rawPassword);

        verify(userRepository).save(any(User.class));

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getPassword()).isEqualTo(encodedPassword);
        assertThat(savedUser.getName()).isEqualTo("Teste User");
        assertThat(savedUser.getEmail()).isEqualTo("teste@email.com");


    }

    @Test
    @DisplayName("Testa se o usuário é salvo com a senha criptografada mesmo quando a senha é vazia")
    void whenSaveUser_withEmptyPassword_shouldStillEncodeAndSave() {

        testUser.setPassword("");
        encodedPassword = "emptyPasswordEncoded";

        when(passwordEncoder.encode(any(String.class))).thenReturn(encodedPassword);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User userBeingSaved = invocation.getArgument(0);
            userBeingSaved.setPassword(encodedPassword);
            return userBeingSaved;
        });

        User savedUser = userService.save(testUser);

        verify(passwordEncoder).encode("");
        verify(userRepository).save(any(User.class));
        assertThat(savedUser.getPassword()).isEqualTo(encodedPassword);
    }
}
