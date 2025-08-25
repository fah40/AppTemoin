package model;
import java.sql.*;
import java.util.*;
import db.MyConnect;
import use.*;

public class Configuration {
    private String cle;
    private String valeur;

    public Configuration() {}

    public Configuration(String cle, String valeur, Connection con) throws Exception {
        setCle(cle);
        setValeur(valeur);
    }

    public String getCle() {
        return cle;
    }

    public void setCle(String cle) throws Exception {
        MyUtil.verifyStringNotNullOrEmpty(cle, "cle");
        this.cle = cle;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) throws Exception {
        // Pas de vérification spécifique ici, car "valeur" peut être null ou vide selon les besoins
        this.valeur = valeur;
    }

    public static Configuration getByCle(String cle, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        Configuration instance = null;

        try {
            String query = "SELECT * FROM configuration WHERE cle = ?";
            st = con.prepareStatement(query);
            st.setString(1, cle);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new Configuration();
                instance.setCle(rs.getString("cle"));
                instance.setValeur(rs.getString("valeur"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }

        return instance;
    }

    public static Configuration[] getAll(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Configuration> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM configuration ORDER BY cle ASC";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Configuration item = new Configuration();
                item.setCle(rs.getString("cle"));
                item.setValeur(rs.getString("valeur"));
                items.add(item);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }

        return items.toArray(new Configuration[0]);
    }

    public int insert(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO configuration (cle, valeur) VALUES (?, ?) RETURNING cle";
            st = con.prepareStatement(query);
            st.setString(1, this.cle);
            st.setString(2, this.valeur);
            try {
                rs = st.executeQuery();
                if (rs.next()) {
                    String generatedCle = rs.getString("cle");
                    this.setCle(generatedCle);
                    con.commit();
                    return 1; // Retourne 1 pour indiquer un succès (pas d'ID numérique ici)
                } else {
                    con.rollback();
                    throw new Exception("Failed to retrieve generated key");
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
            String query = "UPDATE configuration SET valeur = ? WHERE cle = ?";
            st = con.prepareStatement(query);
            st.setString(1, this.valeur);
            st.setString(2, this.cle);
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

    public static void deleteById(String cle) throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;
        try {
            String query = "DELETE FROM configuration WHERE cle = ?";
            st = con.prepareStatement(query);
            st.setString(1, cle);
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
