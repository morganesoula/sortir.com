
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="./layout/entete.jsp"%>
<%@ include file="./layout/navBar.jsp"%>


<!-- Page Content -->
<h2 class="m-5">${title} une ville</h2>

<form method="post" class="form-horizontal offset-sm-1 col-sm-10"
	action="<%=request.getContextPath()%>/${path}">
	<div class="form-group">
		<label for="nom" class="mr-sm-2">Nom</label><input id="nom"
			type="text" name="nom" class="form-control mb-2 mr-sm-2"
			value="${ville.nom}" />
	</div>
	<div class="form-group">
		<label for="prenom" class="mr-sm-2">Code Postal</label><input
			id="codePostal" type="text" name="codePostal"
			class="form-control mb-2 mr-sm-2" value="${ville.codePostal}" />
	</div>

	<div class="form-group">
		<input for="idVille" type="hidden" name="idVille"
			class="form-contrl mb-2 mr-sm-2" value="${ville.idVille}" />
	</div>


	<input type="submit" value="Valider" class="btn btn-success mb-2" /> <a
		href="<%=request.getContextPath()%>/nouveauLieu"
		class="btn btn-info mb-2">Annuler</a>

</form>

<!-- / Page Content -->
<%@ include file="./layout/piedDePage.html"%>
