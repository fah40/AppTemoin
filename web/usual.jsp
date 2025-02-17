<%@ include file="header.jsp" %>
<%@ page import="model.Block, model.Product, model.UsualShape" %>

<%@ page import="java.util.List,java.util.Arrays"%>

<%
  Block[] allBlock=(Block[]) session.getAttribute("bocks");
  UsualShape[] allUsual=(UsualShape[]) request.getAttribute("listUsual");
  Product[] list= (Product[]) session.getAttribute("fabrication");
%>
    <section class="section dashboard">
      <div class="row">

        <!-- Left side columns -->
        <div class="col-lg-8">
          <div class="row">

            <!-- Top Selling -->
            <div class="col-12">

              <div class="card">
                <div class="card-body">
                  <h5 class="card-title"></h5>

                  <!-- Default Table -->
                  <table class="table">
                    <thead>
                      <tr>
                        <th scope="col">#</th>
                        <th scope="col">idBlock</th>
                        <th scope="col">modele</th>
                        <th scope="col">quantity</th>
                      </tr>
                    </thead>
                    <tbody>
                      <%for(int n=0;n< list.length;n++) {%>
                        <tr>
                          <th scope="row"><%=list[n].getId() %></th>
                          <td>block-0<%=list[n].getBlock().getId()%></td>
                          <td><%=list[n].getUsualShape().getName() %></td>
                          <td><%=list[n].getQuantity()%></td>
                        </tr>
                      <%}%>


                    </tbody>
                  </table>
                  <!-- End Default Table Example -->
                <a class="btn btn-success" href="ValidationService">validate</a>
                </div>
              </div>
            </div><!-- End Top Selling -->

          </div>
        </div><!-- End Left side columns -->

        <!-- Right side columns -->
        <div class="col-lg-4">
           <div class="card">
                <div class="card-body">
                  <h5 class="card-title">transformation</h5>

                  <!-- Multi Columns Form -->
                  <form class="row g-3" action="ProductService" method="post">
                    <div class="col-md-12">
                      <label for="inputState" class="form-label">Block</label>
                      <select id="inputState" class="form-select" name="idBlock">
                        <option selected>Choose...</option>
                        <%for(int x=0;x< allBlock.length;x++) {%>
                        <option value="<%=allBlock[x].getId()%>">Block-<%=allBlock[x].getId()%></option>
                        <%}%>
                      </select>
                    </div><br>

                    <div class="col-md-12">
                      <label for="inputState" class="form-label">Usual Shape</label>
                      <select id="inputState" class="form-select" name="idShape">
                        <option selected>Choose...</option>
                        <%for(int x=0;x< allUsual.length;x++) {%>
                        <option value="<%=allUsual[x].getId()%>"><%=allUsual[x].getName()%></option>
                        <%}%>
                      </select>
                    </div><br>

                    <div class="col-md-6">
                      <label for="inputPassword5" class="form-label">quantite</label>
                      <input type="number" class="form-control" id="inputPassword5" name="quantity">
                      <input style="visibility: hidden;" type="" value="" name="mode">
                    </div>

                    <div class="text-center">
                      <button type="submit" class="btn btn-success">save</button>
                      <button type="reset" class="btn btn-secondary">Reset</button>
                    </div>
                  </form><!-- End Multi Columns Form -->

                </div>
              </div>
        </div><!-- End Right side columns -->

      </div>
    </section>

<%@ include file="footer.jsp" %>