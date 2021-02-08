import React, { useState } from "react";
import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import Col from "react-bootstrap/Col";
import Container from "react-bootstrap/Container";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import logo from "../ressources/images/logo-128-128.png";
import { useHistory } from "react-router-dom";

const Login = () => {
  const [validated, setValidated] = useState(false);
  const history = useHistory();

  const handleSubmit = (event: {
    currentTarget: any;
    preventDefault: () => void;
    stopPropagation: () => void;
  }) => {
    history.push("/");
  };

  // const handleSubmit = (event: {
  //   currentTarget: any;
  //   preventDefault: () => void;
  //   stopPropagation: () => void;
  // }) => {
  //   const form = event.currentTarget;
  //   if (form.checkValidity() === false) {
  //     event.preventDefault();
  //     event.stopPropagation();
  //   }

  //   setValidated(true);
  // };

  return (
    <Container style={{ height: "100%" }}>
      <Row>
        <Col className="d-flex justify-content-center">
          <Card className="app-card" style={{ width: "50%", top: "30%" }}>
            <Card.Img
              variant="top"
              src={logo}
              style={{
                width: "128px",
                paddingTop: "15px",
                display: "block",
                marginLeft: "auto",
                marginRight: "auto",
              }}
            />
            <Card.Body>
              <Card.Title className="text-center">
                Bienvenue sur Biblillonie
              </Card.Title>
              <Form onSubmit={handleSubmit}>
                <Form.Group controlId="email">
                  <Form.Label>E-mail</Form.Label>
                  <Form.Control
                    type="email"
                    placeholder="Entrer votre e-mail"
                  />
                </Form.Group>
                <Form.Group controlId="password">
                  <Form.Label>Mot de passe</Form.Label>
                  <Form.Control
                    type="password"
                    placeholder="Entrer votre mot de passe"
                  />
                </Form.Group>
                <Button variant="primary" type="submit" block>
                  Se connecter
                </Button>
              </Form>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container>
  );
};

export default Login;
