let sessionId = null;
let currentQuestionIndex = 0;
let questionList = [];

document.getElementById("start-session-btn").addEventListener("click", startNewSession);
document.getElementById("next-question-btn").addEventListener("click", loadNextQuestion);
document.getElementById("new-quiz-btn").addEventListener("click", restartQuiz);
document.getElementById("create-question-form").addEventListener("submit", createQuestion);
document.getElementById("load-questions-btn").addEventListener("click", loadAllQuestions);

function startNewSession() {
    fetch('/api/quiz/start', {
        method: 'POST',
    })
    .then(response => response.text())
    .then(data => {
        sessionId = data;
        document.getElementById("session-id").querySelector("span").textContent = sessionId;
        document.getElementById("start-section").style.display = 'none';
        document.getElementById("question-section").style.display = 'block';
        loadNextQuestion();
    });
}

function loadNextQuestion() {
    fetch(`/api/quiz/question/${sessionId}`)
    .then(response => response.json())
    .then(question => {
        document.getElementById("question-text").textContent = question.questionText;
        document.getElementById("optionA").textContent = question.optionA;
        document.getElementById("optionB").textContent = question.optionB;
        document.getElementById("optionC").textContent = question.optionC;
        document.getElementById("optionD").textContent = question.optionD;
        questionList.push(question);
        document.getElementById("next-question-btn").style.display = 'none';
        document.getElementById("options").querySelectorAll('button').forEach(button => {
            button.addEventListener("click", (e) => submitAnswer(e.target.id, question));
        });
    });
}

function submitAnswer(chosenOption, question) {
    fetch(`/api/quiz/answer/${sessionId}?questionId=${question.id}&chosenOption=${chosenOption}`, {
        method: 'POST'
    })
    .then(response => response.json())
    .then(isCorrect => {
        if (isCorrect) {
            alert("Correct Answer!");
        } else {
            alert("Wrong Answer!");
        }
        document.getElementById("next-question-btn").style.display = 'block';
    });
}

function loadSessionSummary() {
    fetch(`/api/quiz/summary/${sessionId}`)
    .then(response => response.json())
    .then(summary => {
        document.getElementById("total-questions").textContent = summary.totalQuestionsAsked;
        document.getElementById("correct-answers").textContent = summary.correctAnswers;
        document.getElementById("question-section").style.display = 'none';
        document.getElementById("summary-section").style.display = 'block';
    });
}

function restartQuiz() {
    document.getElementById("summary-section").style.display = 'none';
    document.getElementById("start-section").style.display = 'block';
    questionList = [];
    sessionId = null;
}

function loadAllQuestions() {
    fetch('/api/quiz/questions')
    .then(response => response.json())
    .then(questions => {
        const questionsListDiv = document.getElementById("questions-list");
        questionsListDiv.innerHTML = '';
        questions.forEach(q => {
            const div = document.createElement('div');
            div.innerHTML = `<strong>${q.questionText}</strong>`;
            questionsListDiv.appendChild(div);
        });
    });
}

function createQuestion(event) {
    event.preventDefault();
    
    const newQuestion = {
        questionText: document.getElementById("new-question-text").value,
        optionA: document.getElementById("new-optionA").value,
        optionB: document.getElementById("new-optionB").value,
        optionC: document.getElementById("new-optionC").value,
        optionD: document.getElementById("new-optionD").value,
        correctOption: document.getElementById("new-correct-option").value,
    };
    
    fetch('/api/quiz/question', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newQuestion)
    })
    .then(response => response.json())
    .then(data => {
        alert('Question created successfully');
        loadAllQuestions();
    });
}
