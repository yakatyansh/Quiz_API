package com.example.quiz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.example.quiz.model.Question;

public class QuizSession {
    private final List<Question> answeredQuestions = new ArrayList<>();
    private final Map<Question, Boolean> answers = new LinkedHashMap<>();

    public void addQuestion(Question question) {
        answeredQuestions.add(question);
    }

    public void addAnswer(Question question, boolean isCorrect) {
        answers.put(question, isCorrect);
    }

    public Map<String, Object> getSummary() {
        long correctCount = answers.values().stream().filter(b -> b).count();
        long incorrectCount = answers.size() - correctCount;

        Map<String, Object> summary = new HashMap<>();
        summary.put("totalAnswered", answers.size());
        summary.put("correct", correctCount);
        summary.put("incorrect", incorrectCount);
        summary.put("details", answers);

        return summary;
    }
}
