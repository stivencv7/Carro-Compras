<%@page contentType="text/html" pageEncoding="UTF-8" import="java.time.format.DateTimeFormatter"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp" />
<form   action="${pageContext.request.contextPath}/producto-form" method="post">

<div class="row mb-2">
  <label class="col-sm-2 col-form-label" for="nombre">Nombre</label>
  <div>
    <input class="form-control" type="text" name="nombre" id="nombre" value="${producto.nombre!=null?producto.nombre:""}">
    <c:if test="${!errores.isEmpty() && errores.containsKey('nombre')}">
        <p style="color:red;">${errores.get("nombre")}</p>
    </c:if>
  </div>

</div>


<div class="row mb-2">
  <label class="col-sm-2 col-form-label" for="precio">Precio</label>
  <div>
    <input class="form-control" type="text" name="precio" id="precio" value="${producto.precio>0?producto.precio:""}">
    <c:if test="${!errores.isEmpty() && errores.containsKey('precio')}">
        <p style="color:red;">${errores.get("precio")}</p>
    </c:if>
  </div>
</div>

<div class="row mb-2">
  <label  class="col-sm-2 col-form-label" for="sku">Sku</label>
  <div>
    <input class="form-control" type="text" name="sku" id="sku" value="${producto.sku!=null?producto.sku:""}">
    <c:if test="${!errores.isEmpty() && errores.containsKey('sku')}">
      <p style="color:red;">${errores.get("sku")}</p>
    </c:if>
  </div>
</div>

<div class="row mb-2">
  <label class="col-sm-5 col-form-label" for="fecha">Fecha Registro</label>
  <div>
    <input class="form-control" type="date" name="fecha" id="fecha" value="${producto.fechaRegistro!=null?producto.fechaRegistro.format(DateTimeFormatter.ofPattern('yyyy-MM-dd')):""}">
    <c:if test="${!errores.isEmpty() && errores.containsKey('fecha')}">
        <p style="color:red;">${errores.get("fecha")}</p>
    </c:if>
  </div>

</div>

<div class="row mb-2">
    <label class="col-sm-2 col-form-label" for="categoria">Categoría</label>
  <div>
    <select class="form-select" name="categoria" id="categoria">
      <option >--seleccione una opcion--</option>
      <c:forEach items="${categorias}" var="c">
      <option value="${c.id}" ${producto.categoria.id.equals(c.id)?"selected":""}>${c.nombre}</option>
      </c:forEach>
    </select>
    <c:if test="${!errores.isEmpty() && errores.containsKey('categoria')}">
        <p style="color:red;">${errores.get("categoria")}</p>
    </c:if>
  </div>

</div>

<div class="row mb-2">
      <div>
         <input class="btn btn-primary" type="submit" value=${producto.id>0?"actualizar":"crear"}>
      </div>
</div>
<input type="hidden" name="id" value="${producto.id}">

</form>
<jsp:include page="layout/footer.jsp" />
