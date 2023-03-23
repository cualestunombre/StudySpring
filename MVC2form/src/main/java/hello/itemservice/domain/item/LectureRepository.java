package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public class LectureRepository {
    private static Long seq = 0L;
    private static Map<Long,Lecture> lectureMap = new HashMap<>();

    public void addLecture(Lecture lecture){
        lecture.setId(seq);
        lectureMap.put(seq++,lecture);
        return ;
    }
    public void updateLecture(Long lectureId,Lecture lecture){
        Lecture found = findById(lectureId);
        found.setName(lecture.getName());
        found.setPrice(lecture.getPrice());
        found.setTarget(lecture.getTarget());
        found.setSubject(lecture.getSubject());
    }
    public List<Lecture> getAll(){
        return new ArrayList<>(lectureMap.values());
    }
    public  Lecture findById(Long lectureId){
        return lectureMap.get(lectureId);
    }



}
