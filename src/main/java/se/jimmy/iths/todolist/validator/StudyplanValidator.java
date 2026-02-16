package se.jimmy.iths.todolist.validator;


import org.springframework.stereotype.Component;
import se.jimmy.iths.todolist.exceptions.StudyplanValidationException;
import se.jimmy.iths.todolist.model.Studyplan;

@Component
public class StudyplanValidator {

    public void validate(Studyplan studyplan) {
        if (studyplan == null) {
            throw new StudyplanValidationException("studyplan cannot be empty");
        }

        if (studyplan.getTask() == null || studyplan.getTask().trim().isEmpty()) {
            throw new StudyplanValidationException("please add a task");
        }
        if (studyplan.getCoursename() == null || studyplan.getCoursename().trim().isEmpty()) {
            throw new StudyplanValidationException("coursename cannot be empty");
        }

        if (studyplan.getPriority()<0 || studyplan.getPriority()>10) {
            throw new StudyplanValidationException("priority must be between 0 and 10");
        }

        if(studyplan.getStartdate()== null){
            throw new StudyplanValidationException("start date cannot be null");
        }

        if (studyplan.getDeadline() !=null &&
                studyplan.getDeadline().isBefore(studyplan.getStartdate())) {
            throw new StudyplanValidationException("deadline cannot be before start date");
        }





    }
}


