package hello.itemservice.web.form;

import hello.itemservice.domain.item.Lecture;
import hello.itemservice.domain.item.LectureRepository;
import hello.itemservice.domain.item.Subject;
import hello.itemservice.domain.item.Target;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/study")
@RequiredArgsConstructor
public class StudyController {
    private final LectureValidator lectureValidator;
    @InitBinder
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(lectureValidator);
    }
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
    //에러 관리 정챍 강좌명은 10글자를 넘어서는 안됨
    //강좌 가격은 한 강의당 20만원을 넘어서는 안됨
    //과목은 반드시 선택해야 함
    //대상은 반드시 2개 이상이어야 함 --> 우리 회사는 단일 대상으로는 강의를 만들 지 않음
    public String itemaddV2(@Validated  @ModelAttribute Lecture lecture, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if (bindingResult.hasErrors()){
            log.info("error = {}",bindingResult);
            return "study/addlecture";
        }
        lectureRepository.addLecture(lecture);
        return "redirect:/study/lectures";
    }
//    public String itemaddV1(@ModelAttribute Lecture lecture, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
//        if(!StringUtils.hasText(lecture.getName()) || lecture.getName().length()>10){
//            bindingResult.rejectValue("name","required",new Object[]{10},null);
//        }
//        if(lecture.getPrice()==null || lecture.getPrice()>200000){
//            bindingResult.rejectValue("price","max",new Object[]{200000},null);
//        }
//        if(lecture.getSubject()==null){
//            bindingResult.rejectValue("subject","choose");
//        }
//        if(lecture.getTarget().size()<2){
//            bindingResult.rejectValue("target","min",new Object[]{2},null);
//        }
//        if(bindingResult.hasErrors()){
//            log.info("errors={}",bindingResult);
//            return "study/addlecture";
//        }
//        lectureRepository.addLecture(lecture);
//        return "redirect:/study/lectures";
//    }

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
