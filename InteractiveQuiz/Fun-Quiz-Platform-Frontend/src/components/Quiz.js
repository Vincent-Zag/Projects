import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import Button from 'react-bootstrap/Button';
import { useHistory } from 'react-router-dom';


function Quiz() {
  const [questions, setQuestions] = useState([]);
  const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
  const [userAnswer, setUserAnswer] = useState('');
  const [score, setScore] = useState(0);
  const [quizCompleted, setQuizCompleted] = useState(false);
  const history = useHistory();

  
  useEffect(() => {
    fetchRandomQuestions(); 
  }, []);

  const fetchRandomQuestions = () => {
    axios.get('http://localhost:5001/get-random-questions')
      .then(response => {
        setQuestions(response.data);
      })
      .catch(error => {
        console.error('Error fetching random questions:', error);
      });
  };

  const handleUserAnswerChange = event => {
    setUserAnswer(event.target.value);
  };

  const handleAnswerSubmit = () => {
    const currentQuestion = questions[currentQuestionIndex];
    const data = {
      question: currentQuestion.question,
      user_answer: userAnswer,
      score: score,
    };

    axios.post('http://localhost:5001/check-answer', data)
      .then(response => {
        if (response.data.message === 'Correct answer!') {
          setScore(response.data.score);
        }
        goToNextQuestion();
      })
      .catch(error => {
        console.error('Error checking answer:', error);
      });
  };

  const goToNextQuestion = () => {
    if (currentQuestionIndex < questions.length - 1) {
      setCurrentQuestionIndex(currentQuestionIndex + 1);
      setUserAnswer('');
    } else {
      setQuizCompleted(true);
    }
  };
  if (quizCompleted) {
    return (
      <div className="quiz">
        <h1>Quiz Completed!</h1>
        <p>Your Score: {score}</p>
        <Link className="dblink" to="/dashboard">Go to Dashboard</Link>
      </div>
    );
  }

  if (!questions || questions.length === 0) {
    return <div>Loading...</div>; 
  }

  const currentQuestion = questions[currentQuestionIndex];
  const handleBackToDashboard = () => {
    history.push('/dashboard');
  };
  return (
<div> <Button variant="outline-secondary" onClick={handleBackToDashboard} className="sticky-button">
        Back to Dashboard
      </Button>
      

    <div  className="quiz"
      style={{
        background: `url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZWsiH-dRUHOkXJijCsKJb9CvNxyMyAscc-g&usqp=CAU") no-repeat center center fixed`,
        backgroundSize: 'cover',
        minHeight: '80vh', 
        display: 'flex',
        flexDirection: 'column', 
        alignItems: 'center',
        justifyContent: 'center', 
      }}
      >
        
      <h2>Question {currentQuestionIndex + 1}</h2>
      <br/>

      <p>{currentQuestion.question}</p>
      <br/>

      <input
        type="text"
        value={userAnswer}
        onChange={handleUserAnswerChange}
        placeholder="Enter your answer"
      />
      <br/>
      <br/>

      <button variant="outline-secondary" onClick={handleAnswerSubmit}>Submit Answer</button>
      <br/>
      <br/>

      <p>Score: {score}</p>
      <br/>

    </div>
    </div>
  );
}

export default Quiz;
