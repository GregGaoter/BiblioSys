-- ===================================================
-- lieu
-- ===================================================
INSERT INTO lieu (id, region, departement, code_postal, ville) VALUES (1, 'Région 1', 'Département 1', '10000', 'Ville 1');
INSERT INTO lieu (id, region, departement, code_postal, ville) VALUES (2, 'Région 2', 'Département 2', '20000', 'Ville 2');
INSERT INTO lieu (id, region, departement, code_postal, ville) VALUES (3, 'Région 3', 'Département 3', '30000', 'Ville 3');

-- ===================================================
-- adresse
-- ===================================================
INSERT INTO adresse (id, numero_rue, rue, lieu_id) VALUES (1, 1, 'Rue 1', 1);
INSERT INTO adresse (id, numero_rue, rue, lieu_id) VALUES (2, 2, 'Rue 2', 2);
INSERT INTO adresse (id, numero_rue, rue, lieu_id) VALUES (3, 3, 'Rue 3', 3);

-- ===================================================
-- bibliotheque
-- ===================================================
INSERT INTO bibliotheque (id, nom, adresse_id, description, image_file_name) VALUES (1, 'Bibliothèque 1', 1, 'Description 1', 'image1.jpg');
INSERT INTO bibliotheque (id, nom, adresse_id, description, image_file_name) VALUES (2, 'Bibliothèque 2', 2, 'Description 2', 'image2.jpg');
INSERT INTO bibliotheque (id, nom, adresse_id, description, image_file_name) VALUES (3, 'Bibliothèque 3', 3, 'Description 3', 'image3.jpg');

-- ===================================================
-- rayon
-- ===================================================
INSERT INTO public.rayon (id, nom) VALUES (1, 'Rayon 1');
INSERT INTO public.rayon (id, nom) VALUES (2, 'Rayon 2');
INSERT INTO public.rayon (id, nom) VALUES (3, 'Rayon 3');

-- ===================================================
-- genre
-- ===================================================
INSERT INTO genre (id, nom, rayon_id) VALUES (1, 'Genre 1', 1);
INSERT INTO genre (id, nom, rayon_id) VALUES (2, 'Genre 2', 2);
INSERT INTO genre (id, nom, rayon_id) VALUES (3, 'Genre 3', 3);

-- ===================================================
-- editeur
-- ===================================================
INSERT INTO editeur (id, nom) VALUES (1, 'Editeur 1');
INSERT INTO editeur (id, nom) VALUES (2, 'Editeur 2');
INSERT INTO editeur (id, nom) VALUES (3, 'Editeur 3');

-- ===================================================
-- collection
-- ===================================================
INSERT INTO collection (id, nom) VALUES (1, 'Collection 1');
INSERT INTO collection (id, nom) VALUES (2, 'Collection 2');
INSERT INTO collection (id, nom) VALUES (3, 'Collection 3');

-- ===================================================
-- livre
-- ===================================================
INSERT INTO livre (id, bibliotheque_id, genre_id, nom_image, titre, editeur_id, collection_id, date_parution, dimension, nb_pages, ean13, nb_exemplaires, resume) VALUES (1, 1, 1, 'image-1.png', 'Titre 1', 1, 1, '2000-01-01 00:00:00', '1 x 1 x 1 cm', 111, '1111111111111', 1, 'Résumé 1');
INSERT INTO livre (id, bibliotheque_id, genre_id, nom_image, titre, editeur_id, collection_id, date_parution, dimension, nb_pages, ean13, nb_exemplaires, resume) VALUES (2, 2, 2, 'image-2.png', 'Titre 2', 2, 2, '2000-02-02 00:00:00', '2 x 2 x 2 cm', 222, '2222222222222', 2, 'Résumé 2');
INSERT INTO livre (id, bibliotheque_id, genre_id, nom_image, titre, editeur_id, collection_id, date_parution, dimension, nb_pages, ean13, nb_exemplaires, resume) VALUES (3, 3, 3, 'image-3.png', 'Titre 3', 3, 3, '2000-03-03 00:00:00', '3 x 3 x 3 cm', 333, '3333333333333', 3, 'Résumé 3');

-- ===================================================
-- auteur
-- ===================================================
INSERT INTO auteur (id, prenom_nom) VALUES (1, 'Auteur 1');
INSERT INTO auteur (id, prenom_nom) VALUES (2, 'Auteur 2');
INSERT INTO auteur (id, prenom_nom) VALUES (3, 'Auteur 3');

-- ===================================================
-- identifiant
-- ===================================================
INSERT INTO identifiant (id, email, mot_de_passe, is_actif) VALUES (1, 'email1@domaine1.fr', 'mdp1', TRUE);
INSERT INTO identifiant (id, email, mot_de_passe, is_actif) VALUES (2, 'email2@domaine2.fr', 'mdp2', FALSE);
INSERT INTO identifiant (id, email, mot_de_passe, is_actif) VALUES (3, 'email3@domaine3.fr', 'mdp3', TRUE);

-- ===================================================
-- usager
-- ===================================================
INSERT INTO usager (id, prenom, nom, date_naissance, identifiant_id, adresse_id) VALUES (1, 'Prénom 1', 'Nom 1', '2000-01-01 00:00:00', 1, 1);
INSERT INTO usager (id, prenom, nom, date_naissance, identifiant_id, adresse_id) VALUES (2, 'Prénom 2', 'Nom 2', '2000-02-02 00:00:00', 2, 2);
INSERT INTO usager (id, prenom, nom, date_naissance, identifiant_id, adresse_id) VALUES (3, 'Prénom 3', 'Nom 3', '2000-03-03 00:00:00', 3, 3);

-- ===================================================
-- role
-- ===================================================
INSERT INTO role (id, nom) VALUES (1, 'ROLE1');
INSERT INTO role (id, nom) VALUES (2, 'ROLE2');
INSERT INTO role (id, nom) VALUES (3, 'ROLE3');

-- ===================================================
-- ecriture_livre
-- ===================================================
INSERT INTO ecriture_livre (id, livre_id, auteur_id) VALUES (1, 1, 1);
INSERT INTO ecriture_livre (id, livre_id, auteur_id) VALUES (2, 2, 2);
INSERT INTO ecriture_livre (id, livre_id, auteur_id) VALUES (3, 3, 3);

-- ===================================================
-- favoris
-- ===================================================
INSERT INTO favoris (id, usager_id, livre_id) VALUES (1, 1, 1);
INSERT INTO favoris (id, usager_id, livre_id) VALUES (2, 2, 2);
INSERT INTO favoris (id, usager_id, livre_id) VALUES (3, 3, 3);

-- ===================================================
-- personnel
-- ===================================================
INSERT INTO personnel (id, prenom, nom, date_naissance, identifiant_id, adresse_id, bibliotheque_id) VALUES (1, 'Prénom 1', 'Nom 1', '2000-01-01 00:00:00', 1, 1, 1);
INSERT INTO personnel (id, prenom, nom, date_naissance, identifiant_id, adresse_id, bibliotheque_id) VALUES (2, 'Prénom 2', 'Nom 2', '2000-02-02 00:00:00', 2, 2, 2);
INSERT INTO personnel (id, prenom, nom, date_naissance, identifiant_id, adresse_id, bibliotheque_id) VALUES (3, 'Prénom 3', 'Nom 3', '2000-03-03 00:00:00', 3, 3, 3);

-- ===================================================
-- profil
-- ===================================================
INSERT INTO profil (id, identifiant_id, role_id) VALUES (1, 1, 1);
INSERT INTO profil (id, identifiant_id, role_id) VALUES (2, 2, 2);
INSERT INTO profil (id, identifiant_id, role_id) VALUES (3, 3, 3);

-- ===================================================
-- pret
-- ===================================================
INSERT INTO pret (id, usager_id, livre_id, date_pret, nb_prolongations, nb_relances) VALUES (1, 1, 1, '2000-01-01 00:00:00', 0, 0);
INSERT INTO pret (id, usager_id, livre_id, date_pret, nb_prolongations, nb_relances) VALUES (2, 2, 2, '2000-02-02 00:00:00', 0, 1);
INSERT INTO pret (id, usager_id, livre_id, date_pret, nb_prolongations, nb_relances) VALUES (3, 3, 3, '2000-03-03 00:00:00', 1, 0);