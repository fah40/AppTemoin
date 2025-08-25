package model;
import java.sql.*;
import java.util.*;
import db.MyConnect;
import use.*;

public class HistoPrix {
    private int id;
    private int id_vol;
    private double prix_economique;
    private double prix_business;
    private Timestamp date_debut;
    private Timestamp date_fin;

    public HistoPrix() {}

    public HistoPrix(String id_vol, String prix_economique, String prix_business, String date_debut, String date_fin, Connection con) throws Exception {
        setId_vol(id_vol);
        setPrix_economique(prix_economique);
        setPrix_business(prix_business);
        setDate_debut(date_debut);
        setDate_fin(date_fin);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws Exception {
        MyUtil.verifyNumericPostive(id, "id");
        this.id = id;
    }

    public void setId(String id) throws Exception {
        int toSet = MyUtil.convertIntFromHtmlInput(id);
        setId(toSet);
    }

    public int getId_vol() {
        return id_vol;
    }

    public void setId_vol(int id_vol) throws Exception {
        MyUtil.verifyNumericPostive(id_vol, "id_vol");
        this.id_vol = id_vol;
    }

    public void setId_vol(String id_vol) throws Exception {
        int toSet = MyUtil.convertIntFromHtmlInput(id_vol);
        setId_vol(toSet);
    }

    public double getPrix_economique() {
        return prix_economique;
    }

    public void setPrix_economique(double prix_economique) throws Exception {
        MyUtil.verifyNumericPostive(prix_economique, "prix_economique");
        this.prix_economique = prix_economique;
    }

    public void setPrix_economique(String prix_economique) throws Exception {
        double toSet = MyUtil.convertDoubleFromHtmlInput(prix_economique);
        setPrix_economique(toSet);
    }

    public double getPrix_business() {
        return prix_business;
    }

    public void setPrix_business(double prix_business) throws Exception {
        MyUtil.verifyNumericPostive(prix_business, "prix_business");
        this.prix_business = prix_business;
    }

    public void setPrix_business(String prix_business) throws Exception {
        double toSet = MyUtil.convertDoubleFromHtmlInput(prix_business);
        setPrix_business(toSet);
    }

    public Timestamp getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Timestamp date_debut) throws Exception {
        this.date_debut = date_debut;
    }

    public void setDate_debut(String date_debut) throws Exception {
        this.date_debut = MyUtil.convertTimestampFromHtmlInput(date_debut);
    }

    public Timestamp getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Timestamp date_fin) throws Exception {
        this.date_fin = date_fin;
    }

    public void setDate_fin(String date_fin) throws Exception {
        this.date_fin = MyUtil.convertTimestampFromHtmlInput(date_fin);
    }

    public static HistoPrix getById(int id, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        HistoPrix instance = null;

        try {
            String query = "SELECT * FROM histo_prix WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new HistoPrix();
                instance.setId(rs.getInt("id"));
                instance.setId_vol(rs.getInt("id_vol"));
                instance.setPrix_economique(rs.getDouble("prix_economique"));
                instance.setPrix_business(rs.getDouble("prix_business"));
                instance.setDate_debut(rs.getTimestamp("date_debut"));
                instance.setDate_fin(rs.getTimestamp("date_fin"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }

        return instance;
    }

    public static HistoPrix getByIdVol(int id, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        HistoPrix instance = null;

        try {
            String query = "SELECT * FROM histo_prix WHERE id_vol = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new HistoPrix();
                instance.setId(rs.getInt("id"));
                instance.setId_vol(rs.getInt("id_vol"));
                instance.setPrix_economique(rs.getDouble("prix_economique"));
                instance.setPrix_business(rs.getDouble("prix_business"));
                instance.setDate_debut(rs.getTimestamp("date_debut"));
                instance.setDate_fin(rs.getTimestamp("date_fin"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }

        return instance;
    }

    public static HistoPrix[] getAll(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<HistoPrix> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM histo_prix ORDER BY id ASC";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                HistoPrix item = new HistoPrix();
                item.setId(rs.getInt("id"));
                item.setId_vol(rs.getInt("id_vol"));
                item.setPrix_economique(rs.getDouble("prix_economique"));
                item.setPrix_business(rs.getDouble("prix_business"));
                item.setDate_debut(rs.getTimestamp("date_debut"));
                item.setDate_fin(rs.getTimestamp("date_fin"));
                items.add(item);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }

        return items.toArray(new HistoPrix[0]);
    }

    public int insert(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO histo_prix (id_vol, prix_economique, prix_business, date_debut, date_fin) VALUES (?, ?, ?, ?, ?) RETURNING id";
            st = con.prepareStatement(query);
            st.setInt(1, this.id_vol);
            st.setDouble(2, this.prix_economique);
            st.setDouble(3, this.prix_business);
            st.setTimestamp(4, this.date_debut);
            st.setTimestamp(5, this.date_fin);

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
            String query = "UPDATE histo_prix SET id_vol = ?, prix_economique = ?, prix_business = ?, date_debut = ?, date_fin = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, this.id_vol);
            st.setDouble(2, this.prix_economique);
            st.setDouble(3, this.prix_business);
            st.setTimestamp(4, this.date_debut);
            st.setTimestamp(5, this.date_fin);
            st.setInt(6, this.getId());
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
            String query = "DELETE FROM histo_prix WHERE id = ?";
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
