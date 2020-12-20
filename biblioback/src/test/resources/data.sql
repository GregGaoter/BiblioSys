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
INSERT INTO bibliotheque (id, nom, adresse_id) VALUES (1, 'Bibliothèque 1', 1);
INSERT INTO bibliotheque (id, nom, adresse_id) VALUES (2, 'Bibliothèque 2', 2);
INSERT INTO bibliotheque (id, nom, adresse_id) VALUES (3, 'Bibliothèque 3', 3);

-- ===================================================
-- genre
-- ===================================================
INSERT INTO genre (id, nom) VALUES (1, 'Poésie');

-- ===================================================
-- editeur
-- ===================================================
INSERT INTO editeur (id, nom) VALUES (1, 'Editions Gallimard');

-- ===================================================
-- collection
-- ===================================================
INSERT INTO collection (id, nom) VALUES (1, 'Le livre de poche');

-- ===================================================
-- livre
-- ===================================================
INSERT INTO livre (id, bibliotheque_id, genre_id, nom_image, titre, editeur_id, collection_id, date_parution, dimension, nb_pages, ean13, nb_exemplaires, resume) VALUES (1, 1, 1, 'les-fleurs-du-mal.png', 'Les fleurs du mal', 1, 1, '1997-09-15 00:00:00', '17.8 x 11.1 x 1.4 cm', 286, '9782253007104', 4, 'Avec Les Fleurs du Mal commence la poésie moderne.');

-- ===================================================
-- auteur
-- ===================================================
INSERT INTO auteur (id, prenom_nom) VALUES (1, 'Auteur 1');
INSERT INTO auteur (id, prenom_nom) VALUES (2, 'Auteur 2');
INSERT INTO auteur (id, prenom_nom) VALUES (3, 'Auteur 3');

-- ===================================================
-- identifiant
-- ===================================================
INSERT INTO identifiant (id, email, mot_de_passe, is_actif) VALUES (1, 'amargerison0@toplist.cz', '8Wva2JDb', TRUE);

-- ===================================================
-- usager
-- ===================================================
INSERT INTO usager (id, prenom, nom, date_naissance, identifiant_id, adresse_id) VALUES (1, 'Liliane', 'Philibert', '1965-11-13 00:00:00', 1, 1);

-- ===================================================
-- role
-- ===================================================
INSERT INTO role (id, nom) VALUES (1, 'ADMIN');