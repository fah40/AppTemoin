package model;
import java.sql.*;
import java.util.*;
import db.MyConnect;
import use.*;
public class Promotion {
    private int id;
    private Vol vol;
    private double reduction;
    private int nombre_max_reservations;
    public Promotion(){}
    public Promotion(String vol,String reduction,String nombre_max_reservations,Connection con) throws Exception{
        setVol(vol,con); 
        setReduction(reduction); 
        setNombre_max_reservations(nombre_max_reservations); 
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

    public Vol getVol() {
        return vol;
    }

    public void setVol(Vol vol) throws Exception {
        this.vol = vol;
    }

    public void setVol(String vol,Connection con) throws Exception {
         //define how this type should be conterted from String ... type : Vol
        Vol toSet = Vol.getById(Integer.parseInt(vol),con );

        setVol(toSet) ;
    }

    public double getReduction() {
        return reduction;
    }

    public void setReduction(double reduction) throws Exception {
        MyUtil.verifyNumericPostive(reduction, "reduction");
        this.reduction = reduction;
    }

    public void setReduction(String reduction) throws Exception {
        double toSet =  MyUtil.convertDoubleFromHtmlInput(reduction);

        setReduction(toSet) ;
    }

    public int getNombre_max_reservations() {
        return nombre_max_reservations;
    }

    public void setNombre_max_reservations(int nombre_max_reservations) throws Exception {
        MyUtil.verifyNumericPostive(nombre_max_reservations, "nombre_max_reservations");
        this.nombre_max_reservations = nombre_max_reservations;
    }

    public void setNombre_max_reservations(String nombre_max_reservations) throws Exception {
        int toSet =  MyUtil.convertIntFromHtmlInput(nombre_max_reservations);

        setNombre_max_reservations(toSet) ;
    }

    public static Promotion getById(int id, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        Promotion instance = null;

        try {
            String query = "SELECT * FROM promotion WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new Promotion();
                instance.setId(rs.getInt("id"));
                instance.setVol(Vol.getById(rs.getInt("id_vol") ,con ));
                instance.setReduction(rs.getDouble("reduction"));
                instance.setNombre_max_reservations(rs.getInt("nombre_max_reservations"));
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            
        }

        return instance;
    }
    public static Promotion[] getAll() throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Promotion> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM promotion order by id asc ";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Promotion item = new Promotion();
                item.setId(rs.getInt("id"));
                item.setVol(Vol.getById(rs.getInt("id_vol")  ,con ));
                item.setReduction(rs.getDouble("reduction"));
                item.setNombre_max_reservations(rs.getInt("nombre_max_reservations"));
                items.add(item);
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !false) con.close();
        }

        return items.toArray(new Promotion[0]);
    }
    public int insert(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO promotion (id_vol, reduction, nombre_max_reservations) VALUES (?, ?, ?) RETURNING id";
            st = con.prepareStatement(query);
            st.setInt(1, this.vol.getId());
            st.setDouble(2, this.reduction);
            st.setInt(3, this.nombre_max_reservations);
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
            String query = "UPDATE promotion SET id_vol = ?, reduction = ?, nombre_max_reservations = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt (1, this.vol.getId());
            st.setDouble(2, this.reduction);
            st.setInt(3, this.nombre_max_reservations);
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
            String query = "DELETE FROM promotion WHERE id = ?";
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