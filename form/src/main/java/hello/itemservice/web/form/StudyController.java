package hello.itemservice.web.form;

import hello.itemservice.domain.item.Lecture;
import hello.itemservice.domain.item.LectureRepository;
import hello.itemservice.domain.item.Subject;
import hello.itemservice.domain.item.Target;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/study")
@RequiredArgsConstructor
public class StudyController {
    @ModelAttribute("lectures")
    public List<Lecture> lectures(){
        return lectureRepository.getAll();
    }
    @ModelAttribute("lecture")
    public Lecture lecture(){
        return new Lecture();
    }
    @ModelAttribute("subjectTypes")
    public Subject[] subjectTypes(){
        return Subject.values();
    }
    @ModelAttribute("targetTypes")
    public Target[] targetTypes(){
        return Target.values();
    }
    private final LectureRepository lectureRepository;

    @GetMapping("/lectures")
    public String items(Model model){
        return "study/lectures";
    }
    @GetMapping("/edit/{path}")
    public String editForm(Model model, @PathVariable Long path){
        Lecture lecture = lectureRepository.findById(path);
        model.addAttribute("editLecture",lecture);
        return "study/editlecture";
    }
    @PostMapping("/edit/{path}")
    public String edit(@ModelAttribute Lecture lecture, @PathVariable Long path){
        lectureRepository.updateLecture(path,lecture);
        return "redirect:/study/lectures";
    }
    @GetMapping("/addlecture")
    public String additem(){
        return "study/addlecture";
    }
    @PostMapping("/addlecture")
    public String itemadd(@ModelAttribute Lecture lecture){
        lectureRepository.addLecture(lecture);
        return "redirect:/study/lectures";
    }

    @PostConstruct
    public void initItems(){
        Lecture first = new Lecture();
        first.setName("뉴런 수1");
        first.setPrice(109000L);
        first.setSubject(Subject.MATH);
        List<Target> target = new ArrayList<>();
        target.add(Target.H1);
        target.add(Target.H2);
        target.add(Target.H3);
        first.setTarget(target);
        lectureRepository.addLecture(first);
        Lecture second = new Lecture();
        second.setName("뉴런 수2");
        second.setPrice(111000L);
        second.setSubject(Subject.MATH);
        target = new ArrayList<>();
        target.add(Target.H2);
        target.add(Target.H3);
        second.setTarget(target);
        lectureRepository.addLecture(second);
    }
}
