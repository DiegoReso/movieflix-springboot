package br.com.movieflix.service;

import br.com.movieflix.entity.User;
import br.com.movieflix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User save(User user){
        return repository.save(user);
    }

}
