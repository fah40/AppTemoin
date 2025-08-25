<%@ include file="header.jsp" %>
<%@page import="model.Reservation"%>

<%
  Reservation[] listReservation=(Reservation[]) request.getAttribute("listReservation");
%>
    <section class="section dashboard">
      <div class="row">

        <!-- Left side columns -->
        <div class="col-lg-12">

          <div class="row">

            <% for(int n = 0; n < listReservation.length; n++) { %>
            <!-- Reservation -->
            <div class="col-12">

              <div class="card">
                <div class="card-body">
                  
                  <div class="card-title row g-3">
                    <div class="col-md-3">
                       Reservation-<%= listReservation[n].getId() %> 
                    </div>
                    <div class="col-md-6"></div>
                    <div class="col-md-3">
                      <a class="btn btn-success" style="text-align: end;" href="exporter?idRes=<%= listReservation[n].getId() %>">Exporter</a></div>
                    </div>
                  <h2 class="card-title"> reserv&eacute; par : <%= listReservation[n].getUser().getNom() %></h2>
                  <div class="row-fluid">
                    <div class="card-fluid">
                      <!-- Default Table -->
                      <table class="table">
                        <thead>
                          <tr>
                            <th scope="col">#REF-VOL</th>
                            <th scope="col">Type de si&egrave;ge</th>
                            <th scope="col">Nombre adulte</th>
                            <th scope="col">Nombre enfant</th>
                          </tr>
                        </thead>
                        <tbody>
                            <tr>
                              <td>VOL-<%= listReservation[n].getId_vol() %></td>
                              <td><%= listReservation[n].getType().getNom() %></td>
                              <td><%= listReservation[n].getNombreAdulte() %></td>
                              <td><%= listReservation[n].getNombreEnfant() %></td>
                            </tr>
                        </tbody>
                      </table>

                    </div><br><br>
                  </div>
                  <h5 class="alert alert-primary">date reservation : <%= listReservation[n].getDate_reservation() %></h5>
                </div>
              </div>
            </div><!-- End Top Selling -->
            <% } %>

          </div>
        </div><!-- End Left side columns -->

      </div>
    </section>

<%@ include file="footer.jsp" %>