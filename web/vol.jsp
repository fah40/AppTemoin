<%@ include file="header.jsp" %>
<%@page import="model.Vol"%>
<%@page import="model.Avion"%>
<%@page import="model.Ville"%>
<%
  Vol[] listVol=(Vol[]) request.getAttribute("listVol");
  Avion[] listAvion=(Avion[]) request.getAttribute("listAvion");
  Ville[] listVille=(Ville[]) request.getAttribute("listVille");
%>
    <section class="section dashboard">
      <div class="row">

        <!-- Left side columns -->
        <div class="col-lg-12">
          <div class="row">

        <!-- top side columns -->
        <div class="col-lg-12">
           <div class="card">
                <div class="card-body">
                  <!-- Multi Columns Form -->
                  <form class="row g-3" action="insertVol" method="post">
                    <h5 class="card-title">Insertion vol</h5>

                    <!-- S&eacute;lection de l'avion -->
                    <div class="col-md-12">
                        <label for="idAvion" class="form-label">Avion</label>
                        <select id="idAvion" class="form-select" name="vol.idAvion">
                            <option selected>choisir...</option>
                            <% for(int x=0; x < listAvion.length; x++) { %>
                                <option value="<%=listAvion[x].getId()%>"><%=listAvion[x].getModele()%></option>
                            <% } %>
                        </select>
                    </div>

                    <!-- S&eacute;lection de la ville de d&eacute;part -->
                    <div class="col-md-6">
                        <label for="id_ville_depart" class="form-label">Ville d&eacute;part</label>
                        <select id="id_ville_depart" class="form-select" name="vol.id_ville_depart">
                            <option selected>choisir...</option>
                            <% for(int c=0; c < listVille.length; c++) { %>
                                <option value="<%=listVille[c].getId()%>"><%=listVille[c].getNom()%></option>
                            <% } %>
                        </select>
                    </div>

                    <!-- S&eacute;lection de la ville d'arriv&eacute;e -->
                    <div class="col-md-6">
                        <label for="id_ville_arrivee" class="form-label">Ville destination</label>
                        <select id="id_ville_arrivee" class="form-select" name="vol.id_ville_arrivee">
                            <option selected>choisir...</option>
                            <% for(int c=0; c < listVille.length; c++) { %>
                                <option value="<%=listVille[c].getId()%>"><%=listVille[c].getNom()%></option>
                            <% } %>
                        </select>
                    </div>

                    <!-- Date de d&eacute;part -->
                    <div class="col-md-6">
                        <label for="date_depart" class="form-label">Date d&eacute;part</label>
                        <input type="datetime-local" class="form-control" id="date_depart" name="vol.date_depart">
                    </div>

                    <!-- Date d'arriv&eacute;e -->
                    <div class="col-md-6">
                        <label for="date_arrivee" class="form-label">Date arriv&eacute;e</label>
                        <input type="datetime-local" class="form-control" id="date_arrivee" name="vol.date_arrivee">
                    </div>

                    <!-- Prix &eacute;conomique -->
                    <div class="col-md-6">
                        <label for="prix_economique" class="form-label">Prix &eacute;conomique</label>
                        <input type="number" class="form-control" id="prix_economique" name="vol.prix_economique">
                    </div>

                    <!-- Prix business -->
                    <div class="col-md-6">
                        <label for="prix_business" class="form-label">Prix business</label>
                        <input type="number" class="form-control" id="prix_business" name="vol.prix_business">
                    </div>

                    <!-- Date limite de r&eacute;servation -->
                    <div class="col-md-6">
                        <label for="date_limite_reservation" class="form-label">Date limite r&eacute;servation</label>
                        <input type="datetime-local" class="form-control" id="date_limite_reservation" name="vol.date_limite_reservation">
                    </div>

                    <!-- R&eacute;duction -->
                    <div class="col-md-6">
                        <label for="reduction" class="form-label">R&eacute;duction</label>
                        <input type="number" step="0.01" class="form-control" id="reduction" name="vol.reduction">
                    </div>

                    <!-- Nombre maximum de si&egrave;ges &eacute;conomiques -->
                    <div class="col-md-6">
                        <label for="pro_max_eco" class="form-label">Nombre max promo &eacute;conomiques</label>
                        <input type="number" class="form-control" id="pro_max_eco" name="vol.pro_max_eco">
                    </div>

                    <!-- Nombre maximum de si&egrave;ges business -->
                    <div class="col-md-6">
                        <label for="pro_max_bus" class="form-label">Nombre max promo business</label>
                        <input type="number" class="form-control" id="pro_max_bus" name="vol.pro_max_bus">
                    </div>

                    <!-- Boutons de soumission et de r&eacute;initialisation -->
                    <div class="text-center">
                        <button type="submit" class="btn btn-warning">Save</button>
                        <button type="reset" class="btn btn-secondary">Reset</button>
                    </div>
                </form>
                </div>
              </div>
        </div><!-- End Right side columns -->

            <!-- down side columns -->
            <div class="col-12">

              <div class="card">
                <div class="card-body">
                  <h5 class="card-title" >liste vol</h5>
                  <!-- Default Table -->
                  <table class="table">
                    <thead>
                      <tr>
                          <th scope="col">#ref-vol</th>
                          <th scope="col">#ref-Avion</th>
                          <th scope="col">Ville d&eacute;part</th>
                          <th scope="col">Destination</th>
                          <th scope="col">Date d&eacute;part</th>
                          <th scope="col">Date arriv&eacute;e</th>
                          <th scope="col">Prix business</th>
                          <th scope="col">Prix &eacute;conomique</th>
                          <th scope="col">Date limite r&eacute;servation</th>
                          <th scope="col">R&eacute;duction</th>
                          <th scope="col">Si&egrave;ges max &eacute;co</th>
                          <th scope="col">Si&egrave;ges max bus</th>
                          <th scope="col">Disponible</th>
                      </tr>
                    </thead>
                    <tbody>
                      <% for(int n=0;n< listVol.length;n++) {%>
                        <tr>
                          <th scope="row"><%=listVol[n].getNumero_vol() %></th>
                          <th scope="row"><%=listVol[n].getAvion().getModele() %></th>
                          <td><%=listVol[n].getVille_depart().getNom() %></td>
                          <td><%=listVol[n].getVille_arrivee().getNom() %></td>
                          <td><%=listVol[n].getDate_depart() %></td>
                          <td><%=listVol[n].getDate_arrivee() %></td>
                          <td><%=listVol[n].getPrix_economique() %></td>
                          <td><%=listVol[n].getPrix_business() %></td>
                          <td><%=listVol[n].getDate_limite_reservation() %></td>
                          <td><%=listVol[n].getReduction() %></td>
                          <td><%=listVol[n].getPro_max_eco() %></td>
                          <td><%=listVol[n].getPro_max_bus() %></td>
                          <td><%=listVol[n].getDisponible() %></td>
                        </tr>
                      <%}%>
                    </tbody>
                  </table>
                  <!-- End Default Table Example -->
                </div>
              </div>
            </div><!-- End Top Selling -->

          </div>
        </div><!-- End Left side columns -->


      </div>
    </section>


<%@ include file="footer.jsp" %>