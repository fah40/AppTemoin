package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bd.MyConnect;

public class Avion {
    private int id;
    private String modele;
    private int nbrSiegeEconomique;
    private int nbrSiegeBusiness;

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getNbrSiegeEconomique() {
        return nbrSiegeEconomique;
    }

    public void setNbrSiegeEconomique(int nbrSiegeEconomique) {
        this.nbrSiegeEconomique = nbrSiegeEconomique;
    }

    public int getNbrSiegeBusiness() {
        return nbrSiegeBusiness;
    }

    public void setNbrSiegeBusiness(int nbrSiegeBusiness) {
        this.nbrSiegeBusiness = nbrSiegeBusiness;
    }

    // Méthodes pour les opérations CRUD
    public void insert() throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;

        try {
            String query = "INSERT INTO avion (modele, nbr_siege_economique, nbr_siege_business) VALUES (?, ?, ?)";
            st = con.prepareStatement(query);
            st.setString(1, this.modele);
            st.setInt(2, this.nbrSiegeEconomique);
            st.setInt(3, this.nbrSiegeBusiness);

            try {
                st.executeUpdate();
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Échec de l'insertion de l'avion", e);
            }
        } finally {
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }
    }

    public static Avion getById(int id) throws Exception {
        Connection con = MyConnect.getConnection();
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
                instance.setNbrSiegeEconomique(rs.getInt("nbr_siege_economique"));
                instance.setNbrSiegeBusiness(rs.getInt("nbr_siege_business"));
            }
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }

        return instance;
    }

    public static Avion[] getAll() throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Avion> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM avion ORDER BY id ASC";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Avion item = new Avion();
                item.setId(rs.getInt("id"));
                item.setModele(rs.getString("modele"));
                item.setNbrSiegeEconomique(rs.getInt("nbr_siege_economique"));
                item.setNbrSiegeBusiness(rs.getInt("nbr_siege_business"));

                items.add(item);
            }
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }

        return items.toArray(new Avion[0]);
    }

    public void update() throws Exception {
        Connection con = MyConnect.getConnection();
        PreparedStatement st = null;

        try {
            String query = "UPDATE avion SET modele = ?, nbr_siege_economique = ?, nbr_siege_business = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setString(1, this.modele);
            st.setInt(2, this.nbrSiegeEconomique);
            st.setInt(3, this.nbrSiegeBusiness);
            st.setInt(4, this.id);

            try {
                st.executeUpdate();
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Échec de la mise à jour de l'avion", e);
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
            String query = "DELETE FROM avion WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);

            try {
                st.executeUpdate();
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Échec de la suppression de l'avion", e);
            }
        } finally {
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }
    }

    public static Avion[] search(String modele, Integer nbrSiegeEconomique, Integer nbrSiegeBusiness) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Avion> items = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM avion WHERE 1=1");

        try {
            // Construction de la requête avec les conditions dynamiques
            if (modele != null && !modele.isEmpty()) {
                query.append(" AND modele LIKE ?");
            }
            if (nbrSiegeEconomique != null) {
                query.append(" AND nbr_siege_economique = ?");
            }
            if (nbrSiegeBusiness != null) {
                query.append(" AND nbr_siege_business = ?");
            }

            // Connexion à la base de données
            con = MyConnect.getConnection();
            st = con.prepareStatement(query.toString());

            // Définition des paramètres dans la requête
            int paramIndex = 1;
            if (modele != null && !modele.isEmpty()) {
                st.setString(paramIndex++, "%" + modele + "%");
            }
            if (nbrSiegeEconomique != null) {
                st.setInt(paramIndex++, nbrSiegeEconomique);
            }
            if (nbrSiegeBusiness != null) {
                st.setInt(paramIndex++, nbrSiegeBusiness);
            }

            // Exécution de la requête
            rs = st.executeQuery();

            // Traitement des résultats
            while (rs.next()) {
                Avion item = new Avion();
                item.setId(rs.getInt("id"));
                item.setModele(rs.getString("modele"));
                item.setNbrSiegeEconomique(rs.getInt("nbr_siege_economique"));
                item.setNbrSiegeBusiness(rs.getInt("nbr_siege_business"));

                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erreur lors de la recherche des avions : " + e.getMessage());
        } finally {
            // Fermeture des ressources
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !con.isClosed()) con.close();
        }

        return items.toArray(new Avion[0]);
    }
}
