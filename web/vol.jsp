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
        <div class="col-lg-9">
          <div class="row">

            <!-- Top Selling -->
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
                        <th scope="col">Ville depart</th>
                        <th scope="col">destination</th>
                        <th scope="col">date depart</th>
                        <th scope="col">date arrivee</th>
                        <th scope="col">prix business</th>
                        <th scope="col">prix economique</th>
                        <th scope="col">date limite reservation</th>
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

        <!-- Right side columns -->
        <div class="col-lg-3">
           <div class="card">
                <div class="card-body">
                  <!-- Multi Columns Form -->
                  <form class="row g-3" action="insertVol" method="post">
                    <h5 class="card-title">Insertion vol</h5>

                    <div class="col-md-12">
                      <label for="" class="form-label">Avion</label>
                      <select id="inputState" class="form-select" name="vol.idAvion">
                        <option selected>choisir...</option>
                        <% for(int x=0; x < listAvion.length; x++) { %>
                          <option value="<%=listAvion[x].getId()%>"><%=listAvion[x].getModele()%></option>
                        <% } %>
                      </select>
                    </div>

                    <div class="col-md-12">
                      <label for="" class="form-label">Ville départ</label>
                      <select id="inputState" class="form-select" name="vol.id_ville_depart">
                        <option selected>choisir...</option>
                        <% for(int c=0; c < listVille.length; c++) { %>
                          <option value="<%=listVille[c].getId()%>"><%=listVille[c].getNom()%></option>
                        <% } %>
                      </select>
                    </div>

                    <div class="col-md-12">
                      <label for="" class="form-label">Ville destination</label>
                      <select id="inputState" class="form-select" name="vol.id_ville_arrivee">
                        <option selected>choisir...</option>
                        <% for(int c=0; c < listVille.length; c++) { %>
                          <option value="<%=listVille[c].getId()%>"><%=listVille[c].getNom()%></option>
                        <% } %>
                      </select>
                    </div>

                    <div class="col-md-12">
                      <label for="" class="form-label">Date départ</label>
                      <input type="datetime-local" class="form-control" id="" name="vol.date_depart">
                    </div>

                    <div class="col-md-12">
                      <label for="" class="form-label">Date arrivée</label>
                      <input type="datetime-local" class="form-control" id="" name="vol.date_arrivee">
                    </div>

                    <div class="col-md-8">
                      <label for="" class="form-label">Prix économique</label>
                      <input type="number" class="form-control" id="" name="vol.prix_economique">
                    </div>

                    <div class="col-md-8">
                      <label for="" class="form-label">Prix business</label>
                      <input type="number" class="form-control" id="" name="vol.prix_business">
                    </div>

                    <div class="col-md-12">
                      <label for="" class="form-label">Date limite réservation</label>
                      <input type="datetime-local" class="form-control" id="" name="vol.date_limite_reservation">
                    </div>

                    <div class="text-center">
                      <button type="submit" class="btn btn-warning">Save</button>
                      <button type="reset" class="btn btn-secondary">Reset</button>
                    </div>

                  </form>


                </div>
              </div>
        </div><!-- End Right side columns -->

      </div>
    </section>


<%@ include file="footer.jsp" %>