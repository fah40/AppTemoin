package model;

import java.sql.*;
import java.util.*;
import db.MyConnect;
import use.*;

public class Siege {
    private int id;
    private int id_vol;
    private int id_type;
    private boolean est_reserve;

    private Vol vol;
    private Siege_type type;

    public Siege() {
    }

    public Siege(String vol, String type, String est_reserve, Connection con) throws Exception {
        setVol(vol, con);
        setType(type, con);
        setEst_reserve(est_reserve);
    }

    public int getId_vol() {
        return id_vol;
    }

    public void setId_vol(int id_vol) {
        this.id_vol = id_vol;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
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

    public Vol getVol() {
        return vol;
    }

    public void setVol(Vol vol) throws Exception {
        this.vol = vol;
    }

    public void setVol(String vol, Connection con) throws Exception {
        // define how this type should be conterted from String ... type : Vol
        Vol toSet = Vol.getById(Integer.parseInt(vol), con);

        setVol(toSet);
    }

    public Siege_type getType() {
        return type;
    }

    public void setType(Siege_type type) throws Exception {
        this.type = type;
    }

    public void setType(String type, Connection con) throws Exception {
        // define how this type should be conterted from String ... type : Siege_type
        Siege_type toSet = Siege_type.getById(Integer.parseInt(type), con);

        setType(toSet);
    }

    public boolean getEst_reserve() {
        return est_reserve;
    }

    public void setEst_reserve(boolean est_reserve) throws Exception {
        this.est_reserve = est_reserve;
    }

    public void setEst_reserve(String est_reserve) throws Exception {
        boolean toSet = MyUtil.convertBooleanFromCheckBox(est_reserve);

        setEst_reserve(toSet);
    }

    public static Siege getById(int id, Connection con) throws Exception {
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
                instance.setVol(Vol.getById(rs.getInt("id_vol"), con));
                instance.setType(Siege_type.getById(rs.getInt("id_type"), con));
                instance.setEst_reserve(rs.getBoolean("est_reserve"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
        }

        return instance;
    }

    public static Siege[] getAll() throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Siege> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM siege order by id asc ";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Siege item = new Siege();
                item.setId(rs.getInt("id"));
                item.setVol(Vol.getById(rs.getInt("id_vol"), con));
                item.setType(Siege_type.getById(rs.getInt("id_type"), con));
                item.setEst_reserve(rs.getBoolean("est_reserve"));
                items.add(item);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
            if (con != null && !false)
                con.close();
        }

        return items.toArray(new Siege[0]);
    }

    public int insert(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO siege (id_vol, id_type, est_reserve) VALUES (?, ?, ?) RETURNING id";
            st = con.prepareStatement(query);
            st.setInt(1, this.id_vol);
            st.setInt(2, this.id_type);
            st.setBoolean(3, this.est_reserve);
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
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
        }
    }

    public void update(Connection con) throws Exception {
        PreparedStatement st = null;
        try {
            String query = "UPDATE siege SET id_vol = ?, id_type = ?, est_reserve = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, this.vol.getId());
            st.setInt(2, this.type.getId());
            st.setBoolean(3, this.est_reserve);
            st.setInt(4, this.getId());
            try {
                st.executeUpdate();
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Failed to update record", e);
            }
        } finally {
            if (st != null)
                st.close();
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
                throw new Exception("Failed to delete record", e);
            }
        } finally {
            if (st != null)
                st.close();
            if (con != null)
                con.close();
        }
    }
}

// Commun'IT app