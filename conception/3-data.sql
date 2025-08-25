INSERT INTO ville (nom) VALUES 
('Paris'), 
('New York'), 
('Dubai'), 
('Tokyo'), 
('Los Angeles');

INSERT INTO avion (modele, nbr_siege_economique, nbr_siege_business) VALUES
('Airbus A320', 180, 20),
('Boeing 777', 250, 50),
('Embraer E195', 120, 10);

INSERT INTO siege_type (nom) VALUES
('economique'),
('business');

INSERT INTO configuration (cle,valeur) VALUES ('enfant','50');

INSERT INTO vol (numero_vol, id_avion, id_ville_depart, id_ville_arrivee, date_depart, date_arrivee, prix_economique, prix_business, date_limite_reservation)
VALUES 
-- Vol 1 : Paris -> New York
('AF001', 1, 1, 2, '2025-12-01 08:00:00', '2025-12-01 16:00:00', 500.00, 1200.00, '2025-11-30 23:59:59'),
-- Vol 2 : New York -> Dubai
('EK202', 2, 2, 3, '2025-12-02 10:00:00', '2025-12-02 22:00:00', 600.00, 1500.00, '2025-12-01 23:59:59'),
-- Vol 3 : Dubai -> Tokyo
('JL123', 3, 3, 4, '2025-12-03 12:00:00', '2025-12-03 20:00:00', 700.00, 1800.00, '2025-12-02 23:59:59'),
-- Vol 4 : Tokyo -> Los Angeles
('AA456', 1, 4, 5, '2025-12-04 14:00:00', '2025-12-04 22:00:00', 800.00, 2000.00, '2025-12-03 23:59:59'),
-- Vol 5 : Los Angeles -> Paris
('AF002', 2, 5, 1, '2025-12-05 16:00:00', '2025-12-06 08:00:00', 900.00, 2200.00, '2025-12-04 23:59:59');

-- verification des sieges
-- SELECT * FROM siege WHERE id_vol = 1;  -- AF101

INSERT INTO role (nom) VALUES
('admin'),
('client');

INSERT INTO users (nom, email, password, role) VALUES
-- Administrateurs
('Admin Principal', 'admin1@gestionvol.com', crypt('pass', gen_salt('bf')), 'admin'),
('Admin Secondaire', 'admin2@gestionvol.com', crypt('pass', gen_salt('bf')), 'admin'),

-- Clients
('Alice Dupont', 'alice.dupont@email.com', crypt('pass', gen_salt('bf')), 'client'),
('Bob Martin', 'bob.martin@email.com', crypt('pass', gen_salt('bf')), 'client'),
('Charlie Renault', 'charlie.renault@email.com', crypt('pass', gen_salt('bf')), 'client'),
('David Smith', 'david.smith@email.com', crypt('pass', gen_salt('bf')), 'client'),
('Emma Wilson', 'emma.wilson@email.com', crypt('pass', gen_salt('bf')), 'client');

INSERT INTO user_role (id_user, id_role) VALUES
(1,1),
(2,1),
(3,2),
(4,2),
(5,2),
(6,2),
(7,2);
