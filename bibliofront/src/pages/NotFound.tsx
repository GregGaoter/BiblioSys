import React from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

export const NotFound = () => {
  return (
    <Container>
      <Row>
        <Col>
          <h1>Page introuvable</h1>
        </Col>
        <Col>
          <h2>La ressource demandée n'est pas disponible</h2>
        </Col>
      </Row>
    </Container>
  );
};

export default NotFound;
