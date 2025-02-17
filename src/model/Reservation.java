package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bd.MyConnect;

public class Reservation {
    private int id;
    private int idUser;
    private int idVol;
    private int idSiege;
    private double prixFinal;
    private Timestamp dateReservation;

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdVol() {
        return idVol;
    }

    public void setIdVol(int idVol) {
        this.idVol = idVol;
    }

    public int getIdSiege() {
        return idSiege;
    }

    public void setIdSiege(int idSiege) {
        this.idSiege = idSiege;
    }

    public double getPrixFinal() {
        return prixFinal;
    }

    public void setPrixFinal(double prixFinal) {
        this.prixFinal = prixFinal;
    }

    public Timestamp getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Timestamp dateReservation) {
        this.dateReservation = dateReservation;
    }

    // Méthodes pour les opérations CRUD
    public void insert() throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;

        try {
            String query = "INSERT INTO reservation (id_user, id_vol, id_siege, prix_final) VALUES (?, ?, ?, ?)";
            st = con.prepareStatement(query);
            st.setInt(1, this.idUser);
            st.setInt(2, this.idVol);
            st.setInt(3, this.idSiege);
            st.setDouble(4, this.prixFinal);

            try {
                st.executeUpdate();
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Échec de l'insertion de la réservation", e);
            }
        } finally {
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }
    }

    public static Reservation getById(int id) throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        Reservation instance = null;

        try {
            String query = "SELECT * FROM reservation WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new Reservation();
                instance.setId(rs.getInt("id"));
                instance.setIdUser(rs.getInt("id_user"));
                instance.setIdVol(rs.getInt("id_vol"));
                instance.setIdSiege(rs.getInt("id_siege"));
                instance.setPrixFinal(rs.getDouble("prix_final"));
                instance.setDateReservation(rs.getTimestamp("date_reservation"));
            }
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }

        return instance;
    }

    public static Reservation[] getAll() throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Reservation> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM reservation ORDER BY id ASC";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Reservation item = new Reservation();
                item.setId(rs.getInt("id"));
                item.setIdUser(rs.getInt("id_user"));
                item.setIdVol(rs.getInt("id_vol"));
                item.setIdSiege(rs.getInt("id_siege"));
                item.setPrixFinal(rs.getDouble("prix_final"));
                item.setDateReservation(rs.getTimestamp("date_reservation"));

                items.add(item);
            }
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }

        return items.toArray(new Reservation[0]);
    }

    public void update() throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;

        try {
            String query = "UPDATE reservation SET id_user = ?, id_vol = ?, id_siege = ?, prix_final = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, this.idUser);
            st.setInt(2, this.idVol);
            st.setInt(3, this.idSiege);
            st.setDouble(4, this.prixFinal);
            st.setInt(5, this.id);

            try {
                st.executeUpdate();
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Échec de la mise à jour de la réservation", e);
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
            String query = "DELETE FROM reservation WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);

            try {
                st.executeUpdate();
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Échec de la suppression de la réservation", e);
            }
        } finally {
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }
    }

    public static Reservation[] search(int idUser, int idVol, int idSiege, Double prixFinal, Timestamp dateReservation) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Reservation> items = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM reservation WHERE 1=1");

        try {
            // Construction de la requête avec les conditions dynamiques
            if (idUser > 0) {
                query.append(" AND id_user = ?");
            }
            if (idVol > 0) {
                query.append(" AND id_vol = ?");
            }
            if (idSiege > 0) {
                query.append(" AND id_siege = ?");
            }
            if (prixFinal != null) {
                query.append(" AND prix_final = ?");
            }
            if (dateReservation != null) {
                query.append(" AND date_reservation >= ?");
            }

            // Connexion à la base de données
            con = MyConnect.getConnection();
            st = con.prepareStatement(query.toString());

            // Définition des paramètres dans la requête
            int paramIndex = 1;
            if (idUser > 0) {
                st.setInt(paramIndex++, idUser);
            }
            if (idVol > 0) {
                st.setInt(paramIndex++, idVol);
            }
            if (idSiege > 0) {
                st.setInt(paramIndex++, idSiege);
            }
            if (prixFinal != null) {
                st.setDouble(paramIndex++, prixFinal);
            }
            if (dateReservation != null) {
                st.setTimestamp(paramIndex++, dateReservation);
            }

            // Exécution de la requête
            rs = st.executeQuery();

            // Traitement des résultats
            while (rs.next()) {
                Reservation item = new Reservation();
                item.setId(rs.getInt("id"));
                item.setIdUser(rs.getInt("id_user"));
                item.setIdVol(rs.getInt("id_vol"));
                item.setIdSiege(rs.getInt("id_siege"));
                item.setPrixFinal(rs.getDouble("prix_final"));
                item.setDateReservation(rs.getTimestamp("date_reservation"));

                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erreur lors de la recherche des réservations : " + e.getMessage());
        } finally {
            // Fermeture des ressources
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }

        return items.toArray(new Reservation[0]);
    }
}