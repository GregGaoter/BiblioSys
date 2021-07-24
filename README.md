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
| Production logicielle | Maven | 3.6.1 |
| Code versionning | Git | 2.22.0 |
| Base de données | PostgreSQL | 11.4.3 |
| Serveur de ressources statiques | Nginx | 1.17.8 |

### Application

1.	Installer Git 2.22+ sur votre machine.
1.  Depuis le terminal `Git Bash` de Git, se placer dans le répertoire où vous souhaitez cloner le projet, par exemple :  
    `cd /c/Users/gregg/oc/projets`
1.  Cloner le dépôt GitHub du projet :  
    `git clone https://github.com/GregGaoter/BiblioSys.git`

### Base de données

1.	Installer PostgreSQL 11.4+ sur votre machine.
1.  Depuis un terminal Windows, se placer à la racine du répertoire `bin` de PostgreSQL :  
    `cd "C:\Program Files\PostgreSQL\11\bin"`
1.  Entrer dans le terminal `psql` de PostgreSQL :  
    `psql -U postgres`
1.  Créer la base de données :  
    `CREATE DATABASE db_bibliosys ENCODING UTF8;`
1.  Vérifier que la base de données à bien été créée en affichant la liste des bases de données :  
    `\l`
1.  Quitter le terminal `psql` :  
    `\q`
1.  Restaurer le dump `db_bibliosys.sql` dans la base de données `db_bibliosys` qui vient d'être créée. Le dump se trouve dans le répertoire `doc/scripts` du projet :  
    `psql -U postgres db_bibliosys < [PROJECT_PATH]\doc\scripts\db_bibliosys.sql`
1.  Entrer dans le terminal `psql` de PostgreSQL :  
    `psql -U postgres`
1.  Se connecter à la base de données `db_bibliosys` :  
    `\c db_bibliosys`
1.  Vérifier que les tables ont bien été créées :  
    `\dt`

### Serveur de ressources statiques

Nginx fournit à l'application les images des couvertures des livres.

1.	Installer Nginx 1.17+ sur votre machine.
1.  Copier les images du répertoire `BiblioSys/doc/images` du projet.
1.  Depuis un terminal Windows, se placer à la racine de Nginx :  
    `cd [NGINX_PATH]`
1.  Créer un répertoire livres dans Nginx :  
    `mkdir html\img\livres`
1.  Coller les images des couvertures des livres dans le répertoire `html\img\livres` de Nginx.
1.  Lancer Nginx : double cliquez sur l'exécutable `nginx.exe`.
1.  Vérifier que Nginx soit bien installé. Taper dans votre navigateur Web l'URL :  
    `localhost:80`  
    Si vous voyez la page "Welcome to nginx!", Nginx est bien installé.

## Exécution pour le développement

### Prérequis

Activer le profile `dev` de l'application :
1.  Ouvrir le fichier `application.properties` à la racine de `src/main/resources`.
2.  La propriété `spring.profiles.active=dev` doit être active.  
    La propriété `#spring.profiles.active=prod` doit être en commentaire.

Lancer `Nginx` :
1.  Aller dans le répertoire d'installation de `Nginx`.
2.  Lancer `nginx.exe`.

### Exécution depuis un IDE

1.  Importer le projet Maven. Par exemple pour Eclipse :  
    `File -> Import... -> Maven -> Existing Maven Projects`
2.  Exécuter l'application :  
    `BiblioSys/biblioback/src/main/java/com/dsi/bibliosys/biblioback`  
    `Click droit sur BiblioBackApplication.java -> Run As -> Java Application`
3.  Depuis un navigateur Web, accéder à la page d'accueil :  
    `localhost:8080/`

### Exécution en tant que package JAR

1.  Depuis un terminal Windows, se placer à la racine du projet :  
    `cd [PROJECT_PATH]`
1.  Packager l'application dans un JAR :  
    `mvn clean package`
1.  Exécuter l'application :  
    `java -jar target/bibliosys-0.0.1-SNAPSHOT.jar.jar`
1.  Depuis un navigateur Web, accéder à la page d'accueil :  
    `localhost:8080/`

### Exécution en utilisant le plugin Maven

1.  Depuis un terminal Windows, se placer à la racine du projet :  
    `cd [PROJECT_PATH]`
1.  Exécuter l'application :  
    `mvn spring-boot:run`
1.  Depuis un navigateur Web, accéder à la page d'accueil :  
    `localhost:8080/`

## Exécution pour la production

### Comptes utilisateurs

Les comptes utilisateurs suivants sont fournis pour tester l'application avec différents rôles :

| Adresse email | Mot de passe | Rôle |
| --- | --- | --- |
| fabien.poulin52@protonmail.fr | ZnkVRvZVe | Ami |
| segolene.deniau6@lycos.fr | dIcgxCy59CNVEsLW | Administrateur |



