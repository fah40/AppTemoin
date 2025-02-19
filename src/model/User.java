package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bd.MyConnect;

public class User {
    private int id;
    private String nom;
    private String email;
    private String password;
    private String role;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void insert(Connection con) throws Exception {
        PreparedStatement st = null;

        try {
            String query = "INSERT INTO users (nom, email, password, role) VALUES (?, ?, crypt('?', gen_salt('bf', 8)), ?)";
            st = con.prepareStatement(query);
            st.setString(1, this.nom);
            st.setString(2, this.email);
            st.setString(3, this.password);
            st.setString(4, this.role.toString());

            try {
                st.executeUpdate();
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Échec de l'insertion de l'utilisateur", e);
            }
        } finally {
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }
    }

    // Méthodes pour les opérations CRUD
    public void insert() throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;

        try {
            String query = "INSERT INTO users (nom, email, password, role) VALUES (?, ?, crypt('?', gen_salt('bf', 8)), ?)";
            st = con.prepareStatement(query);
            st.setString(1, this.nom);
            st.setString(2, this.email);
            st.setString(3, this.password);
            st.setString(4, this.role.toString());

            try {
                st.executeUpdate();
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Échec de l'insertion de l'utilisateur", e);
            }
        } finally {
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }
    }

    public static User getById(int id,Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        User instance = null;

        try {
            String query = "SELECT * FROM users WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new User();
                instance.setId(rs.getInt("id"));
                instance.setNom(rs.getString("nom"));
                instance.setEmail(rs.getString("email"));
                instance.setPassword(rs.getString("password"));
                instance.setRole(rs.getString("role"));
            }
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }

        return instance;
    }

    public static User getById(int id) throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        User instance = null;

        try {
            String query = "SELECT * FROM users WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new User();
                instance.setId(rs.getInt("id"));
                instance.setNom(rs.getString("nom"));
                instance.setEmail(rs.getString("email"));
                instance.setPassword(rs.getString("password"));
                instance.setRole(rs.getString("role"));
            }
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }

        return instance;
    }

    public static User[] getAll() throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<User> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM users ORDER BY id ASC";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                User item = new User();
                item.setId(rs.getInt("id"));
                item.setNom(rs.getString("nom"));
                item.setEmail(rs.getString("email"));
                item.setPassword(rs.getString("password"));
                item.setRole(rs.getString("role"));

                items.add(item);
            }
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }

        return items.toArray(new User[0]);
    }

    public static User[] getAll(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<User> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM users ORDER BY id ASC";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                User item = new User();
                item.setId(rs.getInt("id"));
                item.setNom(rs.getString("nom"));
                item.setEmail(rs.getString("email"));
                item.setPassword(rs.getString("password"));
                item.setRole(rs.getString("role"));

                items.add(item);
            }
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }

        return items.toArray(new User[0]);
    }

    public static User checkLoging(String email, String mdp, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        User item = null;
    
        try {
            String query = "SELECT * FROM users WHERE email=? AND password = crypt(?, password);";
            st = con.prepareStatement(query);
            st.setString(1, email);
            st.setString(2, mdp);
            rs = st.executeQuery();
    
            if (rs.next()) {
                item = new User();
                item.setId(rs.getInt("id"));
                item.setNom(rs.getString("nom"));
                item.setEmail(rs.getString("email"));
                item.setPassword(rs.getString("password"));
                item.setRole(rs.getString("role"));
            }
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }
    
        return item;
    }
    

    public void update() throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;

        try {
            String query = "UPDATE users SET nom = ?, email = ?, password = ?, role = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setString(1, this.nom);
            st.setString(2, this.email);
            st.setString(3, this.password);
            st.setString(4, this.role.toString());
            st.setInt(5, this.id);

            try {
                st.executeUpdate();
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Échec de la mise à jour de l'utilisateur", e);
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
            String query = "DELETE FROM users WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);

            try {
                st.executeUpdate();
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Échec de la suppression de l'utilisateur", e);
            }
        } finally {
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }
    }

    public static User[] search(String nom, String email, String role) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<User> items = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM users WHERE 1=1");

        try {
            // Construction de la requête avec les conditions dynamiques
            if (nom != null && !nom.isEmpty()) {
                query.append(" AND nom LIKE ?");
            }
            if (email != null && !email.isEmpty()) {
                query.append(" AND email LIKE ?");
            }
            if (role != null) {
                query.append(" AND role = ?");
            }

            // Connexion à la base de données
            con = MyConnect.getConnection();
            st = con.prepareStatement(query.toString());

            // Définition des paramètres dans la requête
            int paramIndex = 1;
            if (nom != null && !nom.isEmpty()) {
                st.setString(paramIndex++, "%" + nom + "%");
            }
            if (email != null && !email.isEmpty()) {
                st.setString(paramIndex++, "%" + email + "%");
            }
            if (role != null) {
                st.setString(paramIndex++, role.toString());
            }

            // Exécution de la requête
            rs = st.executeQuery();

            // Traitement des résultats
            while (rs.next()) {
                User item = new User();
                item.setId(rs.getInt("id"));
                item.setNom(rs.getString("nom"));
                item.setEmail(rs.getString("email"));
                item.setPassword(rs.getString("password"));
                item.setRole(rs.getString("role"));

                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erreur lors de la recherche des utilisateurs : " + e.getMessage());
        } finally {
            // Fermeture des ressources
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }

        return items.toArray(new User[0]);
    }
}