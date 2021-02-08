import React from "react";
import Carousel from "react-bootstrap/Carousel";
import Container from "react-bootstrap/Container";
import borrowBooks from "../ressources/images/borrow-book.jpg";
import library from "../ressources/images/library.jpg";
import logo from "../ressources/images/logo-128-128.png";
import searchBooks from "../ressources/images/search-book.jpg";

export const Accueil = () => {
  return (
    <Container>
      <Carousel className="app-card">
        <Carousel.Item>
          <img className="d-block w-100" src={library} alt="library" />
          <Carousel.Caption>
            <h3>Bibliothèques</h3>
            <p>Découvrez nos bibliothèques modernes.</p>
          </Carousel.Caption>
        </Carousel.Item>
        <Carousel.Item>
          <img className="d-block w-100" src={searchBooks} alt="searchBooks" />
          <Carousel.Caption>
            <h3>Rechercher</h3>
            <p>Trouvez nos livres parmis un vaste choix de rayons.</p>
          </Carousel.Caption>
        </Carousel.Item>
        <Carousel.Item>
          <img className="d-block w-100" src={borrowBooks} alt="searchBooks" />
          <Carousel.Caption>
            <h3>Emprunter</h3>
            <p>Empruntez nos livres en un simple click.</p>
          </Carousel.Caption>
        </Carousel.Item>
      </Carousel>
      <img
        src={logo}
        alt="logo"
        style={{
          paddingTop: "20px",
          display: "block",
          marginLeft: "auto",
          marginRight: "auto",
        }}
      />
    </Container>
  );
};
