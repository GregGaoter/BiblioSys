import React from "react";
import Card from "react-bootstrap/Card";
import Container from "react-bootstrap/Container";

export const Equipes = () => {
  return (
    <Container fluid>
      <Card className="app-card">
        <Card.Body>
          <Card.Title><h3>Nos équipes</h3></Card.Title>
          <Card.Text>Contenu de la page Equipes.</Card.Text>
        </Card.Body>
      </Card>
    </Container>
  );
};
