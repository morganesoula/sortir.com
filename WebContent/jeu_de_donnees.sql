INSERT INTO PARTICIPANTS(nom, prenom, pseudo, motDePasse, telephone, email, administrateur, actif, sites_idSite) 
VALUES ('Soula', 'Morgane', 'Morgane', '$2a$10$sWjrvrki7snrc0BZ9GGyzetGyKZ5X/O83kQzLOIMvNT4yjiJ3VjBu', '0201040503', 'test@mail.com', 0, 0, 1);

INSERT INTO PARTICIPANTS(nom, prenom, pseudo, motDePasse, telephone, email, administrateur, actif, sites_idSite) 
VALUES ('Aligand', 'Line', 'Lily', '$2a$10$7f9swraKwjU3cHxw6HXdW.hhrYv8I90OorH5XuLdcdTyT9/tAkfMi', '0000000002', 'a@a.com', 0, 0, 1);

INSERT INTO PARTICIPANTS(nom, prenom, pseudo, motDePasse, telephone, email, administrateur, actif, sites_idSite) 
VALUES ('Test', 'Test2', 'admin', '$2a$10$TztHmn.afVdZYhOjt49Y8Odk5biBW3eKaNQ4r4NmrP7SNrdx8gbA2', '0000000003', 'admin@admin.com', 1, 1, 2);

INSERT INTO PARTICIPANTS(nom, prenom, pseudo, motDePasse, telephone, email, administrateur, actif, sites_idSite) 
VALUES ('TestNom', 'TestPrenom', 'test', '$2a$10$TztHmn.afVdZYhOjt49Y8Odk5biBW3eKaNQ4r4NmrP7SNrdx8gbA2', '0000000003', 'admin@admin.com', 0, 1, 2);

INSERT INTO PARTICIPANTS(nom, prenom, pseudo, motDePasse, telephone, email, administrateur, actif, sites_idSite) 
VALUES ('TestNom', 'TestPrenom', 'petit', '$2a$10$TztHmn.afVdZYhOjt49Y8Odk5biBW3eKaNQ4r4NmrP7SNrdx8gbA2', '0000000003', 'admin@admin.com', 0, 1, 2);


INSERT INTO SITES(nom_site) VALUES ('CHARTRES');
INSERT INTO SITES(nom_site) VALUES ('SAINT HERBLAIN');
INSERT INTO SITES(nom_site) VALUES ('NIORT');


INSERT INTO ETATS(libelle) VALUES ('Créée');
INSERT INTO ETATS(libelle) VALUES ('Ouverte');
INSERT INTO ETATS(libelle) VALUES ('Annulée');

INSERT INTO VILLES(nom_ville, code_postal) VALUES ('Rennes', '35000');
INSERT INTO VILLES(nom_ville, code_postal) VALUES ('Nantes', '44000');
INSERT INTO VILLES(nom_ville, code_postal) VALUES ('Vannes', '56000');
INSERT INTO VILLES(nom_ville, code_postal) VALUES ('Les Epesses', '85590');


INSERT INTO LIEUX(nom_lieu, rue, latitude, longitude, villes_idVille) VALUES ('Zone à Rennes', '1 rue des peupliers', 48.117266, -1.677793, 1);
INSERT INTO LIEUX(nom_lieu, rue, latitude, longitude, villes_idVille) VALUES ('Île de Nantes', '1 place des marchands', 47.218371, -1.553621, 2);
INSERT INTO LIEUX(nom_lieu, rue, latitude, longitude, villes_idVille) VALUES ('Puy du fou', 'lieu-dit', 46.889602, -0.928116, 4);


INSERT INTO SORTIES(nom, datedebut, duree, datecloture, nbinscriptionsmax, descriptioninfos, urlPhoto, organisateur, lieux_idLieu, etats_idEtat) 
VALUES('Marché Gourmand', 2018-09-25 00:00:00, 60, 2018-09-20 00:00:00, 10, 'Joli marché sur la place des lices', 'rennes.jpg', 2, 1, 3);

INSERT INTO SORTIES(nom, datedebut, duree, datecloture, nbinscriptionsmax, descriptioninfos, urlPhoto, organisateur, lieux_idLieu, etats_idEtat) 
VALUES('Hangar à bananes', 2018-08-10, 30, 2018-08-01 00:00:00, 5, 'Seconde sortie', 'nantes.jpg', 2, 2, 1);

INSERT INTO SORTIES(nom, datedebut, duree, datecloture, nbinscriptionsmax, descriptioninfos, urlPhoto, organisateur, lieux_idLieu, etats_idEtat) 
VALUES('Puy-du-Fou', 2019-03-20, 360, 2019-01-10 00:00:00, 50, 'Sortie exclusive au Puy-du-fou', 'puyDuFou.jpg', 3, 3, 2);

DELETE FROM SORTIES WHERE idSortie=11;

SELECT * FROM PARTICIPANTS;
SELECT * FROM SITES;
SELECT * FROM ETATS;
SELECT * FROM VILLES;
SELECT * FROM LIEUX;
SELECT * FROM SORTIES;