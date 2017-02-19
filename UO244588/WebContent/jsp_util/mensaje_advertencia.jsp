<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${requestScope.advertencia_usuario != null}">
	<div class="container-fluid">
		<div class="alert alert-warning">
			<a href="#" class="close" data-dismiss="alert">&times;</a> <i>${requestScope.advertencia_usuario}</i>
		</div>
	</div>
</c:if>