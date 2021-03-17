package com.dreamteam.SchoolSite.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="tblSchoolParty")
public class SchoolParty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false)
    @NotBlank(message = "Name is mandatory")
    private String FullDate;

    @Column(nullable=false)
    @NotBlank(message = "Name is mandatory")
    private String Description;

    @Column(nullable=false)
    @NotBlank(message = "Name is mandatory")
    private String imageLink;

    public SchoolParty(long id, @NotBlank(message = "Name is mandatory") String fullDate, @NotBlank(message = "Name is mandatory") String description, @NotBlank(message = "Name is mandatory") String imageLink) {
        this.id = id;
        FullDate = fullDate;
        Description = description;
        this.imageLink = imageLink;
    }

    public SchoolParty(String fullDate, String description, String imageLink) {
    }

    public SchoolParty() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullDate() {
        return FullDate;
    }

    public void setFullDate(String fullDate) {
        FullDate = fullDate;
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
