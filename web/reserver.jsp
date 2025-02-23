<%@ include file="header.jsp" %>
<%@page import="model.Vol"%>
<%@page import="model.Avion"%>
<%@page import="model.Ville"%>
<%@page import="model.User"%>

<%
  Vol myVol= (Vol)request.getAttribute("myVol");
  int id_user= ((User)session.getAttribute("user")).getId();
%>
    <section class="section dashboard">
      <div class="row">

        <!-- Left side columns -->
        <div class="col-lg-12">
          <div class="row">

            <!-- Top Selling -->
            <div class="col-12">

              <div class="card">
                <div class="card-body">
                  <!-- Default Table -->
                  <h5 class="card-title" >vol</h5>
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
                            <tr>
                              <th scope="row"><%=myVol.getNumero_vol() %></th>
                              <th scope="row"><%=myVol.getAvion().getModele() %></th>
                              <td><%=myVol.getVille_depart().getNom() %></td>
                              <td><%=myVol.getVille_arrivee().getNom() %></td>
                              <td><%=myVol.getDate_depart() %></td>
                              <td><%=myVol.getDate_arrivee() %></td>
                              <td><%=myVol.getPrix_economique() %></td>
                              <td><%=myVol.getPrix_business() %></td>
                              <td><%=myVol.getDate_limite_reservation() %></td>
                            </tr>
                        </tbody>
                      </table>

                  <!-- End Default Table Example -->
                <h3 class="alert alert-warning">Reserver</h3>

                  <!-- Multi Columns Form -->
                  <form class="row g-3" action="saveReservation" method="post">

                      <input type="hidden" name="id_vol_curr" value="<%= myVol.getId() %>">
                      <input type="hidden" name="reservation.id_vol" value="<%= myVol.getId() %>">
                      <!-- Champ caché pour l'utilisateur -->
                      <input type="hidden" name="reservation.id_user" value="<%= id_user %>">

                      <!-- Nombre de places réservées -->
                      <div class="col-md-3">
                          <label for="inputNombre" class="form-label">Nombre de places</label>
                          <input type="number" class="form-control" id="inputNombre" name="reservation.nombre" min="1">
                      </div>

                      <!-- Date de réservation -->
                      <div class="col-md-3">
                          <label for="inputDateReservation" class="form-label">Date de reservation</label>
                          <input type="datetime-local" class="form-control" id="inputDateReservation" name="reservation.date_reservation">
                      </div>

                      <!-- Boutons d'action -->
                      <div class="text-center">
                          <button type="submit" class="btn btn-success">Reserver</button>
                          <button type="reset" class="btn btn-secondary">Reset</button>
                      </div>
                  </form>

                </div>
              </div>
            </div><!-- End Top Selling -->

          </div>
        </div><!-- End Left side columns -->

      </div>
    </section>

<%@ include file="footer.jsp" %>