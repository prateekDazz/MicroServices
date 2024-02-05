package com.questions.controller;

import com.questions.entity.QuestionWrapper;
import com.questions.entity.Questions;
import com.questions.entity.Quiz;
import com.questions.entity.Response;
import com.questions.service.QuestionService;
import com.questions.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuizService quizService;

@PostMapping
    public ResponseEntity<Quiz>createQuiz(@RequestParam("category")String category,@RequestParam("numOfQuestions")int noOfQ,@RequestParam("title")String title)
    {
List<Questions> questionsList = questionService.getRandomQuestionsByCategory(category,noOfQ);
Quiz quiz = new Quiz(title,questionsList);
Quiz q2 = quizService.createQuiz(quiz);
return ResponseEntity.ok(q2);
    }
    @PostMapping("/fetchResults")
    public ResponseEntity<Integer>fetchQuizResults(@RequestParam("id")int id,@RequestBody List<Response>res)
    {
return quizService.fetchResults(id,res);

    }

    @GetMapping("/id")
    public ResponseEntity<List<QuestionWrapper>>getQuestionsById(@RequestParam("id")int id)
    {
        List<Questions> questionsList = quizService.getQuestionsById(id);
        List<QuestionWrapper>questionWrappers = new ArrayList<>();
        for(Questions q:questionsList)
        {
            QuestionWrapper w =new QuestionWrapper();
            w.setCategory(q.getCategory());
            w.setOption1(q.getOption1());
            w.setOption2(q.getOption2());
            w.setOption3(q.getOption3());
            w.setOption4(q.getOption4());
            w.setDifficultyLevel(q.getDifficultyLevel());
            w.setId(q.getId());
            w.setQuestionTitle(q.getQuestionTitle());
            questionWrappers.add(w);
        }

        return ResponseEntity.ok(questionWrappers);
    }

}
