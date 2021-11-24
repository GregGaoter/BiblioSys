# Application web Biblillonie

## Projet

### Présentation

La Direction du Système d’Information (DSI) est contactée par le service culturel de la ville qui souhaite moderniser la gestion de ses bibliothèques.

### Contexte

La DSI est en charge de tous les traitements numériques pour la mairie et les structures qui lui sont rattachées, comme la gestion du site web de la ville par exemple.

### Objectif

L'objectif de ce projet est de réaliser les outils numériques à destination des bibliothèques de la ville.

## Installation

### Prérequis

| Description | Technologie | Version |
| --- | --- | --- |
| Langage | Java | 14 |
| Production logicielle | Maven | 3.6.3 |
| Code versionning | Git | 2.28.0 |
| Base de données | PostgreSQL | 12.4 |
| Serveur de ressources statiques | Nginx | 1.18 |
| Serveur web | NodeJs | 14.15.3 |
| Serveur SMTP | Fake SMTP | 2.0 |

### Code source

1.	Installer Git sur votre machine.
1.  Depuis le terminal `Git Bash` de Git, se placer dans le répertoire où vous souhaitez cloner le projet, par exemple :  
    `cd /c/Users/gregg/oc/projets`
1.  Cloner le dépôt GitHub du projet :  
    `git clone https://github.com/GregGaoter/BiblioSys.git`

### Base de données

1.	Installer PostgreSQL sur votre machine.
1.  Depuis un terminal Windows, se placer à la racine du répertoire `bin` de PostgreSQL :  
    `cd "C:\Program Files\PostgreSQL\12\bin"`
1.  Entrer dans le terminal `psql` de PostgreSQL :  
    `psql -U postgres`
1.  Créer la base de données :  
    `CREATE DATABASE db_bibliosys ENCODING UTF8;`
1.  Vérifier que la base de données à bien été créée en affichant la liste des bases de données :  
    `\l`
1.  Quitter le terminal `psql` :  
    `\q`
1.  Restaurer le dump `dump.sql` dans la base de données `db_bibliosys` qui vient d'être créée. Le dump se trouve dans le répertoire `doc/scripts-sql` du projet :  
    `psql -U postgres db_bibliosys < [PROJECT_PATH]\doc\scripts-sql\dump.sql`
1.  Entrer dans le terminal `psql` de PostgreSQL :  
    `psql -U postgres`
1.  Se connecter à la base de données `db_bibliosys` :  
    `\c db_bibliosys`
1.  Vérifier que les tables ont bien été créées :  
    `\dt`

### Serveur de ressources statiques

Nginx fournit à l'application les images des couvertures des livres.

1.	Installer Nginx sur votre machine.
1.  Copier les images du répertoire `doc/images` du projet.
1.  Depuis un terminal Windows, se placer à la racine de Nginx :  
    `cd [NGINX_PATH]`
1.  Créer un répertoire livres dans Nginx :  
    `mkdir html\img\livres`
1.  Coller les images des couvertures des livres dans le répertoire `html\img\livres` de Nginx.
1.  Lancer Nginx : double cliquez sur l'exécutable `nginx.exe`.
1.  Vérifier que Nginx soit bien installé. Taper dans votre navigateur Web l'URL :  
    `localhost:80`  
    Si vous voyez la page "Welcome to nginx!", Nginx est bien installé.

### Serveur SMTP

Fake SMTP, utilisé pour le développement, permet de consulter les emails qui ont été émis.

1.	Installer Fake SMTP sur votre machine.
1.	A la racine de Fake SMTP, lancer le `.jar` exécutable.
1.	Cliquer sur `Start server`.

## Exécution pour le développement

### Prérequis

Lancer `Nginx` :
1.  Aller dans le répertoire d'installation de `Nginx`.
2.  Lancer `nginx.exe`.

Lancer `Fake SMTP` :

1.  Aller dans le répertoire d'installation de `Fake SMTP`.
2.  Lancer le `.jar` exécutable.
3.  Cliquer sur `Start server`.

### Backend

#### Lancement depuis un IDE

1.  Importer le projet Maven. Par exemple pour Eclipse :  
    `File -> Import... -> Maven -> Existing Maven Projects`
2.  Lancer le backend :  
    `BiblioSys/biblioback/src/main/java/com/dsi/bibliosys/biblioback`  
    `Click droit sur BiblioBackApplication.java -> Run As -> Java Application`

#### Lancement en tant que package JAR

1.  Depuis un terminal Windows, se placer à la racine du backend :  
    `cd [BACKEND_PATH]`
1.  Packager le backend dans un JAR :  
    `mvn clean package`
1.  Lancer le backend :  
    `java -jar target/biblioback-0.0.1-SNAPSHOT.jar`

#### Lancement en utilisant le plugin Maven

1.  Depuis un terminal Windows, se placer à la racine du backend :  
    `cd [BACKEND_PATH]`
1.  Lancer le backend :  
    `mvn spring-boot:run`

### Batch

#### Lancement depuis un IDE

1.  Importer le projet Maven. Par exemple pour Eclipse :  
    `File -> Import... -> Maven -> Existing Maven Projects`
2.  Lancer le batch :  
    `BiblioSys/bibliobatch/src/main/java/com/dsi/bibliosys/bibliobatch`  
    `Click droit sur BiblioBatchApplication.java -> Run As -> Java Application`

#### Lancement en tant que package JAR

1.  Depuis un terminal Windows, se placer à la racine du batch :  
    `cd [BATCH_PATH]`
1.  Packager le batch dans un JAR :  
    `mvn clean package`
1.  Lancer le batch :  
    `java -jar target/bibliobatch-0.0.1-SNAPSHOT.jar`

#### Lancement en utilisant le plugin Maven

1.  Depuis un terminal Windows, se placer à la racine du batch :  
    `cd [BATCH_PATH]`
1.  Lancer le batch :  
    `mvn spring-boot:run`

### Frontend

1.  Depuis un terminal Windows, se placer à la racine du frontend :  
    `cd [FRONTEND_PATH]`
1.  Installer les dépendences :  
    `npm i`
1.  Lancer le frontend :  
    `npm start`

### Exécution

1.  Depuis un navigateur Web, accéder à la page d'accueil :  
    `localhost:3000/`

## Comptes utilisateurs

Le compte utilisateur suivant est fourni pour tester l'application :

| Adresse email | Mot de passe | Rôle |
| --- | --- | --- |
| amargerison0@toplist.cz | PPnU2y2S5A | USAGER |


