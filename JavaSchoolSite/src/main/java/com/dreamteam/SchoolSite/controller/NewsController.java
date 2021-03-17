package com.dreamteam.SchoolSite.controller;

import com.dreamteam.SchoolSite.models.News;
import com.dreamteam.SchoolSite.models.User;
import com.dreamteam.SchoolSite.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Controller
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @Value("${upload.path}")
    private  String uploadPathForNews;

    @GetMapping("/news")
    public String newsMain(Model model)
    {
        Iterable<News> news = newsRepository.findAll();
        model.addAttribute("news", news);
        return "news";
    }

    @GetMapping("/news/add")
    public String newsAdd(Model model)
    {
        return "news_add";
    }

    @PostMapping("/news/add")
    public String newsPostAdd(@AuthenticationPrincipal User user,
                              @RequestParam String description,
                              @RequestParam String title,
                              @RequestParam String datePost,
                              @RequestParam String imageLink,Model model)
    {
        News news = new News(description,title,datePost,imageLink,user);


        return "redirect:/news";
    }

    @GetMapping("/news/{id}/edit")
    public String newsEdit(@PathVariable(value = "id") long id, Model model)
    {
        if(!newsRepository.existsById(id))
        {
            return "redirect:/news";
        }

        Optional<News> news = newsRepository.findById(id);
        ArrayList<News> res = new ArrayList<>();
        news.ifPresent(res::add);
        model.addAttribute("news", res);
        return "news_edit";
    }

    @PostMapping("/news/{id}/edit")
    public String newsPostUpdate(@PathVariable(value = "id") long id,
                                   @RequestParam String description,
                                   @RequestParam String datePost,
                                   @RequestParam String title,
                                   @RequestParam String imageLink,Model model)
    {
        News news = newsRepository.findById(id).orElseThrow(IllegalStateException::new);

        news.setDescription(description);
        news.setTitle(title);
        news.setDatePost(datePost);
        news.setImageLink(imageLink);
        newsRepository.save(news);

        return "redirect:/news";
    }

    @PostMapping("/news/{id}/remove")
    public String newsPostDelete(@PathVariable(value = "id") long id, Model model)
    {
        News news = newsRepository.findById(id).orElseThrow(IllegalStateException::new);
        newsRepository.delete(news);

        return "redirect:/news";
    }
}