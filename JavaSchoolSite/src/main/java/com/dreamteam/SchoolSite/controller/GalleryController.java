package com.dreamteam.SchoolSite.controller;


import com.dreamteam.SchoolSite.models.Career;
import com.dreamteam.SchoolSite.models.Gallery;
import com.dreamteam.SchoolSite.repositories.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Controller
public class GalleryController {

    @Autowired
    private GalleryRepository galleryRepository;

    @Value("${upload.path}")
    private  String uploadPath;

    @GetMapping("/gallery")
    public String galleryMain(Model model)
    {
        Iterable<Gallery> galleries = galleryRepository.findAll();
        model.addAttribute("galleries", galleries);
        return "gallery";
    }

    @GetMapping("/gallery/add")
    public String galleryAdd(Model model)
    {
        return "gallery_add";
    }

    @PostMapping("/gallery/add")
    public String galleryPostAdd(@RequestParam String description, Model model,@RequestParam("file") MultipartFile file) throws IOException {
        Gallery gallery = new Gallery(description);

        if(file != null && !file.getOriginalFilename().isEmpty())
        {
            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists())
            {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            gallery.setFilename(resultFilename);
        }

        galleryRepository.save(gallery);
        return "redirect:/gallery";
    }

    @GetMapping("/gallery/{id}/edit")
    public String galleryEdit(@PathVariable(value = "id") long id, Model model)
    {
        if(!galleryRepository.existsById(id))
        {
            return "redirect:/gallery";
        }

        Optional<Gallery> gallery = galleryRepository.findById(id);
        ArrayList<Gallery> res = new ArrayList<>();
        gallery.ifPresent(res::add);
        model.addAttribute("gallery", res);
        return "gallery_edit";
    }

    @PostMapping("/gallery/{id}/edit")
    public String galleryPostUpdate(@PathVariable(value = "id") long id,
                                   @RequestParam String description,
                                    Model model)
    {
        Gallery gallery = galleryRepository.findById(id).orElseThrow(IllegalStateException::new);

        gallery.setDescription(description);
        galleryRepository.save(gallery);

        return "redirect:/gallery";
    }

    @PostMapping("/gallery/{id}/remove")
    public String galleryPostDelete(@PathVariable(value = "id") long id, Model model)
    {
        Gallery gallery = galleryRepository.findById(id).orElseThrow(IllegalStateException::new);
        galleryRepository.delete(gallery);

        return "redirect:/gallery";
    }
}
