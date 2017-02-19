<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${requestScope.exito_usuario != null}">
	<div class="container-fluid">
		<div class="alert alert-success">
			<a href="#" class="close" data-dismiss="alert">&times;</a> <i>${requestScope.exito_usuario}</i>
		</div>
	</div>
</c:if>