import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../styles.css';
import { useHistory } from 'react-router-dom';
import Button from 'react-bootstrap/Button';

function QuestionList() {
  const [questions, setQuestions] = useState([]);
  const history = useHistory();

  useEffect(() => {
    axios.get('http://localhost:5001/quiz/list')
      .then(response => {
        setQuestions(response.data);
      })
      .catch(error => {
        console.error('Error fetching questions:', error);
      });
  }, []);

  const [flippedCard, setFlippedCard] = useState(null);

  const handleCardClick = index => {
    setFlippedCard(index === flippedCard ? null : index);
  };

  const handleBackToDashboard = () => {
    history.push('/dashboard');
  };


  return (
    <div>
      <Button variant="outline-secondary" onClick={handleBackToDashboard} className="sticky-button">
        Back to Dashboard
      </Button>

      <h2>Quiz Questions</h2>
      {questions.map((question, index) => (
        <div
          key={index}
          className={`flip-card ${flippedCard === index ? 'flipped' : ''}`}
          onClick={() => handleCardClick(index)}
        >
          <div className="flip-card-inner">
            <div className="flip-card-front">
              <strong>Question:</strong> {question.question}
            </div>
            <div className="flip-card-back">
              <strong>Answer:</strong> {question.answer}
              <br />

            </div>
          </div>
        </div>
      ))}


    </div>
  );
}

export default QuestionList;




