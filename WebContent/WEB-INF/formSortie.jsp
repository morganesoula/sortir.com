
<%@ include file="./layout/entete.jsp"%>
<%@ include file="./layout/navBar.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- Page Content -->

<h2 class="m-5 text-uppercase">
	Sortie <small> - ${title}</small>
</h2>

	<form method="post" class="form-horizontal offset-sm-1 col-sm-10"
		action="<%=request.getContextPath()%>/${path}">
		<c:if test="${sortie.urlPhoto != null }">
			<div class="col-md-4 p-0 mb-4">
				<img src="<%=request.getContextPath()%>/images/${sortie.urlPhoto}"
					class="img-fluid img-thumbnail">
			</div>
		</c:if>

		<div class="form-group">
			<label for="urlPhoto" class="mr-sm-2">Image de la sortie</label><input
				id="urlPhoto" type="text" name="urlPhoto"
				class="form-control mb-2 mr-sm-2" value="${sortie.urlPhoto}" />
		</div>
		<div class="form-group">
			<label for="nom" class="mr-sm-2">Nom de la sortie</label><input
				id="nom" type="text" name="nom" class="form-control mb-2 mr-sm-2"
				value="${sortie.nom}" />
		</div>
		<div class="form-group">
			<label for="date" class="mr-sm-2">Date Sortie</label><input
				id="date" type="date" name="date" class="form-control mb-2 mr-sm-2" value="<fmt:formatDate value='${sortie.dateHeureDebut}' pattern='yyyy-MM-dd'/>"/>
		</div>
		<div class="form-group">
			<label for="heure" class="mr-sm-2">Heure Sortie</label><input
				id="heure" type="time" name="heure"
				class="form-control mb-2 mr-sm-2" value="<fmt:formatDate value='${sortie.dateHeureDebut}' pattern='HH:mm'/>"/>
		</div>
		<div class="form-group">
			<label for="dateHeureFin" class="mr-sm-2">Date limite
				d'inscription</label><input id="dateHeureFin" type="date"
				name="dateHeureFin" class="form-control mb-2 mr-sm-2" value="<fmt:formatDate value='${sortie.dateHeureFin}' pattern='yyyy-MM-dd'/>" />
		</div>
		<div class="form-group">
			<label for="duree" class="mr-sm-2">Durée (en minutes)</label><input
				id="duree" type="number" name="duree"
				class="form-control mb-2 mr-sm-2" value="${sortie.duree}" />
		</div>

		<div class="form-group">
			<label for="nbParticipantMax" class="mr-sm-2">Nombre de place</label><input
				id="nbParticipantMax" type="number" name="nbParticipantMax"
				class="form-control mb-2 mr-sm-2" value="${sortie.nbParticipantMax}" />
		</div>
		<div class="form-group">
			<label for="description">Description</label>
			<textarea class="form-control" id="description" name="description"
				rows="3">${sortie.description}</textarea>
		</div>

		<div class="form-group">
			<label for="organisateur" class="mr-sm-2">Site de
				rattachement: ${site.nom} </label>
		</div>

		<div class="form-group">
			<label for="lieu" class="mr-sm-2">Lieu</label> <a
				href="<%=request.getContextPath()%>/nouveauLieu"
				title="ajouter un lieu" class="sortir"><span
				class="fa-stack fa-xs"> <i class="fas fa-circle fa-stack-2x"></i>
					<i class="fas fa-plus fa-stack-1x fa-inverse"></i>
			</span></a> <select id="idSite" name="lieu" class="form-control mb-2 mr-sm-2">
				<c:forEach var="lieu" items="${listeLieux}">
					<option value="${lieu.idLieu}"
						${lieu.idLieu == sortie.idLieu.idLieu ? 'selected="selected"' : ''}>
						${lieu.nom}</option>
				</c:forEach>
			</select>
		</div>

		<div class="form-group">
			<label for="etat" class="mr-sm-2">Etat</label> <select id="etat"
				name="etat" class="form-control mb-2 mr-sm-2">
				<c:forEach var="etat" items="${listeEtats}">
					<option value="${etat.idEtat}"
						${etat.idEtat == etat.idEtat ? 'selected="selected"' : ''}>
						${etat.libelle}</option>
				</c:forEach>
			</select>
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

