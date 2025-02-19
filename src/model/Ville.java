package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bd.MyConnect;

public class Ville {
    private int id;
    private String nom;

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Méthodes pour les opérations CRUD
    public void insert() throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;

        try {
            String query = "INSERT INTO ville (nom) VALUES (?)";
            st = con.prepareStatement(query);
            st.setString(1, this.nom);

            try {
                st.executeUpdate();
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Échec de l'insertion de la ville", e);
            }
        } finally {
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }
    }

    public static Ville getById(int id) throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        Ville instance = null;

        try {
            String query = "SELECT * FROM ville WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new Ville();
                instance.setId(rs.getInt("id"));
                instance.setNom(rs.getString("nom"));
            }
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }

        return instance;
    }

    public static Ville getById(int id, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        Ville instance = null;

        try {
            String query = "SELECT * FROM ville WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new Ville();
                instance.setId(rs.getInt("id"));
                instance.setNom(rs.getString("nom"));
            }
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }

        return instance;
    }

    public static Ville[] getAll() throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Ville> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM ville ORDER BY id ASC";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Ville item = new Ville();
                item.setId(rs.getInt("id"));
                item.setNom(rs.getString("nom"));

                items.add(item);
            }
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }

        return items.toArray(new Ville[0]);
    }

    public static Ville[] getAll(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Ville> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM ville ORDER BY id ASC";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Ville item = new Ville();
                item.setId(rs.getInt("id"));
                item.setNom(rs.getString("nom"));

                items.add(item);
            }
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }

        return items.toArray(new Ville[0]);
    }

    public void update() throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;

        try {
            String query = "UPDATE ville SET nom = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setString(1, this.nom);
            st.setInt(2, this.id);

            try {
                st.executeUpdate();
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Échec de la mise à jour de la ville", e);
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
            String query = "DELETE FROM ville WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);

            try {
                st.executeUpdate();
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Échec de la suppression de la ville", e);
            }
        } finally {
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }
    }

    public static Ville[] search(String nom) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Ville> items = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM ville WHERE 1=1");

        try {
            // Construction de la requête avec les conditions dynamiques
            if (nom != null && !nom.isEmpty()) {
                query.append(" AND nom LIKE ?");
            }

            // Connexion à la base de données
            con = MyConnect.getConnection();
            st = con.prepareStatement(query.toString());

            // Définition des paramètres dans la requête
            int paramIndex = 1;
            if (nom != null && !nom.isEmpty()) {
                st.setString(paramIndex++, "%" + nom + "%");
            }

            // Exécution de la requête
            rs = st.executeQuery();

            // Traitement des résultats
            while (rs.next()) {
                Ville item = new Ville();
                item.setId(rs.getInt("id"));
                item.setNom(rs.getString("nom"));

                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erreur lors de la recherche des villes : " + e.getMessage());
        } finally {
            // Fermeture des ressources
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }

        return items.toArray(new Ville[0]);
    }
}
