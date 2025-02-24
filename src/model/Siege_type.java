package model;
import java.sql.*;
import java.util.*;
import db.MyConnect;
import use.*;

public class Siege_type {
    private int id;
    private String nom;
    public Siege_type(){}
    public Siege_type(String nom,Connection con) throws Exception{
        setNom(nom); 
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

    public static Siege_type getById(int id, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        Siege_type instance = null;

        try {
            String query = "SELECT * FROM siege_type WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new Siege_type();
                instance.setId(rs.getInt("id"));
                instance.setNom(rs.getString("nom"));
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }

        return instance;
    }
    public static Siege_type[] getAll(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Siege_type> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM siege_type order by id asc ";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Siege_type item = new Siege_type();
                item.setId(rs.getInt("id"));
                item.setNom(rs.getString("nom"));
                items.add(item);
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }

        return items.toArray(new Siege_type[0]);
    }
    public int insert(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO siege_type (nom) VALUES (?) RETURNING id";
            st = con.prepareStatement(query);
            st.setString(1, this.nom);
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
            String query = "UPDATE siege_type SET nom = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setString(1, this.nom);
            st.setInt(2, this.getId());
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
            String query = "DELETE FROM siege_type WHERE id = ?";
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