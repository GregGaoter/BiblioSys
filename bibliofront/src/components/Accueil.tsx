import { Galleria } from "primereact/galleria";
import React from "react";
import borrowBooksTiny from "../ressources/images/borrow-book-tiny.jpg";
import borrowBooks from "../ressources/images/borrow-book.jpg";
import libraryTiny from "../ressources/images/library-tiny.jpg";
import library from "../ressources/images/library.jpg";
import searchBooksTiny from "../ressources/images/search-book-tiny.jpg";
import searchBooks from "../ressources/images/search-book.jpg";

interface Image {
  itemImageSrc: string;
  thumbnailImageSrc: string;
  title: string;
  description: string;
}

export const Accueil = () => {
  const libraryImages: Image = {
    itemImageSrc: library,
    thumbnailImageSrc: libraryTiny,
    title: "Bibliothèques",
    description: "Découvrez vos bibliothèques modernes.",
  };
  const searchBooksImages: Image = {
    itemImageSrc: searchBooks,
    thumbnailImageSrc: searchBooksTiny,
    title: "Rechercher",
    description: "Trouvez vos livres parmis un vaste choix de rayons.",
  };
  const borrowBooksImages: Image = {
    itemImageSrc: borrowBooks,
    thumbnailImageSrc: borrowBooksTiny,
    title: "Emprunter",
    description: "Empruntez vos livres en un simple click.",
  };
  const images = [libraryImages, searchBooksImages, borrowBooksImages];

  const itemTemplate = (item: Image) => (
    <img src={item.itemImageSrc} alt={item.description} style={{ width: "100%", display: "block" }} />
  );
  const thumbnailTemplate = (item: Image) => (
    <img src={item.thumbnailImageSrc} alt={item.description} style={{ display: "block" }} />
  );
  const caption = (item: Image) => (
    <>
      <h4 className="p-mb-2">{item.title}</h4>
      <p>{item.description}</p>
    </>
  );

  return (
    <div className="p-d-flex p-jc-center">
      <Galleria
        value={images}
        numVisible={3}
        item={itemTemplate}
        thumbnail={thumbnailTemplate}
        caption={caption}
        style={{ maxWidth: "800px" }}
      />
    </div>
  );
};
