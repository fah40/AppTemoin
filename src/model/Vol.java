package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bd.MyConnect;

public class Vol {
    private int id;
    private String numeroVol;
    private int idAvion;
    private int idVilleDepart;
    private int idVilleArrivee;
    private Timestamp dateDepart;
    private Timestamp dateArrivee;
    private double prixEconomique;
    private double prixBusiness;

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroVol() {
        return numeroVol;
    }

    public void setNumeroVol(String numeroVol) {
        this.numeroVol = numeroVol;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    public int getIdVilleDepart() {
        return idVilleDepart;
    }

    public void setIdVilleDepart(int idVilleDepart) {
        this.idVilleDepart = idVilleDepart;
    }

    public int getIdVilleArrivee() {
        return idVilleArrivee;
    }

    public void setIdVilleArrivee(int idVilleArrivee) {
        this.idVilleArrivee = idVilleArrivee;
    }

    public Timestamp getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Timestamp dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Timestamp getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(Timestamp dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public double getPrixEconomique() {
        return prixEconomique;
    }

    public void setPrixEconomique(double prixEconomique) {
        this.prixEconomique = prixEconomique;
    }

    public double getPrixBusiness() {
        return prixBusiness;
    }

    public void setPrixBusiness(double prixBusiness) {
        this.prixBusiness = prixBusiness;
    }

    // Méthodes pour les opérations CRUD
    public void insert() throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;

        try {
            String query = "INSERT INTO vol (numero_vol, id_avion, id_ville_depart, id_ville_arrivee, date_depart, date_arrivee, prix_economique, prix_business) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            st = con.prepareStatement(query);
            st.setString(1, this.numeroVol);
            st.setInt(2, this.idAvion);
            st.setInt(3, this.idVilleDepart);
            st.setInt(4, this.idVilleArrivee);
            st.setTimestamp(5, this.dateDepart);
            st.setTimestamp(6, this.dateArrivee);
            st.setDouble(7, this.prixEconomique);
            st.setDouble(8, this.prixBusiness);

            try {
                st.executeUpdate();
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Échec de l'insertion du vol", e);
            }
        } finally {
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }
    }

    public static Vol getById(int id) throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        Vol instance = null;

        try {
            String query = "SELECT * FROM vol WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new Vol();
                instance.setId(rs.getInt("id"));
                instance.setNumeroVol(rs.getString("numero_vol"));
                instance.setIdAvion(rs.getInt("id_avion"));
                instance.setIdVilleDepart(rs.getInt("id_ville_depart"));
                instance.setIdVilleArrivee(rs.getInt("id_ville_arrivee"));
                instance.setDateDepart(rs.getTimestamp("date_depart"));
                instance.setDateArrivee(rs.getTimestamp("date_arrivee"));
                instance.setPrixEconomique(rs.getDouble("prix_economique"));
                instance.setPrixBusiness(rs.getDouble("prix_business"));
            }
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }

        return instance;
    }

    public static Vol[] getAll() throws Exception {
        Connection con = MyConnect.getConnection();
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
                item.setNumeroVol(rs.getString("numero_vol"));
                item.setIdAvion(rs.getInt("id_avion"));
                item.setIdVilleDepart(rs.getInt("id_ville_depart"));
                item.setIdVilleArrivee(rs.getInt("id_ville_arrivee"));
                item.setDateDepart(rs.getTimestamp("date_depart"));
                item.setDateArrivee(rs.getTimestamp("date_arrivee"));
                item.setPrixEconomique(rs.getDouble("prix_economique"));
                item.setPrixBusiness(rs.getDouble("prix_business"));

                items.add(item);
            }
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }

        return items.toArray(new Vol[0]);
    }

    public void update() throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;

        try {
            String query = "UPDATE vol SET numero_vol = ?, id_avion = ?, id_ville_depart = ?, id_ville_arrivee = ?, date_depart = ?, date_arrivee = ?, prix_economique = ?, prix_business = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setString(1, this.numeroVol);
            st.setInt(2, this.idAvion);
            st.setInt(3, this.idVilleDepart);
            st.setInt(4, this.idVilleArrivee);
            st.setTimestamp(5, this.dateDepart);
            st.setTimestamp(6, this.dateArrivee);
            st.setDouble(7, this.prixEconomique);
            st.setDouble(8, this.prixBusiness);
            st.setInt(9, this.id);

            try {
                st.executeUpdate();
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Échec de la mise à jour du vol", e);
            }
        } finally {
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
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
                throw new Exception("Échec de la suppression du vol", e);
            }
        } finally {
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }
    }

    public static Vol[] search(String numeroVol, Integer idAvion, Integer idVilleDepart, Integer idVilleArrivee, Timestamp dateDepart, Timestamp dateArrivee) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Vol> items = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM vol WHERE 1=1");

        try {
            // Construction de la requête avec les conditions dynamiques
            if (numeroVol != null && !numeroVol.isEmpty()) {
                query.append(" AND numero_vol LIKE ?");
            }
            if (idAvion != null) {
                query.append(" AND id_avion = ?");
            }
            if (idVilleDepart != null) {
                query.append(" AND id_ville_depart = ?");
            }
            if (idVilleArrivee != null) {
                query.append(" AND id_ville_arrivee = ?");
            }
            if (dateDepart != null) {
                query.append(" AND date_depart >= ?");
            }
            if (dateArrivee != null) {
                query.append(" AND date_arrivee <= ?");
            }

            // Connexion à la base de données
            con = MyConnect.getConnection();
            st = con.prepareStatement(query.toString());

            // Définition des paramètres dans la requête
            int paramIndex = 1;
            if (numeroVol != null && !numeroVol.isEmpty()) {
                st.setString(paramIndex++, "%" + numeroVol + "%");
            }
            if (idAvion != null) {
                st.setInt(paramIndex++, idAvion);
            }
            if (idVilleDepart != null) {
                st.setInt(paramIndex++, idVilleDepart);
            }
            if (idVilleArrivee != null) {
                st.setInt(paramIndex++, idVilleArrivee);
            }
            if (dateDepart != null) {
                st.setTimestamp(paramIndex++, dateDepart);
            }
            if (dateArrivee != null) {
                st.setTimestamp(paramIndex++, dateArrivee);
            }

            // Exécution de la requête
            rs = st.executeQuery();

            // Traitement des résultats
            while (rs.next()) {
                Vol item = new Vol();
                item.setId(rs.getInt("id"));
                item.setNumeroVol(rs.getString("numero_vol"));
                item.setIdAvion(rs.getInt("id_avion"));
                item.setIdVilleDepart(rs.getInt("id_ville_depart"));
                item.setIdVilleArrivee(rs.getInt("id_ville_arrivee"));
                item.setDateDepart(rs.getTimestamp("date_depart"));
                item.setDateArrivee(rs.getTimestamp("date_arrivee"));
                item.setPrixEconomique(rs.getDouble("prix_economique"));
                item.setPrixBusiness(rs.getDouble("prix_business"));

                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erreur lors de la recherche des vols : " + e.getMessage());
        } finally {
            // Fermeture des ressources
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }

        return items.toArray(new Vol[0]);
    }
}
