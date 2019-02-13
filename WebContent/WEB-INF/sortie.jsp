
<%@ include file="./layout/entete.jsp"%>
<%@ include file="./layout/navBar.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!-- Page Content -->
<h2 class="m-5">Sortie "${sortie.nom } - ${sortie.nbParticipantMax}
	participants max"</h2>

<div class="row p-3 m-0 ">
	<div class="card p-0 offset-md-1 col-md-3 text-center">
		<img class="card-img-top thumbail"
			src="${pageContext.request.contextPath}/images/${sortie.urlPhoto}"
			alt="Card image cap">
		<div class="card-body text-center">
			<h5 class="card-title">${sortie.nom}</h5>
			<c:if test="${sortie.idEtat.libelle == 'Créée'}">
				<h6 class="card-title">
					<span class="badge badge-dark pt-2 pb-2 pl-5 pr-5">${sortie.idEtat.libelle}</span>
				</h6>
			</c:if>
			<c:if test="${sortie.idEtat.libelle == 'Ouverte'}">
				<h6 class="card-title">
					<span class="badge badge-success pt-2 pb-2 pl-5 pr-5">${sortie.idEtat.libelle}</span>
				</h6>
			</c:if>
			<c:if test="${sortie.idEtat.libelle == 'Annulée'}">
				<h6 class="card-title">
					<span class="badge badge-danger pt-2 pb-2 pl-5 pr-5">${sortie.idEtat.libelle}</span>
				</h6>
			</c:if>
			<h6 class="card-title">
				<i class="fas fa-map-marker-alt"></i> ${sortie.idLieu.nom}
			</h6>
			<h6 class="card-title">
				<i class="fas fa-calendar-alt"></i>
				<fmt:formatDate value="${sortie.dateHeureDebut}"
					pattern="dd-MM-yyyy" />
				<i class="fas fa-clock"></i>
				<fmt:formatDate value="${sortie.dateHeureDebut}" pattern="HH:mm" />
			</h6>
			<h6>
				<i class="fas fa-calendar-times"></i>
				<fmt:formatDate value="${sortie.dateHeureFin}" pattern="dd-MM-yyyy" />
			</h6>

			<h6 class="card-title">
				<i class="fas fa-hourglass-end"></i> ${sortie.duree} minutes
			</h6>

			<h6 class="card-title">
				<i class="fas fa-users"></i> ? /${sortie.nbParticipantMax}
			</h6>
			<h6 class="card-title">
				<i class="fas fa-user-cog"></i> ${sortie.organisateur.pseudo}
			</h6>

			<c:if
				test="${sortie.idEtat.idEtat != 3 && sortie.idEtat.idEtat != 1}">
				<a href="#" class="btn btn-dark" title="S'inscrire à la sortie"><i
					class="fas fa-plus"></i> S'inscrire</a>
			</c:if>

		</div>
	</div>
	<div class="p-0 offset-md-1 col-md-6 offset-md-1">
		<c:choose>
			<c:when test="${listeInscriptions.size()>0}">
			<h2 class="mt-2"><i class="fas fa-users"></i> Liste des participants inscrits</h2>

				<table class="mt-2 table table-striped table-responsive table-hover"
					style="display: table;">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Pseudo</th>
							<th scope="col">Nom</th>
						</tr>
					</thead>
					<tbody>
					
						<c:forEach var="inscription" items="${listeInscriptions}">
							<tr>
								<th scope="row">${inscription.idParticipant.pseudo}</th>
								<td>${inscription.idParticipant.nom} -
									${inscription.idParticipant.prenom}</td>

							</tr>
						</c:forEach>
					</tbody>
				</table>

			</c:when>
			<c:otherwise>
				<p>Pas encore de participants à la sortie, soyez le premier !</p>
			</c:otherwise>
		</c:choose>
	</div>
</div>


<!-- / Page Content -->
<%@ include file="./layout/piedDePage.html"%>

