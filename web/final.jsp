<%@ include file="header.jsp" %>
<%@ page import="model.Block, model.Product, model.UsualShape" %>

<%@ page import="java.util.List,java.util.Arrays"%>

<%
    Block init =(Block) session.getAttribute("initial");

    double initPrice = (double) session.getAttribute("initPrice");
    double maxPrice = (double) session.getAttribute("maxPrice");
    double minPrice = (double) session.getAttribute("minPrice");
    double productionCost = (double) session.getAttribute("productionCost");
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
                  <h2 class="card-title">Resultat </h2>

                  <!-- Default Table -->
                  <table class="table">
                    <thead>
                      <tr>
                        <th scope="col">#</th>
                        <th scope="col">initPrice</th>
                        <th scope="col">maxPrice</th>
                        <th scope="col">minPrice</th>
                        <th scope="col">productionCost</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <th scope="row">1</th>
                        <td><%=initPrice%></td>
                        <td><%=maxPrice%></td>
                        <td><%=minPrice%> </td>
                        <td><%=productionCost%> </td>
                      </tr>
                    </tbody>
                  </table>

                </div>
              </div>
            </div><!-- End Top Selling -->

          </div>
        </div><!-- End Left side columns -->

      </div>
    </section>

<%@ include file="footer.jsp" %>