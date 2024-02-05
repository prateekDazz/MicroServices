package com.questions.controller;

import com.questions.entity.Questions;
import com.questions.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class HomeController {
    @Autowired
private QuestionService questionService;
@PostMapping
    public ResponseEntity<Questions>createQuestion(@RequestBody Questions q)
    {
Questions q1 = questionService.createQuestions(q);
return ResponseEntity.ok(q1);
    }

    @GetMapping
    public ResponseEntity<List<Questions>>getAllQuestions()
    {
        try
        {
            List<Questions>allQuestions = questionService.getAllQuestions();
            return new ResponseEntity<>(allQuestions, HttpStatus.OK);
        }
        catch(Exception e)
        {
e.printStackTrace();
        }
return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    private ResponseEntity<Questions>getQuestionByQuestionId(@PathVariable int id)
    {
        try
        {
            Questions q =  questionService.getQuestionByQuestionId(id);
            if(q==null)
            {
                throw new RuntimeException();
            }

            return new ResponseEntity<>(q, HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();

    }

    @GetMapping("/category")
    private ResponseEntity<List<Questions>>getQuestionsByCategory(@RequestParam("category") String category)
    {

        try
        {
            List<Questions> q = questionService.getQuestionByCategory(category);

if(q==null || q.isEmpty())
{
    throw new RuntimeException();
}
            return new ResponseEntity<>(q, HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }
}
