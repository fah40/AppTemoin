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

-- Ajout des vols avec des dates corrigées
INSERT INTO vol (numero_vol, id_avion, id_ville_depart, id_ville_arrivee, date_depart, date_arrivee, prix_economique, prix_business) VALUES
('AF101', 1, 1, 2, '2025-06-10 08:00:00', '2025-06-10 14:00:00', 500, 1500),  -- Paris → New York
('EK202', 2, 3, 4, '2025-06-15 10:00:00', '2025-06-15 18:00:00', 800, 2500),  -- Dubai → Tokyo
('DL303', 3, 5, 1, '2025-06-20 06:00:00', '2025-06-20 12:00:00', 400, 1200);  -- Los Angeles → Paris

-- Correction des dates limites de réservation
INSERT INTO param_reservation (id_vol, date_limite_reservation, annulation_possible) VALUES 
(1, '2025-06-05 23:59:59', TRUE),  -- Limite de réservation 5 jours avant le vol
(2, '2025-06-10 23:59:59', FALSE), -- Pas d'annulation possible pour ce vol
(3, '2025-06-15 23:59:59', TRUE);  -- Limite de réservation 5 jours avant

INSERT INTO promotion (id_vol,reduction,nombre_max_reservations) VALUES
(1, 0.5, 3),
(2, 0.2, 4),
(3, 0.9, 2);

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
