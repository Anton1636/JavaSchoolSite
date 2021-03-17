package com.dreamteam.SchoolSite.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="tblSchedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false)
    @NotBlank(message = "Name is mandatory")
    private String TeacherName;

    @Column(nullable=false)
    @NotBlank(message = "Name is mandatory")
    private String SubjectName;

    @Column(nullable=false)
    @NotBlank(message = "Name is mandatory")
    private String StartTime;

    @Column(nullable=false)
    @NotBlank(message = "Name is mandatory")
    private String EndTime;

    @Column(nullable=false)
    @NotBlank(message = "Name is mandatory")
    private String DayWeek;

    public Schedule(@NotBlank(message = "Name is mandatory") String teacherName, @NotBlank(message = "Name is mandatory") String subjectName, @NotBlank(message = "Name is mandatory") String startTime, @NotBlank(message = "Name is mandatory") String endTime, @NotBlank(message = "Name is mandatory") String dayWeek) {
        TeacherName = teacherName;
        SubjectName = subjectName;
        StartTime = startTime;
        EndTime = endTime;
        DayWeek = dayWeek;
    }

    public Schedule() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String teacherName) {
        TeacherName = teacherName;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getDayWeek() {
        return DayWeek;
    }

    public void setDayWeek(String dayWeek) {
        DayWeek = dayWeek;
    }
}
