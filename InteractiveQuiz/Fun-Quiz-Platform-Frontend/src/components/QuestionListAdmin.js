import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Button from 'react-bootstrap/Button';
import { useHistory } from 'react-router-dom';

function QuestionListAdmin() {
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



  const handleDeleteQuestion = (question) => {
    axios.post('http://localhost:5001/questions/delete', { question }, {
      headers: {
        'Content-Type': 'application/json',
      }
    }).then(response => {
      console.log(response.data.message);
      // Remove the deleted question from the state
      setQuestions(questions.filter(q => q.question !== question));
    })
      .catch(error => {
        console.error('Error deleting question:', error.response ? error.response.data.message : error.message);
      });
  };

  const handleBackToDashboard = () => {
    history.push('/dashboard'); // Navigate back to dashboard
  };

  return (
    <div>
      <Button variant="outline-secondary" onClick={handleBackToDashboard} className="sticky-button">
        Back to Dashboard
      </Button>
      <h2>Question List (Admin)</h2>
      {questions.map((question, index) => (
        <div key={index} className="admin-question">
          <strong>Question:</strong> {question.question}
          <br />
          <strong>Answer:</strong> {question.answer}
          <br />
          <Button variant="outline-danger" onClick={() => handleDeleteQuestion(question.question)}>
            Delete Question
          </Button>
          <hr />
        </div>
      ))}
    </div>
  );
}

export default QuestionListAdmin;
