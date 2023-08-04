<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp" />
<c:if test="${username.present}">

    <div class="listar-div-btn">
       <a class="btn btn-primary" href="${pageContext.request.contextPath}/producto-form">Crear[+]</a>

       <a class="btn btn-success" href="${pageContext.request.contextPath}/productos.xls">Download
       <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-file-earmark-arrow-down" viewBox="0 0 16 16">
        <path d="M8.5 6.5a.5.5 0 0 0-1 0v3.793L6.354 9.146a.5.5 0 1 0-.708.708l2 2a.5.5 0 0 0 .708 0l2-2a.5.5 0 0 0-.708-.708L8.5 10.293V6.5z"/>
            <path d="M14 14V4.5L9.5 0H4a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2zM9.5 3A1.5 1.5 0 0 0 11 4.5h2V14a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h5.5v2z"/>
        </svg></a>
    </div>
</c:if>
<table class="table table-dark table-hover">
  <tr>
    <th>id</th>
    <th>nombre</th>
    <th>tipo</th>
    <c:if test="${username.present}">
        <th>precio</th>
        <th>carro</th>
        <th>actualizar</th>
        <th>eliminar</th>
    </c:if>
  </tr>
  <c:forEach items="${productos}" var="p">
  <tr>
    <td>${p.id}</td>
    <td>${p.nombre}</td>
    <td>${p.categoria.nombre}</td>
     <c:if test="${username.present}">
        <td>${p.precio}</td>
        <td><a class="btn btn-primary" href="${pageContext.request.contextPath}/carro/agregar?id=${p.id}">
        <svg xmlns="http://www.w3.org/2000/svg" width="33" height="26" fill="currentColor" class="bi bi-cart3" viewBox="0 0 16 16">
          <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
        </svg>
        </a></td>
        <td><a class="btn btn-primary" href="${pageContext.request.contextPath}/producto-form?id=${p.id}">actualizar</a></td>
        <td>
            <a class="btn btn-danger" onclick="return confirm('esta seguro que desea eliminar?');" href="${pageContext.request.contextPath}/producto/eliminar?id=${p.id}">
                <svg xmlns="http://www.w3.org/2000/svg" width="33" height="26" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                    <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z"/>
                    <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z"/>
                </svg>
            </a>
        </td>
    </c:if>
  </tr>
  </c:forEach>
</table>
<jsp:include page="layout/footer.jsp" />