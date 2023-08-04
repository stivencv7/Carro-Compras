
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp" />
<c:choose>
  <c:when test="${carro.items.size()<=0}">
   <p>Carro de compras vasio</p>
  </c:when>
  <c:otherwise>

  <form class="form-carro" name="formcarro" action="${pageContext.request.contextPath}/carro/actualizar" method="post">
    <table class="table table-dark table-hover">
      <tr>
        <th>id</th>
        <th>nombre</th>
        <th>precio</th>
        <th>cantidad</th>
        <th>importe</th>
        <th>eliminar</th>
      </tr>
      <c:forEach items="${carro.items}" var="item">
        <tr>
          <td>${item.producto.id}</td>
          <td>${item.producto.nombre}</td>
          <td>${item.producto.precio}</td>
          <td><input size="4" type="text" name="cant_${item.producto.id}" value="${item.cantidad}"></td>
          <td>${item.importe}</td>
          <td><input class="form-check-input" type="checkbox" name="deleItems" value="${item.producto.id}"></td>
        </tr>
      </c:forEach>
        <tr>
          <td style="text-align:right;" colspan="5">Total</td>
          <td>${carro.total}</td>
        </tr>
    </table>
    <div style="width:50%;">
     <a class="btn btn-primary" href="javascript:document.formcarro.submit();">Actualizar</a>
    </div>
    </form>
  </c:otherwise>
</c:choose>
<jsp:include page="layout/footer.jsp" />

