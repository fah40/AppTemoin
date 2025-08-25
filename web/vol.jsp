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

        <div class="col-lg-12">
           <div class="card">
                <div class="card-body">

                  <form class="row g-3" action="insertVol" method="post">
                    <h5 class="card-title">Insertion vol</h5>

                    <div class="col-md-12">
                        <label for="idAvion" class="form-label">Avion</label>
                        <select id="idAvion" class="form-select" name="vol.idAvion">
                            <option selected>choisir...</option>
                            <% for(int x=0; x < listAvion.length; x++) { %>
                                <option value="<%=listAvion[x].getId()%>"><%=listAvion[x].getModele()%></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="col-md-6">
                        <label for="id_ville_depart" class="form-label">Ville d&eacute;part</label>
                        <select id="id_ville_depart" class="form-select" name="vol.id_ville_depart">
                            <option selected>choisir...</option>
                            <% for(int c=0; c < listVille.length; c++) { %>
                                <option value="<%=listVille[c].getId()%>"><%=listVille[c].getNom()%></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="col-md-6">
                        <label for="id_ville_arrivee" class="form-label">Ville destination</label>
                        <select id="id_ville_arrivee" class="form-select" name="vol.id_ville_arrivee">
                            <option selected>choisir...</option>
                            <% for(int c=0; c < listVille.length; c++) { %>
                                <option value="<%=listVille[c].getId()%>"><%=listVille[c].getNom()%></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="col-md-6">
                        <label for="date_depart" class="form-label">Date d&eacute;part</label>
                        <input type="datetime-local" class="form-control" id="date_depart" name="vol.date_depart">
                    </div>

                    <div class="col-md-6">
                        <label for="date_arrivee" class="form-label">Date arriv&eacute;e</label>
                        <input type="datetime-local" class="form-control" id="date_arrivee" name="vol.date_arrivee">
                    </div>

                    <div class="col-md-6">
                        <label for="prix_economique" class="form-label">Prix &eacute;conomique</label>
                        <input type="number" class="form-control" id="prix_economique" name="vol.prix_economique">
                    </div>

                    <div class="col-md-6">
                        <label for="prix_business" class="form-label">Prix business</label>
                        <input type="number" class="form-control" id="prix_business" name="vol.prix_business">
                    </div>

                    <div class="col-md-6">
                        <label for="date_limite_reservation" class="form-label">Date limite r&eacute;servation</label>
                        <input type="datetime-local" class="form-control" id="date_limite_reservation" name="vol.date_limite_reservation">
                    </div>

                    <div class="col-md-6"></div>
                    
                    <div class="col-md-12"><hr></div>
                    <div class="col-md-12 text-center"><h3>Promotion</h3></div>

                    <div class="col-md-6">
                        <label for="prix_economique" class="form-label">Prix &eacute;conomique</label>
                        <input type="number" class="form-control" id="prix_economique" name="prix_economique">
                    </div>

                    <div class="col-md-6">
                        <label for="prix_business" class="form-label">Prix business</label>
                        <input type="number" class="form-control" id="prix_business" name="prix_business">
                    </div>

                    <div class="col-md-6">
                        <label for="date_limite_reservation" class="form-label">Date d&eacute;but</label>
                        <input type="datetime-local" class="form-control" id="date_limite_reservation" name="date_debut">
                    </div>

                    <div class="col-md-6">
                        <label for="date_limite_reservation" class="form-label">Date fin</label>
                        <input type="datetime-local" class="form-control" id="date_limite_reservation" name="date_fin">
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-primary">Save</button>
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
                  <table class="table" style="font-size: 13px;">
                    <thead>
                      <tr>
                          <th scope="col">#ref-vol</th>
                          <th scope="col">#ref-Avion</th>
                          <th scope="col">Ville d&eacute;part</th>
                          <th scope="col">Destination</th>
                          <th scope="col">Date d&eacute;part</th>
                          <th scope="col">Date arriv&eacute;e</th>
                          <%-- <th scope="col">Prix business</th>
                          <th scope="col">Prix &eacute;conomique</th> --%>
                          <th scope="col">Date limite r&eacute;servation</th>
                          <th scope="col">prix</th>
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
                          <%-- <td><%=listVol[n].getPrix_economique() %></td>
                          <td><%=listVol[n].getPrix_business() %></td> --%>
                          <td><%=listVol[n].getDate_limite_reservation() %></td>
                          <td>
                            <!-- Bouton PROM -->
                            <button type="button" 
                                    class="btn btn-info btn-sm prom-btn"
                                    data-business="<%=listVol[n].getPrix_business()%>"
                                    data-eco="<%=listVol[n].getPrix_economique()%>"
                                    data-promb="<%=listVol[n].getHistoPrix().getPrix_business()%>"
                                    data-prome="<%=listVol[n].getHistoPrix().getPrix_economique()%>"
                                    data-dtd="<%=listVol[n].getHistoPrix().getDate_debut()%>"
                                    data-dtf="<%=listVol[n].getHistoPrix().getDate_fin()%>"
                                    
                                    >
                              Prom
                            </button>
                          </td>
                        </tr>
                      <%}%>
                    </tbody>
                  </table>

                  <div id="floatingTable" 
                   style="display:none; position:absolute; background:white; padding:20px; font-size:13px; z-index: 9999 !important; border-radius: 5px; box-shadow: 0 2px 8px rgba(0,0,0,0.1);">
                    <table class="table mb-0">
                      <thead>
                        <tr>
                          <th>Prix Business</th>
                          <th>Prix &eacute;conomique</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <td id="prixBusiness"></td>
                          <td id="prixEco"></td>
                        </tr>
                        <tr>
                          <td class="text-center" colspan="2">Promotion</td>
                        </tr>
                        <tr>
                          <td id="promb"></td>
                          <td id="prome"></td>
                        </tr>
                        <tr>
                          <td class="text-center" colspan="2">date limite</td>
                        </tr>
                        <tr>
                          <td id="dtd"></td>
                          <td id="dtf"></td>
                        </tr>
                      </tbody>
                    </table>
                  </div>

                  <!-- End Default Table Example -->
                </div>
              </div>
            </div><!-- End Top Selling -->

          </div>
        </div><!-- End Left side columns -->


      </div>
    </section>
<!-- Script pour gérer l'affichage flottant -->
  <script>
  document.addEventListener("DOMContentLoaded", () => {
    const promButtons = document.querySelectorAll(".prom-btn");
    const floatingTable = document.getElementById("floatingTable");
    const prixBusiness = document.getElementById("prixBusiness");
    const prixEco = document.getElementById("prixEco");
    const promB = document.getElementById("promb");
    const promE = document.getElementById("prome");
    const dtd = document.getElementById("dtd");
    const dtf = document.getElementById("dtf");

    function showFloatingTable(btn) {
      prixBusiness.textContent = btn.dataset.business;
      prixEco.textContent = btn.dataset.eco;
      promB.textContent = btn.dataset.promb;
      promE.textContent = btn.dataset.prome;
      dtd.textContent = btn.dataset.dtd;
      dtf.textContent = btn.dataset.dtf;

      const rect = btn.getBoundingClientRect();
      const scrollTop = window.scrollY || document.documentElement.scrollTop;
      const scrollLeft = window.scrollX || document.documentElement.scrollLeft;

      floatingTable.style.left = 650 + "px";
      floatingTable.style.top = 20 + "px";
      floatingTable.style.display = "block";
    }

    promButtons.forEach(btn => {
      btn.addEventListener("click", (e) => {
        e.stopPropagation();
        if (floatingTable.style.display === "block" && 
            prixBusiness.textContent === btn.dataset.business && 
            prixEco.textContent === btn.dataset.eco) {
          floatingTable.style.display = "none";
        } else {
          showFloatingTable(btn);
        }
      });
    });

    document.addEventListener("click", () => {
      floatingTable.style.display = "none";
    });
  });
  </script>




<%@ include file="footer.jsp" %>