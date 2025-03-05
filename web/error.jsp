<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Pages / Not Found 404 - NiceAdmin Bootstrap Template</title>
  <meta content="" name="description">
  <meta content="" name="keywords">


  <!-- Vendor CSS Files -->
  <link href="<%= request.getContextPath() %>/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/assets/vendor/quill/quill.snow.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/assets/vendor/quill/quill.bubble.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/assets/vendor/simple-datatables/style.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="<%= request.getContextPath() %>/assets/css/style.css" rel="stylesheet">

  <!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: May 30 2023 with Bootstrap v5.3.0
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>

  <main>
    <div class="container">

      <section class="section error-404 min-vh-100 d-flex flex-column align-items-center justify-content-center">
        <h5>exception</h5><br>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
          <%= (String) request.getAttribute("error") %>
          <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <a class="btn" href="reservation">Retour</a>
        <img src="assets/img/eror.png" class="img-fluid py-5" alt="Page Not Found">
        <div class="credits">
        </div>
      </section>

    </div>
  </main><!-- End #main -->

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Vendor JS Files -->
  <script src="<%= request.getContextPath() %>/assets/vendor/apexcharts/apexcharts.min.js"></script>
  <script src="<%= request.getContextPath() %>/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="<%= request.getContextPath() %>/assets/vendor/chart.js/chart.umd.js"></script>
  <script src="<%= request.getContextPath() %>/assets/vendor/echarts/echarts.min.js"></script>
  <script src="<%= request.getContextPath() %>/assets/vendor/quill/quill.min.js"></script>
  <script src="<%= request.getContextPath() %>/assets/vendor/simple-datatables/simple-datatables.js"></script>
  <script src="<%= request.getContextPath() %>/assets/vendor/tinymce/tinymce.min.js"></script>
  <script src="<%= request.getContextPath() %>/assets/vendor/php-email-form/validate.js"></script>

  <!-- Template Main JS File -->
  <script src="<%= request.getContextPath() %>/assets/js/main.js"></script>

</body>

</html>