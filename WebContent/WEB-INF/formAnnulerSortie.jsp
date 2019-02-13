
<%@ include file="./layout/entete.jsp"%>
<%@ include file="./layout/navBar.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!-- Page Content -->

<h2 class="m-5 text-uppercase">
	Sortie <small> - Annuler</small>
</h2>

	<form method="post" class="form-horizontal offset-sm-1 col-sm-10"
		action="<%=request.getContextPath()%>/annulerSortie">
		<div class="form-group">
			<label for="nom" class="mr-sm-2">Nom de la sortie:
				${sortie.nom}</label>
		</div>
		<div class="form-group">
			<label for="date" class="mr-sm-2">Date Sortie: <fmt:formatDate
					value="${sortie.dateHeureDebut}" pattern="dd-MM-yyyy à HH:mm" /></label>
		</div>
		<div class="form-group">
			<label for="site" class="mr-sm-2">Site de rattachement:
				${site.nom}</label>
		</div>
		<div class="form-group">
			<label for="site" class="mr-sm-2">Lieu: ${sortie.idLieu.nom},
				${sortie.idLieu.rue} - ${ville.codePostal} - ${ville.nom} </label>
		</div>
		<div class="form-group">
			<label for="site" class="mr-sm-2">Description:
				${sortie.description}</label>
		</div>

		<div class="form-group">
			<label for="description">Motif</label>
			<textarea class="form-control" id="description" name="description"
				rows="3"></textarea>
		</div>
		<div class="form-group">
			<input id="idSortie" type="hidden" name="idSortie" class="form-control mb-2 mr-sm-2"
				value="${sortie.idSortie}" />
		</div>
		<input type="submit" value="Valider" class="btn btn-success mb-2" />
		<a href="<%=request.getContextPath()%>/sorties"
			class="btn btn-info mb-2">Annuler</a>

	</form>


<!-- / Page Content -->
<%@ include file="./layout/piedDePage.html"%>

