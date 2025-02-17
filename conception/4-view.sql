CREATE OR REPLACE VIEW details_reservation as
SELECT r.id, u.nom, v.numero_vol, s.numero_siege, s.type, r.prix_final, r.date_reservation
FROM reservation r
JOIN user u ON r.id_user = u.id
JOIN vol v ON r.id_vol = v.id
JOIN siege s ON r.id_siege = s.id;