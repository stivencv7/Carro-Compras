<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp" />
<form  action="${pageContext.request.contextPath}/login" method="post">

    <div class="row mb-2">
        <label class="col-sm-1 col-form-label" for="username">nombre</label>
        <div>
            <input class="form-control" type="text" name="username" id="username">
        </div>
    </div>

    <div class="row mb-2">
        <label class="col-sm-1 col-form-label" for="password">password</label>
        <div >
            <input class="form-control" type="text" name="password" id="password">
        </div>
    </div>
    <div><input class="btn btn-primary" type="submit" value="Login"></div>
</form>

<jsp:include page="layout/footer.jsp" />