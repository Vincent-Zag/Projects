import React, { useState } from 'react';
import axios from 'axios';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import '../styles.css';
import { useHistory } from 'react-router-dom';


function AddQuestion() {
  const [question, setQuestion] = useState('');
  const [answer, setAnswer] = useState('');
  const [category, setCategory] = useState('');
  const [difficulty, setDifficulty] = useState('');
  const [message, setMessage] = useState('');

  const history = useHistory();
  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .post('http://localhost:5001/questions', {
        question,
        answer,
        category,
        difficulty,
      })
      .then((response) => {
        console.log(response.data.message);
        setMessage('Question added successfully!');
        setQuestion('');     // Reset question state
        setAnswer('');       // Reset answer state
        setCategory('');     // Reset category state
        setDifficulty('');   // Reset difficulty state
      })
      .catch((error) => {
        console.error('Error adding question:', error.response.data.message);
        setMessage('Error adding question. Please try again.');
      });
  };

  const handleBackToDashboard = () => {
    history.push('/dashboard'); // Navigate back to dashboard
  };

  return (
    <div className="container mt-4">
      
      <br/>

      <Button variant="outline-secondary" onClick={handleBackToDashboard} className="sticky-button">
        Back to Dashboard
      </Button>
      <br/>

      <br />
      <h2>Add New Question</h2>
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="question">
          <Form.Label>Question</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter question"
            value={question}
            onChange={(e) => setQuestion(e.target.value)}
            required
          />
        </Form.Group>

        <Form.Group controlId="answer">
          <Form.Label>Answer</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter answer"
            value={answer}
            onChange={(e) => setAnswer(e.target.value)}
            required
          />
        </Form.Group>

        <Form.Group controlId="category">
          <Form.Label>Category</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter category"
            value={category}
            onChange={(e) => setCategory(e.target.value)}
            required
          />
        </Form.Group>

        <Form.Group controlId="difficulty">
          <Form.Label>Difficulty</Form.Label>
          <Form.Control
            type="number"
            placeholder="Enter difficulty (0-5)"
            value={difficulty}
            onChange={(e) => setDifficulty(e.target.value)}
            required
          />
        </Form.Group>
        <br />
        <Button variant="outline-secondary" type="submit">
          Add Question
        </Button>
      <br/>
        <p className="mt-2">{message}</p>
      </Form>

    </div>
  );
}

export default AddQuestion;
