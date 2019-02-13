
<%@ include file="./layout/entete.jsp"%>
<%@ include file="./layout/navBar.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- Page Content -->
<h2 class="m-5">Profil de ${profil.pseudo }</h2>
<div class="card p-0 offset-sm-4 col-sm-4 text-center">
	<img class="card-img-top thumbail" src="./images/avatar.png"
		alt="Card image cap" style="width: 30%; margin: auto;">
	<div class="card-body">
		<h5 class="card-title text-uppercase">${profil.pseudo}</h5>
		<h6 class="card-subtitle mb-2 text-muted">${profil.nom} -
			${profil.prenom}</h6>
		<p class="p-2 m-0  text-primary">
			<i class="fas fa-map-marker"></i> ${site.nom}
		</p>
		<p class="p-2 m-0 text-primary">
			<i class="fas fa-phone"></i> ${profil.telephone}
		</p>
		<p class="p-2 m-0  text-primary">
			<i class="fas fa-envelope"></i> ${profil.email}
		</p>
		<c:if
			test="${participantEnCours.pseudo == profil.pseudo }">
			<a href="${pageContext.request.contextPath}/updateProfil"
				class="btn btn-sortir" title="modifier la sortie"><i
				class="fas fa-edit"></i></a>
		</c:if>
	</div>
</div>




<!-- / Page Content -->
<%@ include file="./layout/piedDePage.html"%>

