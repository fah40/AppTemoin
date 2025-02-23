package model;
import java.sql.*;
import java.util.*;
import db.MyConnect;
import use.*;
public class Avion {
    private int id;
    private String modele;
    private int nbr_siege_economique;
    private int nbr_siege_business;
    public Avion(){}
    public Avion(String modele,String nbr_siege_economique,String nbr_siege_business,Connection con) throws Exception{
        setModele(modele); 
        setNbr_siege_economique(nbr_siege_economique); 
        setNbr_siege_business(nbr_siege_business); 
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

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) throws Exception {
        MyUtil.verifyStringNotNullOrEmpty(modele, "modele");
        this.modele = modele;
    }

    public int getNbr_siege_economique() {
        return nbr_siege_economique;
    }

    public void setNbr_siege_economique(int nbr_siege_economique) throws Exception {
        MyUtil.verifyNumericPostive(nbr_siege_economique, "nbr_siege_economique");
        this.nbr_siege_economique = nbr_siege_economique;
    }

    public void setNbr_siege_economique(String nbr_siege_economique) throws Exception {
        int toSet =  MyUtil.convertIntFromHtmlInput(nbr_siege_economique);

        setNbr_siege_economique(toSet) ;
    }

    public int getNbr_siege_business() {
        return nbr_siege_business;
    }

    public void setNbr_siege_business(int nbr_siege_business) throws Exception {
        MyUtil.verifyNumericPostive(nbr_siege_business, "nbr_siege_business");
        this.nbr_siege_business = nbr_siege_business;
    }

    public void setNbr_siege_business(String nbr_siege_business) throws Exception {
        int toSet =  MyUtil.convertIntFromHtmlInput(nbr_siege_business);

        setNbr_siege_business(toSet) ;
    }

    public static Avion getById(int id, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        Avion instance = null;

        try {
            String query = "SELECT * FROM avion WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new Avion();
                instance.setId(rs.getInt("id"));
                instance.setModele(rs.getString("modele"));
                instance.setNbr_siege_economique(rs.getInt("nbr_siege_economique"));
                instance.setNbr_siege_business(rs.getInt("nbr_siege_business"));
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }

        return instance;
    }
    public static Avion[] getAll(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Avion> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM avion order by id asc ";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Avion item = new Avion();
                item.setId(rs.getInt("id"));
                item.setModele(rs.getString("modele"));
                item.setNbr_siege_economique(rs.getInt("nbr_siege_economique"));
                item.setNbr_siege_business(rs.getInt("nbr_siege_business"));
                items.add(item);
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }

        return items.toArray(new Avion[0]);
    }
    public int insert(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO avion (modele, nbr_siege_economique, nbr_siege_business) VALUES (?, ?, ?) RETURNING id";
            st = con.prepareStatement(query);
            st.setString(1, this.modele);
            st.setInt(2, this.nbr_siege_economique);
            st.setInt(3, this.nbr_siege_business);
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
            String query = "UPDATE avion SET modele = ?, nbr_siege_economique = ?, nbr_siege_business = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setString(1, this.modele);
            st.setInt(2, this.nbr_siege_economique);
            st.setInt(3, this.nbr_siege_business);
            st.setInt(4, this.getId());
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
            String query = "DELETE FROM avion WHERE id = ?";
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