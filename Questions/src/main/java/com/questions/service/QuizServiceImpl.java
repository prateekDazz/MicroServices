package com.questions.service;

import com.questions.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService{

    @Autowired
    private QuizRepository  quizRepository;
    @Autowired
    private QuestionsRepository questionsRepository;
    @Override
    public Quiz createQuiz(Quiz q) {
        return quizRepository.save(q);
    }

    @Override
    public List<Questions> getQuestionsById(int id) {
        Quiz quiz = quizRepository.findById(id).get();
        return quiz.getQuestions();
    }

    @Override
    public ResponseEntity<Integer> fetchResults(int id, List<Response> res) {
        List<Questions>q = quizRepository.findById(id).get().getQuestions();
        int right = 0;
        for(Response r1:res)
        {
            for(Questions q1:q)
            {
                if(q1.getRightAnswer().equalsIgnoreCase(r1.getResponse()) && r1.getId()==q1.getId())
                {
                    right++;
                }
            }
        }
        return ResponseEntity.ok(right);
    }
}
