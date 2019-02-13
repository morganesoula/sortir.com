
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="./layout/entete.jsp"%>
<%@ include file="./layout/navBar.jsp"%>



<!-- Page Content -->
<h2 class="m-5">Ajouter un lieu</h2>

<form method="post" class="form-horizontal offset-sm-1 col-sm-10"
	action="<%=request.getContextPath()%>/nouveauLieu">
	<div class="form-group">
		<label for="nom" class="mr-sm-2">Nom du lieu</label><input id="nom"
			type="text" name="nom" class="form-control mb-2 mr-sm-2"
			value="${lieu.nom}" />
	</div>
	<div class="form-group">
		<label for="prenom" class="mr-sm-2">Rue</label><input id="rue"
			type="text" name="rue" class="form-control mb-2 mr-sm-2"
			value="${lieu.rue}" />
	</div>
	<div class="form-group">
		<label for="pseudo" class="mr-sm-2">Latitude</label><input
			id="latitude" type="text" name="latitude"
			class="form-control mb-2 mr-sm-2" value="${lieu.latitude}" />
	</div>
	<div class="form-group">
		<label for="telephone" class="mr-sm-2">Longitude</label><input
			id="longitude" type="text" name="longitude"
			class="form-control mb-2 mr-sm-2" value="${lieu.longitude}" />
	</div>
	<div class="form-group">
		 <label for="idSite" class="mr-sm-2">Ville</label>
		 <a href="<%=request.getContextPath()%>/nouvelleVille"
			title="ajouter une ville" class="sortir"><span
			class="fa-stack fa-xs"> <i class="fas fa-circle fa-stack-2x"></i>
				<i class="fas fa-plus fa-stack-1x fa-inverse"></i>
		</span></a> 
		<select id="idVille" name="idVille" class="form-control mb-2 mr-sm-2">
			<c:forEach var="ville" items="${listeVilles}">
				<option value="${ville.idVille}">${ville.nom}</option>
			</c:forEach>
		</select>
	</div>

	<input type="submit" value="Valider" class="btn btn-success mb-2" /> <a
		href="<%=request.getContextPath()%>/nouvelleSortie"
		class="btn btn-info mb-2">Annuler</a>

</form>

<!-- / Page Content -->
<%@ include file="./layout/piedDePage.html"%>
