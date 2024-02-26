package com.questions.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Entity
@Table(name = "QUIZ")
@NoArgsConstructor
public class Quiz {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
  @ManyToMany
  private List<Questions> questions;

  public Quiz(String title, List<Questions> questions) {
    this.title = title;
    this.questions = questions;
  }


}
