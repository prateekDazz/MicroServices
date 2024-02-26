package com.questions.service;

import com.questions.entity.Questions;
import com.questions.entity.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionsRepository questionsRepository;
    @Override
    public Questions createQuestions(Questions que) {
        return questionsRepository.save(que) ;
    }

    @Override
    public Questions getQuestionByQuestionId(int id) {



        Questions q1=  questionsRepository.findById(id).get();
        return q1;
    }

    @Override
    public List<Questions> getAllQuestions() {
        return questionsRepository.findAll();
    }

    @Override
    public List<Questions> getQuestionByCategory(String category) {


        List<Questions>  q =  questionsRepository.getQuestionsByCategory(category);
        if(q ==null  || CollectionUtils.isEmpty(q))
        {
            return null;
        }
        return q;
    }

    @Override
    public List<Questions> getRandomQuestionsByCategory(String category, int noOfQ) {

        List<Questions> q = questionsRepository.getRandomQuestionsByCategory(category,noOfQ);
        return q;
    }


}
