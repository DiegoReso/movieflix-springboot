package br.com.movieflix.service;

import br.com.movieflix.entity.Category;
import br.com.movieflix.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<Category> findAll() {
        List<Category> list = repository.findAll();
        return list;
    }

    public Category insert(Category category) {
        return repository.save(category);
    }

    public Optional<Category> getCategoryById(Long id) {
        return repository.findById(id);
    }
}
