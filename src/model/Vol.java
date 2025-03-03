package model;

import java.sql.*;
import java.util.*;
import db.MyConnect;
import use.*;

public class Vol {
    private int id;
    private String numero_vol;
    private int idAvion;
    private int id_ville_depart;
    private int id_ville_arrivee;
    private java.sql.Timestamp date_depart;
    private java.sql.Timestamp date_arrivee;
    private double prix_economique;
    private double prix_business;
    private java.sql.Timestamp date_limite_reservation;
    private double reduction;
    private int pro_max_eco;
    private int pro_max_bus;
    private java.sql.Timestamp insert_date;
    private boolean disponible;

    private Avion avion;
    private Ville ville_depart;
    private Ville ville_arrivee;

    public Vol() {
    }

    public Vol(String numero_vol, String avion, String ville_depart, String ville_arrivee, String date_depart,
            String date_arrivee, String prix_economique, String prix_business, String date_limite_reservation,
            String disponible, Connection con) throws Exception {
        setNumero_vol(numero_vol);
        setAvion(avion, con);
        setVille_depart(ville_depart, con);
        setVille_arrivee(ville_arrivee, con);
        setDate_depart(date_depart);
        setDate_arrivee(date_arrivee);
        setPrix_economique(prix_economique);
        setPrix_business(prix_business);
        setDate_limite_reservation(date_limite_reservation);
        setDisponible(disponible);
    }

    public java.sql.Timestamp getInsert_date() {
        return insert_date;
    }

    public void setInsert_date(java.sql.Timestamp insert_date) {
        this.insert_date = insert_date;
    }

    public int getPro_max_eco() {
        return pro_max_eco;
    }

    public void setPro_max_eco(int pro_max_eco) {
        this.pro_max_eco = pro_max_eco;
    }

    public int getPro_max_bus() {
        return pro_max_bus;
    }

    public void setPro_max_bus(int pro_max_bus) {
        this.pro_max_bus = pro_max_bus;
    }

    public double getReduction() {
        return reduction;
    }

    public void setReduction(double reduction) {
        this.reduction = reduction;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    public int getId_ville_depart() {
        return id_ville_depart;
    }

    public void setId_ville_depart(int id_ville_depart) {
        this.id_ville_depart = id_ville_depart;
    }

    public int getId_ville_arrivee() {
        return id_ville_arrivee;
    }

    public void setId_ville_arrivee(int id_ville_arrivee) {
        this.id_ville_arrivee = id_ville_arrivee;
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

    public String getNumero_vol() {
        return numero_vol;
    }

    public void setNumero_vol(String numero_vol) throws Exception {
        MyUtil.verifyStringNotNullOrEmpty(numero_vol, "numero_vol");
        this.numero_vol = numero_vol;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) throws Exception {
        this.avion = avion;
    }

    public void setAvion(String avion, Connection con) throws Exception {
        // define how this type should be conterted from String ... type : Avion
        Avion toSet = Avion.getById(Integer.parseInt(avion), con);

        setAvion(toSet);
    }

    public Ville getVille_depart() {
        return ville_depart;
    }

    public void setVille_depart(Ville ville_depart) throws Exception {
        this.ville_depart = ville_depart;
    }

    public void setVille_depart(String ville_depart, Connection con) throws Exception {
        // define how this type should be conterted from String ... type : Ville
        Ville toSet = Ville.getById(Integer.parseInt(ville_depart), con);

        setVille_depart(toSet);
    }

    public Ville getVille_arrivee() {
        return ville_arrivee;
    }

    public void setVille_arrivee(Ville ville_arrivee) throws Exception {
        this.ville_arrivee = ville_arrivee;
    }

    public void setVille_arrivee(String ville_arrivee, Connection con) throws Exception {
        // define how this type should be conterted from String ... type : Ville
        Ville toSet = Ville.getById(Integer.parseInt(ville_arrivee), con);

        setVille_arrivee(toSet);
    }

    public java.sql.Timestamp getDate_depart() {
        return date_depart;
    }

    public void setDate_depart(java.sql.Timestamp date_depart) throws Exception {
        MyUtil.verifyObjectNotNull(date_depart, "date_depart");
        this.date_depart = date_depart;
    }

    public void setDate_depart(String date_depart) throws Exception {
        java.sql.Timestamp toSet = MyUtil.convertTimestampFromHtmlInput(date_depart);

        setDate_depart(toSet);
    }

    public java.sql.Timestamp getDate_arrivee() {
        return date_arrivee;
    }

    public void setDate_arrivee(java.sql.Timestamp date_arrivee) throws Exception {
        MyUtil.verifyObjectNotNull(date_arrivee, "date_arrivee");
        this.date_arrivee = date_arrivee;
    }

    public void setDate_arrivee(String date_arrivee) throws Exception {
        java.sql.Timestamp toSet = MyUtil.convertTimestampFromHtmlInput(date_arrivee);

        setDate_arrivee(toSet);
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

    public java.sql.Timestamp getDate_limite_reservation() {
        return date_limite_reservation;
    }

    public void setDate_limite_reservation(java.sql.Timestamp date_limite_reservation) throws Exception {
        MyUtil.verifyObjectNotNull(date_limite_reservation, "date_limite_reservation");
        this.date_limite_reservation = date_limite_reservation;
    }

    public void setDate_limite_reservation(String date_limite_reservation) throws Exception {
        java.sql.Timestamp toSet = MyUtil.convertTimestampFromHtmlInput(date_limite_reservation);

        setDate_limite_reservation(toSet);
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) throws Exception {
        this.disponible = disponible;
    }

    public void setDisponible(String disponible) throws Exception {
        boolean toSet = MyUtil.convertBooleanFromCheckBox(disponible);

        setDisponible(toSet);
    }

    public static Vol getById(int id, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        Vol instance = null;
    
        try {
            // Requête SQL pour récupérer un vol par son ID
            String query = "SELECT * FROM vol WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();
    
            if (rs.next()) {
                instance = new Vol();
    
                // Remplissage des attributs de l'objet Vol
                instance.setId(rs.getInt("id"));
                instance.setNumero_vol(rs.getString("numero_vol"));
                instance.setIdAvion(rs.getInt("id_avion"));
                instance.setId_ville_depart(rs.getInt("id_ville_depart"));
                instance.setId_ville_arrivee(rs.getInt("id_ville_arrivee"));
                instance.setDate_depart(rs.getTimestamp("date_depart"));
                instance.setDate_arrivee(rs.getTimestamp("date_arrivee"));
                instance.setPrix_economique(rs.getDouble("prix_economique"));
                instance.setPrix_business(rs.getDouble("prix_business"));
                instance.setDate_limite_reservation(rs.getTimestamp("date_limite_reservation"));
                instance.setReduction(rs.getDouble("reduction"));
                instance.setPro_max_eco(rs.getInt("mx_eco")); // mx_eco dans la table
                instance.setPro_max_bus(rs.getInt("mx_bus")); // mx_bus dans la table
                instance.setInsert_date(rs.getTimestamp("insert_date"));
                instance.setDisponible(rs.getBoolean("disponible"));
    
                // Récupération des objets associés (Avion et Ville)
                instance.setAvion(Avion.getById(rs.getInt("id_avion"), con));
                instance.setVille_depart(Ville.getById(rs.getInt("id_ville_depart"), con));
                instance.setVille_arrivee(Ville.getById(rs.getInt("id_ville_arrivee"), con));
            }
        } catch (Exception e) {
            throw new Exception("Failed to retrieve Vol by ID", e);
        } finally {
            // Fermeture des ressources
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
        }
    
        return instance;
    }

    public static Vol[] getAll(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Vol> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM vol ORDER BY id ASC";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Vol item = new Vol();
                item.setId(rs.getInt("id"));
                item.setNumero_vol(rs.getString("numero_vol"));
                item.setIdAvion(rs.getInt("id_avion"));
                item.setId_ville_depart(rs.getInt("id_ville_depart"));
                item.setId_ville_arrivee(rs.getInt("id_ville_arrivee"));
                item.setDate_depart(rs.getTimestamp("date_depart"));
                item.setDate_arrivee(rs.getTimestamp("date_arrivee"));
                item.setPrix_economique(rs.getDouble("prix_economique"));
                item.setPrix_business(rs.getDouble("prix_business"));
                item.setDate_limite_reservation(rs.getTimestamp("date_limite_reservation"));
                item.setReduction(rs.getDouble("reduction"));
                item.setPro_max_eco(rs.getInt("mx_eco"));
                item.setPro_max_bus(rs.getInt("mx_bus"));
                item.setInsert_date(rs.getTimestamp("insert_date"));
                item.setDisponible(rs.getBoolean("disponible"));
                item.setAvion(Avion.getById(rs.getInt("id_avion"), con));
                item.setVille_depart(Ville.getById(rs.getInt("id_ville_depart"), con));
                item.setVille_arrivee(Ville.getById(rs.getInt("id_ville_arrivee"), con));

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

        return items.toArray(new Vol[0]);
    }

    public void insert(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            // Requête SQL avec tous les champs obligatoires et optionnels
            String query = "INSERT INTO vol (numero_vol, id_avion, id_ville_depart, id_ville_arrivee, date_depart, date_arrivee, prix_economique, prix_business, date_limite_reservation, reduction, mx_eco, mx_bus) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";

            st = con.prepareStatement(query);

            // Génération du numéro de vol (si nécessaire)
            String numeroVol = "VOL-N-" + getNextVolNumber(con); // Assurez-vous que cette méthode est définie
            st.setString(1, numeroVol);

            // Remplissage des autres paramètres
            st.setInt(2, this.idAvion);
            st.setInt(3, this.id_ville_depart);
            st.setInt(4, this.id_ville_arrivee);
            st.setTimestamp(5, this.date_depart);
            st.setTimestamp(6, this.date_arrivee);
            st.setDouble(7, this.prix_economique);
            st.setDouble(8, this.prix_business);
            st.setTimestamp(9, this.date_limite_reservation);
            st.setDouble(10, this.reduction); // Champ optionnel
            st.setInt(11, this.pro_max_eco); // mx_eco dans la table
            st.setInt(12, this.pro_max_bus); // mx_bus dans la table

            // Exécution de la requête
            rs = st.executeQuery();

            // Récupération de l'ID généré
            if (rs.next()) {
                this.setId(rs.getInt("id"));
                con.commit(); // Validation de la transaction
            } else {
                con.rollback(); // Annulation de la transaction en cas d'échec
                throw new Exception("Failed to retrieve generated ID");
            }
        } catch (Exception e) {
            con.rollback(); // Annulation de la transaction en cas d'erreur
            throw new Exception("Failed to insert record", e);
        } finally {
            // Fermeture des ressources
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
        }
    }

    // Fonction pour récupérer le prochain numéro de vol
    private int getNextVolNumber(Connection con) throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT NEXTVAL('vol_ref')");
        int nextVal = rs.next() ? rs.getInt(1) : 1;
        rs.close();
        st.close();
        return nextVal;
    }

    public void update(Connection con) throws Exception {
        PreparedStatement st = null;
        try {
            // Requête SQL avec tous les champs modifiables
            String query = "UPDATE vol SET "
                    + "numero_vol = ?, "
                    + "id_avion = ?, "
                    + "id_ville_depart = ?, "
                    + "id_ville_arrivee = ?, "
                    + "date_depart = ?, "
                    + "date_arrivee = ?, "
                    + "prix_economique = ?, "
                    + "prix_business = ?, "
                    + "date_limite_reservation = ?, "
                    + "reduction = ?, "
                    + "mx_eco = ?, "
                    + "mx_bus = ?, "
                    + "disponible = ? "
                    + "WHERE id = ?";
    
            st = con.prepareStatement(query);
    
            // Remplissage des paramètres
            st.setString(1, this.numero_vol);
            st.setInt(2, this.idAvion); // Utilisation de idAvion au lieu de avion.getId()
            st.setInt(3, this.id_ville_depart); // Utilisation de id_ville_depart au lieu de ville_depart.getId()
            st.setInt(4, this.id_ville_arrivee); // Utilisation de id_ville_arrivee au lieu de ville_arrivee.getId()
            st.setTimestamp(5, this.date_depart);
            st.setTimestamp(6, this.date_arrivee);
            st.setDouble(7, this.prix_economique);
            st.setDouble(8, this.prix_business);
            st.setTimestamp(9, this.date_limite_reservation);
            st.setDouble(10, this.reduction); // Champ optionnel
            st.setInt(11, this.pro_max_eco); // mx_eco dans la table
            st.setInt(12, this.pro_max_bus); // mx_bus dans la table
            st.setBoolean(13, this.disponible);
            st.setInt(14, this.getId()); // Clause WHERE
    
            // Exécution de la requête
            int rowsUpdated = st.executeUpdate();
    
            // Vérification du succès de la mise à jour
            if (rowsUpdated > 0) {
                con.commit(); // Validation de la transaction
            } else {
                con.rollback(); // Annulation de la transaction si aucune ligne n'est mise à jour
                throw new Exception("No record found to update");
            }
        } catch (Exception e) {
            con.rollback(); // Annulation de la transaction en cas d'erreur
            throw new Exception("Failed to update record", e);
        } finally {
            // Fermeture des ressources
            if (st != null)
                st.close();
        }
    }

    public static void deleteById(int id) throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;
        try {
            String query = "DELETE FROM vol WHERE id = ?";
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