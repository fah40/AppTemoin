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
            </div><!-- End Top Selling -->

          </div>
        </div><!-- End Left side columns -->

    
      </div>
    </section>


<%@ include file="footer.jsp" %>