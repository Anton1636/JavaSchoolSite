package com.dreamteam.SchoolSite.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="tblTeachers")
public class Teachers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false)
    @NotBlank(message = "Name is mandatory")
    private String FullName;

    @Column(nullable=false)
    @NotBlank(message = "Name is mandatory")
    private String Description;

    @Column(nullable=false)
    @NotBlank(message = "Name is mandatory")
    private String imageLink;

    public Teachers(long id, @NotBlank(message = "Name is mandatory") String fullName, @NotBlank(message = "Name is mandatory") String description, @NotBlank(message = "Name is mandatory") String imageLink) {
        this.id = id;
        FullName = fullName;
        Description = description;
        this.imageLink = imageLink;
    }

    public Teachers(String fullName, String imageLink, String description) {
    }

    public Teachers() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
