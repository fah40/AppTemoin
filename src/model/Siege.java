package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bd.MyConnect;

public class Siege {
    private int id;
    private int idVol;
    private String numeroSiege;
    private SiegeType type;
    private boolean estReserve;

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVol() {
        return idVol;
    }

    public void setIdVol(int idVol) {
        this.idVol = idVol;
    }

    public String getNumeroSiege() {
        return numeroSiege;
    }

    public void setNumeroSiege(String numeroSiege) {
        this.numeroSiege = numeroSiege;
    }

    public SiegeType getType() {
        return type;
    }

    public void setType(SiegeType type) {
        this.type = type;
    }

    public boolean isEstReserve() {
        return estReserve;
    }

    public void setEstReserve(boolean estReserve) {
        this.estReserve = estReserve;
    }

    // Méthodes pour les opérations CRUD
    public void insert() throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;

        try {
            String query = "INSERT INTO siege (id_vol, numero_siege, type, est_reserve) VALUES (?, ?, ?, ?)";
            st = con.prepareStatement(query);
            st.setInt(1, this.idVol);
            st.setString(2, this.numeroSiege);
            st.setString(3, this.type.toString());
            st.setBoolean(4, this.estReserve);

            try {
                st.executeUpdate();
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Échec de l'insertion du siège", e);
            }
        } finally {
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }
    }

    public static Siege getById(int id) throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        Siege instance = null;

        try {
            String query = "SELECT * FROM siege WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new Siege();
                instance.setId(rs.getInt("id"));
                instance.setIdVol(rs.getInt("id_vol"));
                instance.setNumeroSiege(rs.getString("numero_siege"));
                instance.setType(SiegeType.valueOf(rs.getString("type")));
                instance.setEstReserve(rs.getBoolean("est_reserve"));
            }
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }

        return instance;
    }

    public static Siege[] getAll() throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Siege> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM siege ORDER BY id ASC";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Siege item = new Siege();
                item.setId(rs.getInt("id"));
                item.setIdVol(rs.getInt("id_vol"));
                item.setNumeroSiege(rs.getString("numero_siege"));
                item.setType(SiegeType.valueOf(rs.getString("type")));
                item.setEstReserve(rs.getBoolean("est_reserve"));

                items.add(item);
            }
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }

        return items.toArray(new Siege[0]);
    }

    public void update() throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;

        try {
            String query = "UPDATE siege SET id_vol = ?, numero_siege = ?, type = ?, est_reserve = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, this.idVol);
            st.setString(2, this.numeroSiege);
            st.setString(3, this.type.toString());
            st.setBoolean(4, this.estReserve);
            st.setInt(5, this.id);

            try {
                st.executeUpdate();
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Échec de la mise à jour du siège", e);
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
            String query = "DELETE FROM siege WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);

            try {
                st.executeUpdate();
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Échec de la suppression du siège", e);
            }
        } finally {
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }
    }

    public static Siege[] search(int idVol, String numeroSiege, SiegeType type, Boolean estReserve) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Siege> items = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM siege WHERE 1=1");

        try {
            // Construction de la requête avec les conditions dynamiques
            if (idVol > 0) {
                query.append(" AND id_vol = ?");
            }
            if (numeroSiege != null && !numeroSiege.isEmpty()) {
                query.append(" AND numero_siege LIKE ?");
            }
            if (type != null) {
                query.append(" AND type = ?");
            }
            if (estReserve != null) {
                query.append(" AND est_reserve = ?");
            }

            // Connexion à la base de données
            con = MyConnect.getConnection();
            st = con.prepareStatement(query.toString());

            // Définition des paramètres dans la requête
            int paramIndex = 1;
            if (idVol > 0) {
                st.setInt(paramIndex++, idVol);
            }
            if (numeroSiege != null && !numeroSiege.isEmpty()) {
                st.setString(paramIndex++, "%" + numeroSiege + "%");
            }
            if (type != null) {
                st.setString(paramIndex++, type.toString());
            }
            if (estReserve != null) {
                st.setBoolean(paramIndex++, estReserve);
            }

            // Exécution de la requête
            rs = st.executeQuery();

            // Traitement des résultats
            while (rs.next()) {
                Siege item = new Siege();
                item.setId(rs.getInt("id"));
                item.setIdVol(rs.getInt("id_vol"));
                item.setNumeroSiege(rs.getString("numero_siege"));
                item.setType(SiegeType.valueOf(rs.getString("type")));
                item.setEstReserve(rs.getBoolean("est_reserve"));

                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erreur lors de la recherche des sièges : " + e.getMessage());
        } finally {
            // Fermeture des ressources
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }

        return items.toArray(new Siege[0]);
    }
}

// Enum pour le type de siège
enum SiegeType {
    ECONOMIQUE,
    BUSINESS
}
