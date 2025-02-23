-- Trigger pour ajouter des sièges après la création d'un vol
CREATE OR REPLACE FUNCTION ajouter_sieges_apres_creation_vol() 
RETURNS TRIGGER AS $$
DECLARE
    total_economique INT;
    total_business INT;
BEGIN
    -- Récupérer le nombre de sièges de l'avion associé au vol
    SELECT nbr_siege_economique, nbr_siege_business 
    INTO total_economique, total_business
    FROM avion WHERE id = NEW.id_avion;

    -- Insérer les sièges économiques
    INSERT INTO siege (id_vol, numero_siege, type, est_reserve)
    SELECT NEW.id, CONCAT('E', g) AS numero_siege, 'economique', FALSE
    FROM generate_series(1, total_economique) g;

    -- Insérer les sièges business
    INSERT INTO siege (id_vol, numero_siege, type, est_reserve)
    SELECT NEW.id, CONCAT('B', g) AS numero_siege, 'business', FALSE
    FROM generate_series(1, total_business) g;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Définition du trigger
CREATE TRIGGER ajouter_sieges_apres_creation_vol_trigger
AFTER INSERT ON vol
FOR EACH ROW
EXECUTE FUNCTION ajouter_sieges_apres_creation_vol();

-- ======================================================= RESERVATION
CREATE OR REPLACE FUNCTION tr_promotion_premieres_reservations() 
RETURNS TRIGGER AS $$
DECLARE
    count_reservations INT;
    promo_reduction DECIMAL(5,2) := 0;
    promo_max INT := 0;
BEGIN
    -- Vérifier s'il existe une promotion pour ce vol
    SELECT reduction, nombre_max_reservations 
    INTO promo_reduction, promo_max
    FROM promotion
    WHERE id_vol = NEW.id_vol
    LIMIT 1;

    -- Si aucune promotion n'existe, ne rien modifier
    IF promo_reduction IS NULL OR promo_max IS NULL THEN
        RETURN NEW;
    END IF;

    -- Compter le nombre de réservations existantes pour ce vol
    SELECT COUNT(*) INTO count_reservations 
    FROM billet 
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
BEFORE INSERT ON billet
FOR EACH ROW
EXECUTE FUNCTION tr_promotion_premieres_reservations();

-- ======================================================= BILLETS
CREATE OR REPLACE FUNCTION tr_generer_billets() 
RETURNS TRIGGER AS $$
DECLARE
    v_siege_id INT;
    v_prix_final DECIMAL(12,2);
    v_compteur INT := 0;
BEGIN
    -- Déterminer le prix du billet (ajouter une éventuelle promotion)
    SELECT COALESCE(p.reduction, 0) 
    INTO v_prix_final
    FROM promotion p
    WHERE p.id_vol = NEW.id_vol
    LIMIT 1;

    IF v_prix_final IS NULL THEN
        -- Récupérer le prix normal si pas de promotion
        SELECT prix_economique INTO v_prix_final FROM vol WHERE id = NEW.id_vol;
    ELSE
        -- Appliquer la réduction sur le prix économique
        SELECT prix_economique - v_prix_final INTO v_prix_final FROM vol WHERE id = NEW.id_vol;
    END IF;

    -- Boucle pour insérer autant de billets que de places réservées
    WHILE v_compteur < NEW.nombre LOOP
        -- Sélectionner un siège disponible
        SELECT id INTO v_siege_id 
        FROM siege 
        WHERE id_vol = NEW.id_vol 
        AND est_reserve = FALSE 
        LIMIT 1;

        -- Vérifier si un siège a été trouvé
        IF v_siege_id IS NOT NULL THEN
            -- Insérer le billet pour ce siège
            INSERT INTO billet (id_user, id_vol, id_reservation, id_siege, prix_final)
            VALUES (NEW.id_user, NEW.id_vol, NEW.id, v_siege_id, v_prix_final);

            -- Marquer le siège comme réservé
            UPDATE siege SET est_reserve = TRUE WHERE id = v_siege_id;
        ELSE
            -- Si plus de sièges disponibles, arrêter la boucle
            EXIT;
        END IF;

        v_compteur := v_compteur + 1;
    END LOOP;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Définition du trigger
CREATE TRIGGER tr_generer_billets_trigger
AFTER INSERT ON reservation
FOR EACH ROW
EXECUTE FUNCTION tr_generer_billets();


-- ======================================================= ANNULATION RESERVATION - BILLET
CREATE OR REPLACE FUNCTION tr_annulation_billet() 
RETURNS TRIGGER AS $$
BEGIN
    -- Supprimer les billets correspondant à la réservation
    DELETE FROM billet WHERE id_reservation = OLD.id;
    
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;


-- Définition du trigger
CREATE TRIGGER tr_annulation_billet_trigger
AFTER DELETE ON reservation
FOR EACH ROW
EXECUTE FUNCTION tr_annulation_billet();

-- ======================================================= ANNULATION RESERVATION-SIEGE
CREATE OR REPLACE FUNCTION tr_liberer_siege() 
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
CREATE TRIGGER tr_liberer_siege_trigger
AFTER DELETE ON billet
FOR EACH ROW
EXECUTE FUNCTION tr_liberer_siege();


