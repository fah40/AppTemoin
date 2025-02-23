package model;

import java.sql.*;
import java.util.*;
import db.MyConnect;
import use.*;

public class Reservation {
    private int id;
    private int id_user;
    private int id_vol;
    private int nombre;
    private java.sql.Timestamp date_reservation;

    private User user;
    private Vol vol;

    public Reservation() {
    }

    public Reservation(String user, String vol, String nombre, String date_reservation, Connection con)
            throws Exception {
        setUser(user, con);
        setVol(vol, con);
        setNombre(nombre);
        setDate_reservation(date_reservation);
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_vol() {
        return id_vol;
    }

    public void setId_vol(int id_vol) {
        this.id_vol = id_vol;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) throws Exception {
        this.user = user;
    }

    public void setUser(String user, Connection con) throws Exception {
        // define how this type should be conterted from String ... type : Users
        User toSet = User.getById(Integer.parseInt(user), con);

        setUser(toSet);
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

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) throws Exception {
        MyUtil.verifyNumericPostive(nombre, "nombre");
        this.nombre = nombre;
    }

    public void setNombre(String nombre) throws Exception {
        int toSet = MyUtil.convertIntFromHtmlInput(nombre);

        setNombre(toSet);
    }

    public java.sql.Timestamp getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(java.sql.Timestamp date_reservation) throws Exception {
        MyUtil.verifyObjectNotNull(date_reservation, "date_reservation");
        this.date_reservation = date_reservation;
    }

    public void setDate_reservation(String date_reservation) throws Exception {
        java.sql.Timestamp toSet = MyUtil.convertTimestampFromHtmlInput(date_reservation);

        setDate_reservation(toSet);
    }

    public static Reservation getById(int id, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        Reservation instance = null;

        try {
            String query = "SELECT * FROM reservation WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new Reservation();
                instance.setId(rs.getInt("id"));
                instance.setUser(User.getById(rs.getInt("id_user"), con));
                instance.setVol(Vol.getById(rs.getInt("id_vol"), con));
                instance.setNombre(rs.getInt("nombre"));
                instance.setDate_reservation(rs.getTimestamp("date_reservation"));
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

    public static Reservation[] getAll(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Reservation> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM reservation order by id asc ";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Reservation item = new Reservation();
                item.setId(rs.getInt("id"));
                item.setUser(User.getById(rs.getInt("id_user"), con));
                item.setVol(Vol.getById(rs.getInt("id_vol"), con));
                item.setNombre(rs.getInt("nombre"));
                item.setDate_reservation(rs.getTimestamp("date_reservation"));
                items.add(item);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
        }

        return items.toArray(new Reservation[0]);
    }

    public int insert(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO reservation (id_user, id_vol, nombre, date_reservation) VALUES (?, ?, ?, ?) RETURNING id";
            st = con.prepareStatement(query);
            st.setInt(1, this.id_user);
            st.setInt(2, this.id_vol);
            st.setInt(3, this.nombre);
            st.setTimestamp(4, this.date_reservation);
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
            String query = "UPDATE reservation SET id_user = ?, id_vol = ?, nombre = ?, date_reservation = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, this.user.getId());
            st.setInt(2, this.vol.getId());
            st.setInt(3, this.nombre);
            st.setTimestamp(4, this.date_reservation);
            st.setInt(5, this.getId());
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
            String query = "DELETE FROM reservation WHERE id = ?";
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