import React from "react";
import Card from "react-bootstrap/Card";
import Container from "react-bootstrap/Container";

export const Reseau = () => {
  return (
    <Container fluid>
      <Card className="app-card">
        <Card.Body>
          <Card.Title><h3>Notre réseau</h3></Card.Title>
          <Card.Text>Contenu de la page Reseau.</Card.Text>
        </Card.Body>
      </Card>
    </Container>
  );
};
