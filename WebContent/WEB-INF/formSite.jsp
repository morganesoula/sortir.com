
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="./layout/entete.jsp"%>
<%@ include file="./layout/navBar.jsp"%>



<!-- Page Content -->
<h2 class="m-5">${title} un site</h2>

<form method="post" class="form-horizontal offset-sm-1 col-sm-10"
	action="<%=request.getContextPath()%>/${path}">
	
	<div class="form-group">
		<label for="nom" class="mr-sm-2">Nom du site</label><input id="nom"
			type="text" name="nom" class="form-control mb-2 mr-sm-2"
			value="${site.nom}" />
	</div>

	<div class="form-group">
		<input for="idSite" type="hidden" name="idSite"
			class="form-control mb-2 mr-sm-2" value="${site.idSite}" />
	</div>

	<input type="submit" value="Valider" class="btn btn-success mb-2" /> <a
		href="<%=request.getContextPath()%>/sites" class="btn btn-info mb-2">Annuler</a>

</form>

<!-- / Page Content -->
<%@ include file="./layout/piedDePage.html"%>
