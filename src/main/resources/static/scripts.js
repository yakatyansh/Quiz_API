let sessionId = null;
let currentQuestionIndex = 0;
let questionList = [];

document.getElementById("start-session-btn").addEventListener("click", startNewSession);
document.getElementById("next-question-btn").addEventListener("click", loadNextQuestion);
document.getElementById("new-quiz-btn").addEventListener("click", restartQuiz);
document.getElementById("create-question-form").addEventListener("submit", createQuestion);
document.getElementById("load-questions-btn").addEventListener("click", loadAllQuestions);

function startNewSession() {
    fetch('http://localhost:8080/api/quiz/start', {
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
    fetch(`http://localhost:8080/api/quiz/question/random/${sessionId}`)
    .then(response => response.json())
    .then(question => {
        document.getElementById("question-text").textContent = question.questionText;
        document.getElementById("optionA").textContent = question.optionA;
        document.getElementById("optionB").textContent = question.optionB;
        document.getElementById("optionC").textContent = question.optionC;
        document.getElementById("optionD").textContent = question.optionD;
        questionList.push(question);
        document.getElementById("next-question-btn").style.display = 'none';
        
        const optionButtons = document.getElementById("options").querySelectorAll('button');
        optionButtons.forEach(button => {
            // Remove any existing event listeners
            button.replaceWith(button.cloneNode(true));
        });

        // Re-attach event listeners to the fresh buttons
        document.getElementById("options").querySelectorAll('button').forEach(button => {
            button.addEventListener("click", (e) => submitAnswer(e.target.id, question));
        });
    });
}

function submitAnswer(chosenOption, question) {
    fetch(`http://localhost:8080/api/quiz/answer/${sessionId}?questionId=${question.id}&chosenOption=${chosenOption}`, {
        method: 'POST'
    })
    .then(response => {
        // Ensure the response is properly converted to JSON
        return response.json();
    })
    .then(isCorrect => {
        console.log("Is Correct Response: ", isCorrect); 
        console.log("Chosen Option:", chosenOption);// Debugging line
        if (isCorrect === true) {
            alert("Correct Answer!");
        } else if (isCorrect === false) {
            alert("Wrong Answer!");
        } else {
            alert("Unexpected response from server.");
        }
        document.getElementById("next-question-btn").style.display = 'block';
    })
    .catch(error => {
        console.error("Error in submitAnswer: ", error);
        alert("An error occurred. Please try again.");
    });
}


function loadSessionSummary() {
    fetch(`http://localhost:8080/api/quiz/summary/${sessionId}`)
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
    fetch('http://localhost:8080/api/quiz/questions')
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
    
    fetch('http://localhost:8080/api/quiz/question', {
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
