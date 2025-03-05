package model;

import java.sql.*;
import java.util.*;
import db.MyConnect;
import use.*;

public class Billet {
    private int id;
    private User user;
    private int id_vol;
    private int id_reservation;
    private Reservation reservation;
    private int id_type;
    private Siege_type type;
    private double prix_final;

    public Billet() {
    }

    public Billet(String user, String id_vol, String reservation, String siege, String prix_final, Connection con)
            throws Exception {
        setUser(user, con);
        setId_vol(id_vol);
        setReservation(reservation, con);
        setSiege(siege, con);
        setPrix_final(prix_final);
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
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
        // define how this type should be conterted from String ... type : User
        User toSet = User.getById(Integer.parseInt(user), con);

        setUser(toSet);
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

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) throws Exception {
        this.reservation = reservation;
    }

    public void setReservation(String reservation, Connection con) throws Exception {
        // define how this type should be conterted from String ... type : Reservation
        Reservation toSet = Reservation.getById(Integer.parseInt(reservation), con);

        setReservation(toSet);
    }

    public Siege_type getSiege() {
        return type;
    }

    public void setSiege(Siege_type siege) throws Exception {
        this.type = siege;
    }

    public void setSiege(String siege, Connection con) throws Exception {
        // define how this type should be conterted from String ... type : Siege
        Siege_type toSet = Siege_type.getById(Integer.parseInt(siege), con);

        setSiege(toSet);
    }

    public double getPrix_final() {
        return prix_final;
    }

    public void setPrix_final(double prix_final) throws Exception {
        MyUtil.verifyNumericPostive(prix_final, "prix_final");
        this.prix_final = prix_final;
    }

    public void setPrix_final(String prix_final) throws Exception {
        double toSet = MyUtil.convertDoubleFromHtmlInput(prix_final);

        setPrix_final(toSet);
    }

    public static Billet getById(int id, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        Billet instance = null;

        try {
            String query = "SELECT * FROM billet WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new Billet();
                instance.setId(rs.getInt("id"));
                instance.setUser(User.getById(rs.getInt("id_user"), con));
                instance.setId_vol(rs.getInt("id_vol"));
                instance.setReservation(Reservation.getById(rs.getInt("id_reservation"), con));
                instance.setId_reservation(rs.getInt("id_reservation"));
                instance.setSiege(Siege_type.getById(rs.getInt("id_type"), con));
                instance.setId_type(rs.getInt("id_type"));
                instance.setPrix_final(rs.getDouble("prix_final"));
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

    public static Billet[] getAll(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Billet> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM billet order by id asc ";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Billet item = new Billet();
                item.setId(rs.getInt("id"));
                item.setUser(User.getById(rs.getInt("id_user"), con));
                item.setId_vol(rs.getInt("id_vol"));
                item.setReservation(Reservation.getById(rs.getInt("id_reservation"), con));
                item.setId_reservation(rs.getInt("id_reservation"));
                item.setSiege(Siege_type.getById(rs.getInt("id_type"), con));
                item.setId_type(rs.getInt("id_type"));
                item.setPrix_final(rs.getDouble("prix_final"));
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

        return items.toArray(new Billet[0]);
    }

    public static Billet[] getAllMyBillet(Connection con,int id_user) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Billet> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM billet WHERE id_user = ? order by id asc ";
            st = con.prepareStatement(query);
            st.setInt(1, id_user);
            rs = st.executeQuery();

            while (rs.next()) {
                Billet item = new Billet();
                item.setId(rs.getInt("id"));
                item.setUser(User.getById(rs.getInt("id_user"), con));
                item.setId_vol(rs.getInt("id_vol"));
                item.setReservation(Reservation.getById(rs.getInt("id_reservation"), con));
                item.setId_reservation(rs.getInt("id_reservation"));
                item.setSiege(Siege_type.getById(rs.getInt("id_type"), con));
                item.setId_type(rs.getInt("id_type"));
                item.setPrix_final(rs.getDouble("prix_final"));
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

        return items.toArray(new Billet[0]);
    }

    public static int getcountByIdType(int id, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        int rep = 0;

        try {
            String query = "SELECT count(*) as sumres FROM billet WHERE id_type = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                rep = rs.getInt("sumres");
            }
        } catch (Exception e) {
            throw new Exception("Failed to count reservations by type", e);
        } finally {
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
        }
        return rep;
    }

    public int insert(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO billet (id_user, id_vol, id_reservation, id_type, prix_final) VALUES (?, ?, ?, ?, ?) RETURNING id";
            st = con.prepareStatement(query);
            st.setInt(1, this.user.getId());
            st.setInt(2, this.id_vol);
            st.setInt(3, this.id_reservation);
            st.setInt(4, this.id_type);
            st.setDouble(5, this.prix_final);
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
            String query = "UPDATE billet SET id_user = ?, id_vol = ?, id_reservation = ?, id_type = ?, prix_final = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, this.user.getId());
            st.setInt(2, this.id_vol);
            st.setInt(3, this.reservation.getId());
            st.setInt(4, this.type.getId());
            st.setDouble(5, this.prix_final);
            st.setInt(6, this.getId());
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
            String query = "DELETE FROM billet WHERE id = ?";
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