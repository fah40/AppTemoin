package model;
import java.sql.*;
import java.util.*;

import db.MyConnect;
import use.*;

public class User {
    private int id;
    private String nom;
    private String email;
    private String password;
    private String role;

    private model.Role userRole;

    public User(){}
    public User(String nom,String email,String password,String role,Connection con) throws Exception{
        setNom(nom); 
        setEmail(email); 
        setPassword(password); 
        setRole(role); 
    }


    public model.Role getUserRole() {
        return userRole;
    }
    public void setUserRole(model.Role userRole) {
        this.userRole = userRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws Exception {
        MyUtil.verifyNumericPostive(id, "id");
        this.id = id;
    }

    public void setId(String id) throws Exception {
        int toSet =  MyUtil.convertIntFromHtmlInput(id);

        setId(toSet) ;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws Exception {
        MyUtil.verifyStringNotNullOrEmpty(nom, "nom");
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        MyUtil.verifyStringNotNullOrEmpty(email, "email");
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {
        MyUtil.verifyStringNotNullOrEmpty(password, "password");
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) throws Exception {
        MyUtil.verifyStringNotNullOrEmpty(role, "role");
        this.role = role;
    }

    public static User getById(int id, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        User instance = null;

        try {
            String query = "SELECT * FROM user_roles WHERE id = ?";
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
                instance.setUserRole(model.Role.getRoleById(rs.getInt("role_id"), con));
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }

        return instance;
    }

    public static User checkLogin(String email, String pass, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        User instance = null;
    
        try {
            String query = "SELECT * FROM user_roles WHERE email = ? AND password = crypt(?, password)";
            st = con.prepareStatement(query);
            st.setString(1, email);
            st.setString(2, pass);
            rs = st.executeQuery();
    
            if (rs.next()) {
                instance = new User();
                instance.setId(rs.getInt("id"));
                instance.setNom(rs.getString("nom"));
                instance.setEmail(rs.getString("email"));
                instance.setPassword(rs.getString("password")); // En général, on ne stocke pas le password récupéré
                instance.setRole(rs.getString("role"));
                instance.setUserRole(model.Role.getRoleById(rs.getInt("role_id"), con));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }
    
        return instance;
    }
    

    public static User[] getAll() throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<User> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM user_roles order by id asc ";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                User item = new User();
                item.setId(rs.getInt("id"));
                item.setNom(rs.getString("nom"));
                item.setEmail(rs.getString("email"));
                item.setPassword(rs.getString("password"));
                item.setRole(rs.getString("role"));
                item.setUserRole(model.Role.getRoleById(rs.getInt("role_id"), con));
                items.add(item);
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !false) con.close();
        }

        return items.toArray(new User[0]);
    }
    public int insert(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO users (nom, email, password) VALUES (?, ?, ?) RETURNING id";
            st = con.prepareStatement(query);
            st.setString(1, this.nom);
            st.setString(2, this.email);
            st.setString(3, this.password);
            try {
                rs = st.executeQuery();
                if (rs.next()) {
                    int generatedId = rs.getInt("id");
                    this.setId(generatedId); 
                    con.commit();
                    return generatedId;
                } else {
                    con.rollback();
                    throw new Exception("Failed to retrieve generated ID");
                }
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Failed to insert record", e);
            }
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }
    }
    public void update(Connection con) throws Exception {
        PreparedStatement st = null;
        try {
            String query = "UPDATE users SET nom = ?, email = ?, password = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setString(1, this.nom);
            st.setString(2, this.email);
            st.setString(3, this.password);
            st.setInt(5, this.getId());
            try {
                st.executeUpdate();
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Failed to update record", e);
            }
        } finally {
            if (st != null) st.close();
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
                throw new Exception("Failed to delete record", e);
            }
        } finally {
            if (st != null) st.close();
           if (con != null) con.close(); 
        }
    }
}

// Commun'IT app