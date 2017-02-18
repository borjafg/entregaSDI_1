<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${requestScope.informacion_usuario != null}">
	<div class="container-fluid">
		<div class="alert alert-info">
			<a href="#" class="close" data-dismiss="alert">&times;</a> <i>${requestScope.informacion_usuario}</i>
		</div>
	</div>
</c:if>