<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Aeroport</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="<%= request.getContextPath() %>/assets/fa/css/all.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/assets/fa/css/all.min.css" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="<%= request.getContextPath() %>/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/assets/vendor/quill/quill.snow.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/assets/vendor/quill/quill.bubble.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/assets/vendor/simple-datatables/style.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">

</head>

<body>

  <!-- ======= Header ======= -->

  <!-- ======= Sidebar ======= -->
   <aside id="sidebar" class="sidebar">
    <br>
    <ul class="sidebar-nav" id="sidebar-nav">

      <li class="nav-item">
        <a class="nav-link " href="listVol">
          <i class="fa fa-plane-departure"></i>
          <span>Vol</span>
        </a>
      </li><!-- End Block Nav -->

      <li class="nav-item">
        <a class="nav-link " href="reservation">
          <i class="fa fa-clipboard-check"></i>
          <span>Reservation</span>
        </a>
      </li><!-- End Transformation Nav -->

      <li class="nav-item">
        <a class="nav-link " href="reservation">
          <i class="fa fa-ticket-alt"></i>
          <span>Billet</span>
        </a>
      </li><!-- End Transformation Nav -->
    </ul>

    <a style="position:fixed; bottom:20px;" class="btn btn-secondary" href="deconnection">
          <i class="fa fa-door"></i>
          <span>deconnecter</span>
        </a>
  </aside><!-- End Sidebar-->

  <main id="main" class="main">
