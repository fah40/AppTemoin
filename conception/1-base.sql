CREATE DATABASE gestion_vol;
\c gestion_vol;

CREATE TYPE role_type AS ENUM ('admin', 'client', 'user');
CREATE TYPE siege_type AS ENUM ('economique', 'business');
CREATE EXTENSION IF NOT EXISTS pgcrypto;
-- Création des tables


-- Table des utilisateurs (Clients et Administrateurs)
CREATE TABLE users (
    id serial PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role role_type NOT NULL -- Utilisation du type ENUM défini
);

-- Table des villes desservies
CREATE TABLE ville (
    id serial PRIMARY KEY,
    nom VARCHAR(100) UNIQUE NOT NULL
);

-- Table des avions
CREATE TABLE avion (
    id serial PRIMARY KEY,
    modele VARCHAR(100) NOT NULL,
    nbr_siege_economique INT NOT NULL,
    nbr_siege_business INT NOT NULL
);

CREATE SEQUENCE vol_ref START WITH 1 INCREMENT BY 1;
-- Table des vols
CREATE TABLE vol (
    id serial PRIMARY KEY,
    numero_vol VARCHAR(50) UNIQUE NOT NULL,
    id_avion INT NOT NULL,
    id_ville_depart INT NOT NULL,
    id_ville_arrivee INT NOT NULL,
    date_depart TIMESTAMP NOT NULL, -- Utilisation de TIMESTAMP au lieu de DATETIME
    date_arrivee TIMESTAMP NOT NULL, -- Utilisation de TIMESTAMP au lieu de DATETIME
    prix_economique DECIMAL(12,2) NOT NULL,
    prix_business DECIMAL(12,2) NOT NULL,
    date_limite_reservation TIMESTAMP NOT NULL, -- Utilisation de TIMESTAMP au lieu de DATETIME
    disponible BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (id_avion) REFERENCES avion(id),
    FOREIGN KEY (id_ville_depart) REFERENCES ville(id),
    FOREIGN KEY (id_ville_arrivee) REFERENCES ville(id)
);

-- Table des sièges
CREATE TABLE siege (
    id serial PRIMARY KEY,
    id_vol INT NOT NULL,
    numero_siege VARCHAR(10) NOT NULL,
    type siege_type NOT NULL, -- Utilisation du type ENUM défini
    est_reserve BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_vol) REFERENCES vol(id)
);

-- Table des réservations
CREATE TABLE reservation (
    id serial PRIMARY KEY,
    id_user INT NOT NULL,
    id_vol INT NOT NULL,
    id_siege INT NOT NULL UNIQUE,
    prix_final DECIMAL(12,2) NOT NULL,
    date_reservation TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Utilisation de TIMESTAMP au lieu de DATETIME
    FOREIGN KEY (id_user) REFERENCES users(id),
    FOREIGN KEY (id_vol) REFERENCES vol(id),
    FOREIGN KEY (id_siege) REFERENCES siege(id)
);

-- Table des promotions
CREATE TABLE promotion (
    id serial PRIMARY KEY,
    id_vol INT NOT NULL,
    reduction DECIMAL(12,2) NOT NULL,
    nombre_max_reservations INT NOT NULL DEFAULT 3,
    FOREIGN KEY (id_vol) REFERENCES vol(id)
);

-- Table des paramètres de réservation
CREATE TABLE param_vol (
    id serial PRIMARY KEY,
    id_vol INT NOT NULL,
    date_limite_reservation TIMESTAMP NOT NULL, -- Utilisation de TIMESTAMP au lieu de DATETIME
    annulation_possible BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (id_vol) REFERENCES vol(id)
);
