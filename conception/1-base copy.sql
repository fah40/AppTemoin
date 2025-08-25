CREATE DATABASE gestion_vol;
\c gestion_vol;

CREATE EXTENSION IF NOT EXISTS pgcrypto;


CREATE TABLE users (
    id serial PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(30) NOT NULL
);

CREATE TABLE role (
    id serial PRIMARY KEY,
    nom VARCHAR(100) NOT NULL
);

CREATE TABLE user_role (
    id serial PRIMARY KEY,
    id_user INT NOT NULL,
    id_role INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_user) REFERENCES users(id),
    FOREIGN KEY (id_role) REFERENCES role(id)
);


CREATE TABLE ville (
    id serial PRIMARY KEY,
    nom VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE avion (
    id serial PRIMARY KEY,
    modele VARCHAR(100) NOT NULL,
    nbr_siege_economique INT NOT NULL,
    nbr_siege_business INT NOT NULL
);

CREATE SEQUENCE vol_ref START WITH 1 INCREMENT BY 1;

CREATE TABLE vol (
    id serial PRIMARY KEY,
    numero_vol VARCHAR(50) UNIQUE NOT NULL,
    id_avion INT NOT NULL,
    id_ville_depart INT NOT NULL,
    id_ville_arrivee INT NOT NULL,
    date_depart TIMESTAMP NOT NULL,
    date_arrivee TIMESTAMP NOT NULL,
    prix_economique DECIMAL(12,2) NOT NULL,
    prix_business DECIMAL(12,2) NOT NULL,
    date_limite_reservation TIMESTAMP NOT NULL,
    reduction DECIMAL(12,2),
    insert_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    mx_eco INT,
    mx_bus INT,
    disponible BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (id_avion) REFERENCES avion(id),
    FOREIGN KEY (id_ville_depart) REFERENCES ville(id),
    FOREIGN KEY (id_ville_arrivee) REFERENCES ville(id)
);

CREATE TABLE histo_prix (
    id serial PRIMARY KEY
    id_vol INT NOT NULL,
    prix_economique DECIMAL(12,2) NOT NULL,
    prix_business DECIMAL(12,2) NOT NULL,
    date_debut TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_fin TIMESTAMP DEFAULT NULL,
    FOREIGN KEY (id_vol) REFERENCES vol(id)
)

CREATE TABLE configuration (
    cle VARCHAR(200) PRIMARY KEY,
    valeur TEXT 
);

CREATE TABLE siege_type (
    id serial PRIMARY KEY,
    nom VARCHAR(50) check(nom = 'economique' or nom = 'business')
);

CREATE TABLE siege (
    id serial PRIMARY KEY,
    id_vol INT NOT NULL,
    id_type INT NOT NULL,
    est_reserve BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_type) REFERENCES siege_type(id),
    FOREIGN KEY (id_vol) REFERENCES vol(id)
);

CREATE TABLE reservation (
    id serial PRIMARY KEY,
    id_user INT NOT NULL,
    id_type INT NOT NULL,
    id_vol INT NOT NULL,
    nombreAdulte INT NOT NULL,
    nombreEnfant INT NOT NULL,
    file TEXT,
    date_reservation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    est_paye BOOLEAN DEFAULT FALSE,
    date_paye TIMESTAMP DEFAULT NULL,
    montant DECIMAL(12,2) DEFAULT NULL,
    FOREIGN KEY (id_user) REFERENCES users(id),
    FOREIGN KEY (id_type) REFERENCES siege_type(id),
    FOREIGN KEY (id_vol) REFERENCES vol(id)
);

CREATE TABLE billet (
    id serial PRIMARY KEY,
    id_user INT NOT NULL,
    id_vol INT NOT NULL,
    id_reservation INT NOT NULL,
    id_type INT NOT NULL,
    prix_final DECIMAL(12,2) NOT NULL,
    FOREIGN KEY (id_user) REFERENCES users(id),
    FOREIGN KEY (id_reservation) REFERENCES reservation(id),
    FOREIGN KEY (id_type) REFERENCES siege_type(id)
);