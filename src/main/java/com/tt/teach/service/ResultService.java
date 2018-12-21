package com.tt.teach.service;

import com.tt.teach.pojo.Result;
import com.tt.teach.pojo.Student;

import java.util.List;

public interface ResultService {
    List<Result> getResultList();

    int deleteResult(Integer resultNo);
}
