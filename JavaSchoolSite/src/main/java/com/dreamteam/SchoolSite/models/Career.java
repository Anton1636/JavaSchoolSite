package com.dreamteam.SchoolSite.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="tblCareer")
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false)
    @NotBlank(message = "Name is mandatory")
    private String Description;

    @Column(nullable=false)
    @NotBlank(message = "Name is mandatory")
    private String Salary;

    public Career(@NotBlank(message = "Name is mandatory") String description, @NotBlank(message = "Name is mandatory") String salary) {
        Description = description;
        Salary = salary;
    }

    public Career() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }
}
