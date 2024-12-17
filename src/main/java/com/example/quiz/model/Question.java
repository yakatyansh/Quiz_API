package com.example.quiz.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;
    @Column(name = "OPTION_A")
    private String optionA;

    @Column(name = "OPTION_B")
    private String optionB;

    @Column(name = "OPTION_C")
    private String optionC;

    @Column(name = "OPTION_D")
    private String optionD;

    private String correctOption;
}
