
<%@ include file="./layout/entete.jsp"%>
<%@ include file="./layout/navBar.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!-- Page Content -->
<h2 class="m-5 text-uppercase">
	Les Sorties <small>- profitez de notre communauté d'étudiants
		ENJOY! <a href="<%=request.getContextPath()%>/nouvelleSortie"
		title="ajouter une sortie" class="sortir"><span
			class="fa-stack fa-xs"> <i class="fas fa-circle fa-stack-2x"></i>
				<i class="fas fa-plus fa-stack-1x fa-inverse"></i>
		</span></a>
	</small>
</h2>

<div class="row p-2 m-0">
	<c:choose>
		<c:when test="${listeSorties.size()>0}">
			<c:forEach var="sortie" items="${listeSorties}">
				<div class="col-lg-3 col-md-6 col-sm-12 mb-3">
					<div class="card">
						<img class="card-img-top"
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
									pattern="dd-MM-yyyy" /> <i class="fas fa-clock"></i> 
								<fmt:formatDate value="${sortie.dateHeureDebut}"
									pattern="HH:mm" />
							</h6>
							<h6>
							<i class="fas fa-calendar-times"></i>
								<fmt:formatDate value="${sortie.dateHeureFin}"
									pattern="dd-MM-yyyy" /></h6>

							<h6 class="card-title">
								<i class="fas fa-hourglass-end"></i> ${sortie.duree} minutes
							</h6>

							<h6 class="card-title">
								<i class="fas fa-users"></i>
								
							? / ${sortie.nbParticipantMax}
							</h6>
							<h6 class="card-title">
								<a
									href="${pageContext.request.contextPath}/organisateur?pseudo=${sortie.organisateur.pseudo}" class="text-primary"><i
									class="fas fa-user-cog"></i> ${sortie.organisateur.pseudo}</a>
							</h6>
							<p class="card-text">${sortie.description}</p>
								<a href="${pageContext.request.contextPath}/sortie?idSortie=${sortie.idSortie}"
									class="btn btn-outline-dark" title="Voir la sortie"><i
									class="fas fa-eye"></i></a>
							<c:if
								test="${sortie.idEtat.idEtat != 3 && sortie.idEtat.idEtat != 1}">
								<a href="#"
									class="btn btn-dark" title="S'inscrire à la sortie"><i
									class="fas fa-plus"></i></a>
							</c:if>
							<c:if
								test="${participantEnCours.pseudo == sortie.organisateur.pseudo || participantEnCours.administrateur}">
								<a
									href="${pageContext.request.contextPath}/editerSortie?idSortie=${sortie.idSortie}"
									class="btn btn-sortir" title="modifier la sortie"><i
									class="fas fa-edit"></i></a>
								<a
									href="${pageContext.request.contextPath}/annulerSortie?idSortie=${sortie.idSortie}"
									class="btn btn-danger" title="Annuler la sortie"><i
									class="fas fa-eraser"></i></a>
							</c:if>
						</div>
					</div>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<p>Pas de liste actuellement.</p>
		</c:otherwise>
	</c:choose>
</div>

<!-- / Page Content -->
<%@ include file="./layout/piedDePage.html"%>

