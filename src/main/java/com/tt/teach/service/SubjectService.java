package com.tt.teach.service;

import com.tt.teach.pojo.Grade;
import com.tt.teach.pojo.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> getSubjectList();

    int deleteSubject(Integer subjectNo);

    int addSubject(Subject subject);

    int updateSubject(Subject subject);

    Subject getSubByName(String subjectName);

    List<Grade> getGrade();
}
