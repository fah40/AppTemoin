<%@ include file="header.jsp" %>
<%@ page import="model.Block, model.Product, model.UsualShape" %>

<%@ page import="java.util.List,java.util.Arrays"%>

<%
    Block[] source =(Block[]) session.getAttribute("source");
    Block[] blocks =(Block[]) session.getAttribute("blocks");
    Product[] products =(Product[]) session.getAttribute("prodducts");
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
                  <form class="row g-3" action="ResultService" method="get">
                    <div class="col-md-2">
                      <label for="inputState" class="form-label">Block source</label>
                    </div>
                    <div class="col-md-8">
                      <select id="inputState" class="form-select" name="id">
                        <option selected>all</option>
                        <% for(int x=0;x< source.length;x++) {
                            if(source[x].getSource() == null){ %>
                          <option value="<%=source[x].getId()%>">block-<%=source[x].getId()%></option>
                        <%}}%>
                      </select>
                    </div><br>
                    <div class="col-md-2">
                      <button type="submit" class="btn btn-warning form-control">show</button>
                    </div>
                  </form><br>
                  <div class="alert alert-success">
                  <h2>Block</h2>
                    <table class="table">
                      <thead>
                        <tr>
                          <th scope="col">#</th>
                          <th scope="col">length</th>
                          <th scope="col">width</th>
                          <th scope="col">heigth</th>
                          <th scope="col">date</th>
                          <th scope="col">block-mere</th>
                          <th scope="col">block-source</th>
                          <th scope="col">initPrice</th>
                          <th scope="col">maxPrice</th>
                          <th scope="col">minPrice</th>
                          <th scope="col">productionCost</th>
                        </tr>
                      </thead>
                      <tbody>
                        <% for(int n=0;n< blocks.length;n++) {
                          %>
                          <tr>
                            <th scope="row"><a href="ResultService?idBlockProd=<%=blocks[n].getId() %>"> block-0<%=blocks[n].getId() %></a></th>
                            <td><%=blocks[n].getSize().getLength()%></td>
                            <td><%=blocks[n].getSize().getWidth() %></td>
                            <td><%=blocks[n].getSize().getHeight()%></td>
                            <td><%=blocks[n].getDateInsert()%></td>
                            <% if(blocks[n].getMere() != null){%>
                              <td>block-<%=blocks[n].getMere().getId()%></td>
                            <% } else{%>
                              <td> - </td>
                            <% } if(blocks[n].getSource() != null){%>
                              <td>block-<%=blocks[n].getSource().getId()%></td>
                            <% } else{%>
                              <td> - </td>
                            <% }%>
                            <td><%=String.format("%.2f", blocks[n].getInitPrice()) %></td>
                            <td><%=String.format("%.2f", blocks[n].getMaxPrice()) %></td>
                            <td><%=String.format("%.2f", blocks[n].getMinPrice()) %> </td>
                            <td><%=String.format("%.2f", blocks[n].getFirstPrice()) %> </td>  
                          </tr>
                        <%}%>
                      </tbody>
                    </table>
                  </div><br><br>

                  <div class="alert alert-warning">
                    <h2>Product</h2>
                    <table class="table">
                      <thead>
                        <tr>
                          <th scope="col">#</th>
                          <th scope="col">name</th>
                          <th scope="col">quantite</th>
                          <th scope="col">date</th>
                          <th scope="col">unit cost price</th>
                          <th scope="col">cost price</th>
                          <th scope="col">block-mere</th>
                          <th scope="col">block-source</th>
                        </tr>
                      </thead>
                      <tbody>
                        <% for(int n=0;n< products.length;n++) { %>
                          <tr>
                            <th scope="row"><%=products[n].getId() %></th>
                            <td><%=products[n].getUsualShape().getName() %></td>
                            <td><%=products[n].getQuantity() %></td>
                            <td><%=products[n].getDateInsert()%></td>
                            <td><%=String.format("%.2f", products[n].getPrixRevient()) %> </td>
                            <td><%=String.format("%.2f", products[n].getSumPrixRevient()) %> </td>
                            <td>block-<%=products[n].getBlock().getId()%></td>
                            <% if(products[n].getBlock().getSource() != null){%>
                              <td>block-<%=products[n].getBlock().getSource().getId()%></td>
                            <% } else{%>
                              <td>block-<%=products[n].getBlock().getId()%></td>
                            <% } %>
                          </tr>
                        <%}%>
                      </tbody>
                    </table>
                  </div><br><br>

                </div>
              </div>
            </div><!-- End Top Selling -->

          </div>
        </div><!-- End Left side columns -->

      </div>
    </section>

<%@ include file="footer.jsp" %>