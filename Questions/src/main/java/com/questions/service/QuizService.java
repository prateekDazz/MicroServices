package com.questions.service;

import com.questions.entity.Questions;
import com.questions.entity.Quiz;
import com.questions.entity.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizService {

    public Quiz createQuiz(Quiz q);


    List<Questions> getQuestionsById(int id);

    ResponseEntity<Integer> fetchResults(int id, List<Response> res);
}
