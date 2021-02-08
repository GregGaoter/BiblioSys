import React from "react";
import Container from "react-bootstrap/Container";
import Dropdown from "react-bootstrap/Dropdown";
import DropdownButton from "react-bootstrap/DropdownButton";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";
import { Route } from "react-router-dom";
import "./App.css";
import { Accueil } from "./components/Accueil";
import { Bibliotheques } from "./components/Bibliotheques";
import { Equipes } from "./components/Equipes";
import { Reseau } from "./components/Reseau";
import logo from "./ressources/images/logo-32-32.png";

const routers = [
  { path: "/", component: Accueil, exact: true },
  { path: "/reseau", component: Reseau },
  { path: "/bibliotheques", component: Bibliotheques },
  { path: "/equipes", component: Equipes },
];

function App() {
  return (
    <>
      <Navbar bg="dark" variant="dark" className="app-navbar">
        <Navbar.Brand href="/">
          <img alt="" src={logo} width="32" height="32" className="d-inline-block align-top" /> Biblillonie
        </Navbar.Brand>
        <Nav className="mr-auto">
          <NavDropdown title="Accueil" id="nav-accueil-dropdown">
            <NavDropdown.Item href="reseau">Notre réseau</NavDropdown.Item>
            <NavDropdown.Item href="bibliotheques">Nos Bibliothèques</NavDropdown.Item>
            <NavDropdown.Item href="equipes">Nos équipes</NavDropdown.Item>
          </NavDropdown>
          <Nav.Link href="#livres">Nos livres</Nav.Link>
        </Nav>
        <DropdownButton menuAlign="right" title="Grégory Gautier" variant="secondary">
          <Dropdown.Item eventKey="1">Mon compte</Dropdown.Item>
          <Dropdown.Item eventKey="2">Mes emprunts</Dropdown.Item>
          <Dropdown.Divider />
          <Dropdown.Item eventKey="3">Se déconnecter</Dropdown.Item>
        </DropdownButton>
      </Navbar>
      <Container fluid>
        {routers.map((router, index) => {
          if (router.exact) {
            return <Route key={`router${index}`} path={router.path} exact component={router.component} />;
          }
          return <Route key={`router${index}`} path={router.path} component={router.component} />;
        })}
      </Container>
    </>
  );
}

export default App;
