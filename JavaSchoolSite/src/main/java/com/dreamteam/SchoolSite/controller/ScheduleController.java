package com.dreamteam.SchoolSite.controller;

import com.dreamteam.SchoolSite.models.Career;
import com.dreamteam.SchoolSite.models.News;
import com.dreamteam.SchoolSite.models.Schedule;
import com.dreamteam.SchoolSite.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @GetMapping("/schedule")
    public String scheduleMain(Model model)
    {
        Iterable<Schedule> schedules  = scheduleRepository.findAll();
        model.addAttribute("schedules", schedules);
        return "schedule";
    }

    @GetMapping("/schedule/add")
    public String scheduleAdd(Model model)
    {
        return "schedule_add";
    }

    @PostMapping("/schedule/add")
    public String schedulePostAdd(@RequestParam String teacherName,
                                  @RequestParam String subjectName,
                                  @RequestParam String startTime,
                                  @RequestParam String endTime,
                                  @RequestParam String dayWeek, Model model)
    {
        Schedule schedule = new Schedule(teacherName,subjectName,startTime,endTime,dayWeek);
        scheduleRepository.save(schedule);
        return "redirect:/schedule";
    }

    @GetMapping("/schedule/{id}/edit")
    public String scheduleEdit(@PathVariable(value = "id") long id, Model model)
    {
        if(!scheduleRepository.existsById(id))
        {
            return "redirect:/schedule";
        }

        Optional<Schedule> schedule = scheduleRepository.findById(id);
        ArrayList<Schedule> res = new ArrayList<>();
        schedule.ifPresent(res::add);
        model.addAttribute("schedule", res);
        return "schedule_edit";
    }

    @PostMapping("/schedule/{id}/edit")
    public String schedulePostUpdate(@PathVariable(value = "id") long id,
                                   @RequestParam String teacherName,
                                   @RequestParam String subjectName,
                                   @RequestParam String startTime,
                                   @RequestParam String endTime,
                                   @RequestParam String dayWeek,Model model)
    {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(IllegalStateException::new);

        schedule.setTeacherName(teacherName);
        schedule.setSubjectName(subjectName);
        schedule.setStartTime(startTime);
        schedule.setEndTime(endTime);
        schedule.setDayWeek(dayWeek);
        scheduleRepository.save(schedule);

        return "redirect:/schedule";
    }

    @PostMapping("/schedule/{id}/remove")
    public String schedulePostDelete(@PathVariable(value = "id") long id, Model model)
    {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(IllegalStateException::new);
        scheduleRepository.delete(schedule);

        return "redirect:/schedule";
    }
}