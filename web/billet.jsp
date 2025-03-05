<%@ include file="header.jsp" %>
<%@page import="model.Billet"%>

<%
  Billet[] listBillet = (Billet[]) request.getAttribute("listBillet");
%>
    <section class="section dashboard">
      <div class="row">

        <!-- Left side columns -->
        <div class="col-lg-12">
          <div class="row">

            <% for(int n = 0; n < listBillet.length; n++) { %>
            <!-- Top Selling -->
            <div class="col-12">

              <div class="card">
                <div class="card-body">
                  <h2 class="card-title">BILLET-<%= listBillet[n].getId() %></h2>
                  <h2 class="card-title">Nom : <%= listBillet[n].getUser().getNom() %></h2>
                  <div class="row-fluid">
                    <div class="card-fluid">
                      <!-- Default Table -->
                      <table class="table">
                        <thead>
                          <tr>
                            <th scope="col">#REF-VOL</th>
                            <th scope="col">#ID R&eacute;servation</th>
                            <th scope="col">Type de si&egrave;ge</th>
                            <th scope="col">Prix</th>
                          </tr>
                        </thead>
                        <tbody>
                            <tr>
                              <td>VOL-<%= listBillet[n].getId_vol() %></td>
                              <td>RES-<%= listBillet[n].getId_reservation() %></td>
                              <td><%= listBillet[n].getSiege().getNom() %></td>
                              <td><%= listBillet[n].getPrix_final() %></td>
                            </tr>
                        </tbody>
                      </table>

                    </div><br><br>
                  </div>
                  <h5 class="alert alert-primary">date depart : <%= listBillet[n].getReservation().getVol().getDate_depart() %></h5>
                </div>
              </div>
            </div><!-- End Top Selling -->
            <% } %>

          </div>
        </div><!-- End Left side columns -->

      </div>
    </section>

<%@ include file="footer.jsp" %>