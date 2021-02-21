import React from "react";
import Card from "react-bootstrap/Card";
import CardDeck from "react-bootstrap/CardDeck";
import Container from "react-bootstrap/Container";
import memory from "../ressources/images/memory.jpg";
import reverie from "../ressources/images/reverie.jpg";
import idea from "../ressources/images/idea.png";

export const Bibliotheques = () => {
  return (
    <Container fluid>
      <CardDeck>
        <Card>
          <Card.Img variant="top" src={memory} height="350" />
          <Card.Body>
            <Card.Title>Bibliothèque Mémoire</Card.Title>
            <Card.Text>
              Cette bibliothèque située <strong>81 Rue des Mandarines</strong> fera travailler votre mémoire avec sa
              large collection de revues scientifiques.
            </Card.Text>
          </Card.Body>
          <Card.Footer>
            <small className="text-muted">500 livres</small>
          </Card.Footer>
        </Card>
        <Card>
          <Card.Img variant="top" src={reverie} height="350" />
          <Card.Body>
            <Card.Title>Bibliothèque Rêverie</Card.Title>
            <Card.Text>
              Cette bibliothèque située <strong>10 Rue des Bijoux</strong> vous fera rêver avec sa large collection de
              romans sentimentaux.
            </Card.Text>
          </Card.Body>
          <Card.Footer>
            <small className="text-muted">500 livres</small>
          </Card.Footer>
        </Card>
        <Card>
          <Card.Img variant="top" src={idea} height="350" />
          <Card.Body>
            <Card.Title>Bibliothèque Idée</Card.Title>
            <Card.Text>
              Cette bibliothèque située <strong>49 Rue de Lavande</strong> ne vous laissera pas à court d'idée avec sa
              large collection de revues techniques et de bricolage.
            </Card.Text>
          </Card.Body>
          <Card.Footer>
            <small className="text-muted">500 livres</small>
          </Card.Footer>
        </Card>
      </CardDeck>
    </Container>
  );
};
