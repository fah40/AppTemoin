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

INSERT INTO vol (numero_vol, id_avion, id_ville_depart, id_ville_arrivee, date_depart, date_arrivee, prix_economique, prix_business, date_limite_reservation, reduction, mx_eco, mx_bus)
VALUES 
-- Vol 1 : Paris -> New York
('AF001', 1, 1, 2, '2023-12-01 08:00:00', '2023-12-01 16:00:00', 500.00, 1200.00, '2023-11-30 23:59:59', 0.05, 4, 5),
-- Vol 2 : New York -> Dubai
('EK202', 2, 2, 3, '2023-12-02 10:00:00', '2023-12-02 22:00:00', 600.00, 1500.00, '2023-12-01 23:59:59', 0.04, 3, 3),
-- Vol 3 : Dubai -> Tokyo
('JL123', 3, 3, 4, '2023-12-03 12:00:00', '2023-12-03 20:00:00', 700.00, 1800.00, '2023-12-02 23:59:59', 0.03, 3, 5),
-- Vol 4 : Tokyo -> Los Angeles
('AA456', 1, 4, 5, '2023-12-04 14:00:00', '2023-12-04 22:00:00', 800.00, 2000.00, '2023-12-03 23:59:59', 0.04, 2, 2),
-- Vol 5 : Los Angeles -> Paris
('AF002', 2, 5, 1, '2023-12-05 16:00:00', '2023-12-06 08:00:00', 900.00, 2200.00, '2023-12-04 23:59:59', 0.02, 8, 2);

-- verification des sieges
SELECT * FROM siege WHERE id_vol = 1;  -- AF101

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

-- Réservation 1
INSERT INTO reservation (id_user, id_vol, id_siege, prix_final)
VALUES (1, 1, 1, 500);

-- Réservation 2
INSERT INTO reservation (id_user, id_vol, id_siege, prix_final)
VALUES (2, 1, 2, 500);

-- Réservation 3 (cela devrait appliquer la promotion de 10%)
INSERT INTO reservation (id_user, id_vol, id_siege, prix_final)
VALUES (3, 1, 3, 500);

-- Réservation 4 (pas de promotion ici, car la limite est atteinte)
INSERT INTO reservation (id_user, id_vol, id_siege, prix_final)
VALUES (4, 1, 4, 500);
