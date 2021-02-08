import React from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

export const Access = () => {
  return (
    <Container>
      <Row>
        <Col>
          <h1>Accès refusé</h1>
        </Col>
        <Col>
          <h2>Vous ne disposez pas des autorisations nécessaires</h2>
        </Col>
      </Row>
    </Container>
  );
};

export default Access;
