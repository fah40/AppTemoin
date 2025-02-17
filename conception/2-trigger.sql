-- Trigger pour ajouter des sièges après la création d'un vol
CREATE OR REPLACE FUNCTION ajouter_sieges_apres_creation_vol() 
RETURNS TRIGGER AS $$
DECLARE
    total_economique INT;
    total_business INT;
    num INT := 1;
BEGIN
    -- Récupérer le nombre de sièges de l'avion associé au vol
    SELECT nbr_siege_economique, nbr_siege_business 
    INTO total_economique, total_business
    FROM avion WHERE id = NEW.id_avion;

    -- Insérer les sièges économiques
    WHILE num <= total_economique LOOP
        INSERT INTO siege (id_vol, numero_siege, type, est_reserve)
        VALUES (NEW.id, CONCAT('E', num), 'economique', FALSE);
        num := num + 1;
    END LOOP;

    -- Insérer les sièges business
    num := 1;
    WHILE num <= total_business LOOP
        INSERT INTO siege (id_vol, numero_siege, type, est_reserve)
        VALUES (NEW.id, CONCAT('B', num), 'business', FALSE);
        num := num + 1;
    END LOOP;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Définition du trigger
CREATE TRIGGER ajouter_sieges_apres_creation_vol_trigger
AFTER INSERT ON vol
FOR EACH ROW
EXECUTE FUNCTION ajouter_sieges_apres_creation_vol();


-- ======================================================= PROMOTION
CREATE OR REPLACE FUNCTION tr_promotion_premieres_reservations() 
RETURNS TRIGGER AS $$
DECLARE
    count_reservations INT;
    promo_reduction DECIMAL(5,2);
    promo_max INT;
BEGIN
    -- Vérifier s'il existe une promotion pour ce vol
    SELECT reduction, nombre_max_reservations INTO promo_reduction, promo_max
    FROM promotion
    WHERE id_vol = NEW.id_vol
    LIMIT 1;

    -- Compter le nombre de réservations existantes pour ce vol
    SELECT COUNT(*) INTO count_reservations 
    FROM reservation 
    WHERE id_vol = NEW.id_vol;

    -- Appliquer la promotion seulement si on est encore dans la limite
    IF count_reservations < promo_max THEN
        NEW.prix_final := NEW.prix_final * (1 - promo_reduction / 100);
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Définition du trigger
CREATE TRIGGER tr_promotion_premieres_reservations_trigger
BEFORE INSERT ON reservation
FOR EACH ROW
EXECUTE FUNCTION tr_promotion_premieres_reservations();


-- ======================================================= ANNULATION RESERVATION
CREATE OR REPLACE FUNCTION tr_annulation_reservation() 
RETURNS TRIGGER AS $$
BEGIN
    -- Libérer le siège en mettant disponible à TRUE
    UPDATE siege
    SET est_reserve = FALSE
    WHERE id = OLD.id_siege;

    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

-- Définition du trigger
CREATE TRIGGER tr_annulation_reservation_trigger
AFTER DELETE ON reservation
FOR EACH ROW
EXECUTE FUNCTION tr_annulation_reservation();
