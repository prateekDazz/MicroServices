package com.questions.service;

import com.questions.entity.Questions;

import java.util.List;

public interface QuestionService {
    public Questions createQuestions(Questions que);
    public Questions getQuestionByQuestionId(int id);
    public List<Questions> getAllQuestions();

    List<Questions> getQuestionByCategory(String category);

    List<Questions> getRandomQuestionsByCategory(String category, int noOfQ);
}
