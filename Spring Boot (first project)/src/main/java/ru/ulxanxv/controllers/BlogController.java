package ru.ulxanxv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ulxanxv.models.Post;
import ru.ulxanxv.repo.PostRepository;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/blog")
    public String blog(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);

        return "blog";
    }

    @GetMapping("/blog/add")
    public String blogAddGet(Model model) {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogAddPost(@RequestParam String title,
                              @RequestParam String anons,
                              @RequestParam String fullText,
                              Model model) {

        Post post = new Post(title, anons, fullText);
        postRepository.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") Long id,
                              Model model) {

        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }

        Optional<Post> byId = postRepository.findById(id);
        Post post = byId.orElseThrow(IllegalAccessError::new);
        post.setViews(post.getViews() + 1);
        postRepository.save(post);

        ArrayList<Post> arrayList = new ArrayList<>();
        byId.ifPresent(arrayList::add);

        model.addAttribute("post", arrayList);

        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") Long id,
                              Model model) {

        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }

        Optional<Post> byId = postRepository.findById(id);

        ArrayList<Post> arrayList = new ArrayList<>();
        byId.ifPresent(arrayList::add);

        model.addAttribute("post", arrayList);

        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") Long id,
                                 @RequestParam String title,
                              @RequestParam String anons,
                              @RequestParam String fullText,
                              Model model) {

        Post post = postRepository.findById(id).orElseThrow(IllegalAccessError::new);

        post.setTitle(title);
        post.setAnons(anons);
        post.setFullText(fullText);

        postRepository.save(post);
        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") Long id,
                                 Model model) {

        Post post = postRepository.findById(id).orElseThrow(IllegalAccessError::new);

        postRepository.delete(post);
        return "redirect:/blog";
    }

}
