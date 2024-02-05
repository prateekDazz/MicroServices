package com.questions.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.annotation.Native;
import java.util.List;

public interface QuestionsRepository extends JpaRepository<Questions,Integer> {
   @Query("from Questions q where q.category=?1")
   List<Questions> getQuestionsByCategory(String category);

  @Query(value = "select * from Questions  where category=?1 limit ?2",nativeQuery = true)
   List<Questions> getRandomQuestionsByCategory(String category,int limit);
}
