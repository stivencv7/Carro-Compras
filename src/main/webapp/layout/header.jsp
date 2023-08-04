<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link href="layout/style.css"  rel="stylesheet" type="text/css">


</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Navbar</a>

    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/productos">Catalogo</a>
        </li>
        <li class="nav-item">
          <a style="display:flex;gap:1px;border-radius;align-items:center;" class="nav-link" href="${pageContext.request.contextPath}/carro-ver">Carro<div class=${carro.items.size()>0?"item-carro":"item-carro-oculto"}>${carro.items.size()}</div></a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="${pageContext.request.contextPath}/login" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            ${not empty sessionScope.username?sessionScope.username:"cuenta"}

          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <a class="dropdown-item"
              href="${pageContext.request.contextPath}/${not empty username? "logout": "login"}">
                ${not empty sessionScope.username? "Logout": "Login"}
              </a>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>
<div class="container-padre">
<div class="div-container">