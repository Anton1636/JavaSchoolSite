package com.dreamteam.SchoolSite.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="tblNews")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    @NotBlank(message = "Name is mandatory")
    private String Description;

    @Column(nullable=false)
    @NotBlank(message = "Name is mandatory")
    private String Title;

    @Column(nullable=false)
    @NotBlank(message = "Name is mandatory")
    private String DatePost;

    @Column(nullable=false)
    @NotBlank(message = "Name is mandatory")
    private String imageLink;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User Author;



    public News(@NotBlank(message = "Name is mandatory") String description, @NotBlank(message = "Name is mandatory") String title, @NotBlank(message = "Name is mandatory") String datePost, User user) {
        Author = user;
        Description = description;
        Title = title;
        DatePost = datePost;
    }

    public News(String description, String title, String datePost, String imageLink, User user) {
    }

    public News() {
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getDescription() { return Description; }

    public void setDescription(String description) { Description = description; }

    public String getTitle() { return Title; }

    public void setTitle(String title) { Title = title; }

    public String getDatePost() { return DatePost; }

    public void setDatePost(String datePost) { DatePost = datePost; }

    public User getAuthor() { return Author; }

    public void setAuthor(User author) { Author = author; }

    public String getImageLink() { return imageLink; }

    public void setImageLink(String imageLink) { this.imageLink = imageLink; }
}
