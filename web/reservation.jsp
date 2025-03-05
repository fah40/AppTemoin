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

            <!-- Top Selling -->
            <div class="col-12">

              <div class="card">
                <div class="card-body">
                  <h2 class="card-title">Reserver </h2>
                  <form class="row g-3" action="ResultService" method="get">
                    <div class="col-md-2">
                      <select id="inputState" class="form-select" name="idAvion">
                        <option selected>choisir...</option>
                        <% for(int x=0;x< listAvion.length;x++) {%>
                        <option value="<%=listAvion[x].getId()%>"><%=listAvion[x].getModele()%></option>
                        <% } %>
                      </select>
                    </div>
                    <div class="col-md-2">
                      <select id="inputState" class="form-select" name="idDestination">
                        <option selected>destination ...</option>
                        <% for(int c=0;c< listVille.length;c++) {%>
                        <option value="<%=listVille[c].getId()%>"><%=listVille[c].getNom()%></option>
                        <% } %>
                      </select>
                    </div>
                    <div class="col-md-2">
                      <input type="datetime-local" class="form-control" id="" name="datemin">
                    </div>
                    <div class="col-md-2">
                      <input type="datetime-local" class="form-control" id="" name="datemax">
                    </div>
                    <div class="col-md-2">
                      <button type="submit" class="btn btn-primary">show</button>
                    </div>
                  </form><br>
                  <div class="row-fluid">
                    <div class="card-fluid">
                      <h5 class="card-title" >liste vol</h5>
                      <!-- Default Table -->
                      <table class="table">
                        <thead>
                          <tr>
                            <th scope="col">-</th>
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
                              <th scope="row"><a class="btn btn-primary" href="reserver?idVol=<%=listVol[n].getId() %>">reserver</a></th>
                              <th scope="row"><%=listVol[n].getAvion().getModele() %></th>
                              <td><%=listVol[n].getVille_depart().getNom() %></td>
                              <td><%=listVol[n].getVille_arrivee().getNom() %></td>
                              <td><%=listVol[n].getDate_depart() %></td>
                              <td><%=listVol[n].getDate_arrivee() %></td>
                              <td><%=listVol[n].getPrix_economique() %></td>
                              <td><%=listVol[n].getPrix_business() %></td>
                              <td><%=listVol[n].getDate_limite_reservation() %></td>
                            </tr>
                          <% } %>
                        </tbody>
                      </table>

                    </div><br><br>
                  </div>
                </div>
              </div>
            </div><!-- End Top Selling -->

          </div>
        </div><!-- End Left side columns -->

      </div>
    </section>

<%@ include file="footer.jsp" %>