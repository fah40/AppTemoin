package model;

import java.sql.*;
import java.util.*;
import db.MyConnect;
import use.*;

public class Reservation {
    private int id;
    private int id_user;
    private int id_type;
    private int id_vol;
    private int nombre;
    private java.sql.Timestamp date_reservation;

    private Siege_type type;
    private User user;
    private Vol vol;
    private double total;


    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Siege_type getType() {
        return type;
    }

    public void setType(Siege_type type) {
        this.type = type;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

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

    // Méthode getById
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
                instance.setType(Siege_type.getById(rs.getInt("id_type"), con));
                instance.setNombre(rs.getInt("nombre"));
                instance.setDate_reservation(rs.getTimestamp("date_reservation"));
            }
        } catch (Exception e) {
            throw new Exception("Failed to retrieve Reservation by ID", e);
        } finally {
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
        }

        return instance;
    }

    // Méthode getcountByIdType
    public static int getcountByIdType(int id, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        int rep = 0;

        try {
            String query = "SELECT SUM(nombre) as sumres FROM reservation WHERE id_type = ?";
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

    // Méthode getAll
    public static Reservation[] getAll(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Reservation> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM reservation ORDER BY id ASC";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Reservation item = new Reservation();
                item.setId(rs.getInt("id"));
                item.setUser(User.getById(rs.getInt("id_user"), con));
                item.setVol(Vol.getById(rs.getInt("id_vol"), con));
                item.setType(Siege_type.getById(rs.getInt("id_type"), con));
                item.setNombre(rs.getInt("nombre"));
                item.setDate_reservation(rs.getTimestamp("date_reservation"));
                items.add(item);
            }
        } catch (Exception e) {
            throw new Exception("Failed to retrieve all reservations", e);
        } finally {
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
        }

        return items.toArray(new Reservation[0]);
    }

    // Méthode insert
    public int insert(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;


        try {
            String query = "INSERT INTO reservation (id_user, id_vol, id_type, nombre, date_reservation) VALUES (?, ?, ?, ?, ?) RETURNING id";
            st = con.prepareStatement(query);
            st.setInt(1, this.id_user);
            st.setInt(2, this.id_vol);
            st.setInt(3, this.id_type);
            st.setInt(4, this.nombre);
            st.setTimestamp(5, this.date_reservation);

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
            throw new Exception("Failed to insert reservation", e);
        } finally {
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
        }
    }

    public void insertCorrectly(Connection con, int id_vol_curr) throws Exception {
        
        Siege_type type= Siege_type.getById(this.getId_type(), con);
        Vol vol= Vol.getById(id_vol_curr, con);
        Avion avion= vol.getAvion();
        int id_reservation= 0;
        int max_siege= 0;
        int reservationrestant = 0;
        int countBillet = 0;
        double prix = 0;
        double prixPromo = 0;
        double promoMax = 0;
        double reduce = 0;
        
        if (type.getNom().compareTo("economique") == 0) {
            prix = vol.getPrix_economique();
            reduce = vol.getPrix_economique() * vol.getReduction();
            prixPromo = vol.getPrix_economique() - reduce;
            max_siege= avion.getNbr_siege_economique();
            promoMax = vol.getPro_max_eco();
        }else{
            prix = vol.getPrix_business();
            prixPromo = vol.getPrix_business() * vol.getReduction();
            max_siege= avion.getNbr_siege_business();
            promoMax = vol.getPro_max_bus();
        }

        reservationrestant = max_siege - Reservation.getcountByIdType(type.getId(), con);
        
        if (reservationrestant >= this.getNombre()) {
            id_reservation = this.insert(con);
            int i=0;
            // realisation de la reduction
            while (i < this.getNombre()) {
                countBillet = Billet.getcountByIdType(type.getId(), con);
                // realisation de la reduction
                Billet bl = new Billet();
                
                bl.setUser(User.getById(this.id_user, con));
                bl.setId_vol(id_vol_curr);
                bl.setId_reservation(id_reservation);
                bl.setId_type(type.getId());

                if(countBillet < promoMax){
                    bl.setPrix_final(prixPromo);
                }else{
                    bl.setPrix_final(prix);
                }

                bl.insert(con);
                i++;
            }
            
        }else{
            throw new Exception("le nombre de Siege n'est pas assez!");
        }
    }

    // Méthode update
    public void update(Connection con) throws Exception {
        PreparedStatement st = null;

        try {
            String query = "UPDATE reservation SET id_user = ?, id_vol = ?, id_type = ?, nombre = ?, date_reservation = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, this.user.getId());
            st.setInt(2, this.vol.getId());
            st.setInt(3, this.type.getId());
            st.setInt(4, this.nombre);
            st.setTimestamp(5, this.date_reservation);
            st.setInt(6, this.getId());

            int rowsUpdated = st.executeUpdate();
            if (rowsUpdated > 0) {
                con.commit();
            } else {
                con.rollback();
                throw new Exception("No record found to update");
            }
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Failed to update reservation", e);
        } finally {
            if (st != null)
                st.close();
        }
    }

    // Méthode deleteById
    public static void deleteById(int id, Connection con) throws Exception {
        PreparedStatement st = null;

        try {
            String query = "DELETE FROM reservation WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);

            int rowsDeleted = st.executeUpdate();
            if (rowsDeleted > 0) {
                con.commit();
            } else {
                con.rollback();
                throw new Exception("No record found to delete");
            }
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Failed to delete reservation", e);
        } finally {
            if (st != null)
                st.close();
        }
    }
}