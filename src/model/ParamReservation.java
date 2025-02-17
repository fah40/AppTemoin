package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bd.MyConnect;

public class ParamReservation {
    private int id;
    private Vol vol; // Remplace idVol par un objet Vol
    private Timestamp dateLimiteReservation;
    private boolean annulationPossible;

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vol getVol() {
        return vol;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }

    public Timestamp getDateLimiteReservation() {
        return dateLimiteReservation;
    }

    public void setDateLimiteReservation(Timestamp dateLimiteReservation) {
        this.dateLimiteReservation = dateLimiteReservation;
    }

    public boolean isAnnulationPossible() {
        return annulationPossible;
    }

    public void setAnnulationPossible(boolean annulationPossible) {
        this.annulationPossible = annulationPossible;
    }

    // Méthodes pour les opérations CRUD
    public void insert() throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;

        try {
            String query = "INSERT INTO param_reservation (id_vol, date_limite_reservation, annulation_possible) VALUES (?, ?, ?)";
            st = con.prepareStatement(query);
            st.setInt(1, this.vol.getId()); // Utilisation de l'ID de l'objet Vol
            st.setTimestamp(2, this.dateLimiteReservation);
            st.setBoolean(3, this.annulationPossible);

            try {
                st.executeUpdate();
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Échec de l'insertion des paramètres de réservation", e);
            }
        } finally {
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }
    }

    public static ParamReservation getById(int id) throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        ParamReservation instance = null;

        try {
            String query = "SELECT * FROM param_reservation WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new ParamReservation();
                instance.setId(rs.getInt("id"));
                instance.setVol(Vol.getById(rs.getInt("id_vol"))); // Récupération de l'objet Vol
                instance.setDateLimiteReservation(rs.getTimestamp("date_limite_reservation"));
                instance.setAnnulationPossible(rs.getBoolean("annulation_possible"));
            }
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }

        return instance;
    }

    public static ParamReservation[] getAll() throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<ParamReservation> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM param_reservation ORDER BY id ASC";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                ParamReservation item = new ParamReservation();
                item.setId(rs.getInt("id"));
                item.setVol(Vol.getById(rs.getInt("id_vol"))); // Récupération de l'objet Vol
                item.setDateLimiteReservation(rs.getTimestamp("date_limite_reservation"));
                item.setAnnulationPossible(rs.getBoolean("annulation_possible"));

                items.add(item);
            }
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }

        return items.toArray(new ParamReservation[0]);
    }

    public void update() throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;

        try {
            String query = "UPDATE param_reservation SET id_vol = ?, date_limite_reservation = ?, annulation_possible = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, this.vol.getId()); // Utilisation de l'ID de l'objet Vol
            st.setTimestamp(2, this.dateLimiteReservation);
            st.setBoolean(3, this.annulationPossible);
            st.setInt(4, this.id);

            try {
                st.executeUpdate();
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Échec de la mise à jour des paramètres de réservation", e);
            }
        } finally {
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }
    }

    public static void deleteById(int id) throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;

        try {
            String query = "DELETE FROM param_reservation WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);

            try {
                st.executeUpdate();
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Échec de la suppression des paramètres de réservation", e);
            }
        } finally {
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }
    }

    public static ParamReservation[] search(int idVol, Timestamp dateLimiteReservation, Boolean annulationPossible) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<ParamReservation> items = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM param_reservation WHERE 1=1");

        try {
            // Construction de la requête avec les conditions dynamiques
            if (idVol > 0) {
                query.append(" AND id_vol = ?");
            }
            if (dateLimiteReservation != null) {
                query.append(" AND date_limite_reservation >= ?");
            }
            if (annulationPossible != null) {
                query.append(" AND annulation_possible = ?");
            }

            // Connexion à la base de données
            con = MyConnect.getConnection();
            st = con.prepareStatement(query.toString());

            // Définition des paramètres dans la requête
            int paramIndex = 1;
            if (idVol > 0) {
                st.setInt(paramIndex++, idVol);
            }
            if (dateLimiteReservation != null) {
                st.setTimestamp(paramIndex++, dateLimiteReservation);
            }
            if (annulationPossible != null) {
                st.setBoolean(paramIndex++, annulationPossible);
            }

            // Exécution de la requête
            rs = st.executeQuery();

            // Traitement des résultats
            while (rs.next()) {
                ParamReservation item = new ParamReservation();
                item.setId(rs.getInt("id"));
                item.setVol(Vol.getById(rs.getInt("id_vol"))); // Récupération de l'objet Vol
                item.setDateLimiteReservation(rs.getTimestamp("date_limite_reservation"));
                item.setAnnulationPossible(rs.getBoolean("annulation_possible"));

                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erreur lors de la recherche des paramètres de réservation : " + e.getMessage());
        } finally {
            // Fermeture des ressources
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }

        return items.toArray(new ParamReservation[0]);
    }
}