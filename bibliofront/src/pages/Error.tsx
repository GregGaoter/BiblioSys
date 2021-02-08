import React from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

export const Error = () => {
  return (
    <Container>
      <Row>
        <Col>
          <h1>Erreur</h1>
        </Col>
        <Col>
          <h2>Un problème est survenu</h2>
        </Col>
      </Row>
    </Container>
  );
};

export default Error;
