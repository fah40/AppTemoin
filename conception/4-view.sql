CREATE OR REPLACE VIEW details_reservation as
SELECT r.id, u.nom, v.numero_vol, s.numero_siege, s.type, r.prix_final, r.date_reservation
FROM reservation r
JOIN users u ON r.id_user = u.id
JOIN vol v ON r.id_vol = v.id
JOIN siege s ON r.id_siege = s.id;

CREATE OR REPLACE VIEW user_roles AS
SELECT U.*, R.nom AS role_name, R.id AS role_id
FROM users U join user_role UR ON U.id = UR.id_user
JOIN role R ON UR.id_role = R.id; 