package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Role {
    private int id;
    private String nom;
    
    public Role() {
    }

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

    public static Role getRoleById(int id, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        Role instance = null;

        try {
            String query = "SELECT * FROM role WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new Role();
                instance.setId(rs.getInt("id"));
                instance.setNom(rs.getString("nom"));
            }
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }
        return instance;
    }
}
