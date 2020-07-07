package ru.ulxanxv.repo;

import org.springframework.data.repository.CrudRepository;
import ru.ulxanxv.models.Post;

public interface PostRepository extends CrudRepository<Post, Long> {
}
