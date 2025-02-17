<%@ include file="header.jsp" %>
<%@page import="model.Block"%>
<%
  Block[] listBlock=(Block[]) session.getAttribute("bocks");
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
                  <h5 class="card-title" >liste</h5>
                  <p>
                    <a class="btn btn-warning" href="index.jsp?id=all">all</a>
                    <a class="btn btn-warning" href="index.jsp?id=orig">Origin</a>
                  </p>
                  <p>en cm</p>
                  <!-- Default Table -->
                  <table class="table">
                    <thead>
                      <tr>
                        <th scope="col">#</th>
                        <th scope="col">length</th>
                        <th scope="col">width</th>
                        <th scope="col">heigth</th>
                        <th scope="col">price</th>
                        <th scope="col">Date</th>
                        <th scope="col">block mere</th>
                        <th scope="col">block source</th>
                      </tr>
                    </thead>
                    <tbody>
                      <% for(int n=0;n< listBlock.length;n++) {%>
                        <tr>
                          <th scope="row"><a href="index.jsp?id=<%=listBlock[n].getId() %>"> block-0<%=listBlock[n].getId() %></a></th>
                          <td><%=listBlock[n].getSize().getLength()%></td>
                          <td><%=listBlock[n].getSize().getWidth() %></td>
                          <td><%=listBlock[n].getSize().getHeight()%></td>
                          <td><%= String.format("%.2f", listBlock[n].getPrice()) %> </td>
                          <td><%=listBlock[n].getDateInsert()%></td>
                        <% if(listBlock[n].getMere() != null){%>
                          <td>block-<%=listBlock[n].getMere().getId()%></td>
                        <% } else{%>
                          <td> - </td>
                        <% } if(listBlock[n].getSource() != null){%>
                          <td>block-<%=listBlock[n].getSource().getId()%></td>
                        <% } else{%>
                          <td> - </td>
                          <td><a class="btn btn-secondary" href="index.jsp?u=<%=listBlock[n].getId() %>">update</a></td>
                        <% }%>
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
                  <form class="row g-3" action="index.jsp" method="post">
                  <% if(request.getAttribute("update") == null){%>
                  <h5 class="card-title">Insertion block</h5>
                    <div class="col-md-12">
                      <label for="inputPassword5" class="form-label">length</label>
                      <input type="number" class="form-control" id="inputPassword5" name="length">
                    </div>

                    <div class="col-md-12">
                      <label for="inputPassword5" class="form-label">width</label>
                      <input type="number" class="form-control" id="inputPassword5" name="width">
                    </div>

                    <div class="col-md-12">
                      <label for="inputPassword5" class="form-label">heigth</label>
                      <input type="number" class="form-control" id="inputPassword5" name="heigth">
                    </div>

                    <div class="col-md-8">
                      <label for="inputPassword5" class="form-label">price</label>
                      <input type="number" class="form-control" id="inputPassword5" name="price">
                      <input style="visibility: hidden;" type="" value="create" name="mode">
                    </div>
                    <% } else{
                      Block update= (Block)request.getAttribute("update");
                      %>
                      <div class="col-md-12"><br>
                        <label for="inputPrice" class="form-label">Update price</label>
                        <input type="" class="form-control" value="<%= update.getId()%>" name="idu" readonly><br>
                        <input type="number" class="form-control" id="inputPrice" value="<%= update.getPrice() %>" name="price">
                        <input style="visibility: hidden;" type="" value="update" name="mode">
                      </div>
                    <% }%>

                    <div class="text-center">
                      <button type="submit" class="btn btn-warning">save</button>
                      <button type="reset" class="btn btn-secondary">Reset</button>
                    </div>
                  </form><!-- End Multi Columns Form -->

                </div>
              </div>
        </div><!-- End Right side columns -->

      </div>
    </section>


<%@ include file="footer.jsp" %>