<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarTogglerDemo01">
		<div class="site-title text-white"
			style="font-size: 30px; cursor: pointer;"
			onclick="javascript:window.location.href='${pageContext.request.contextPath}/sorties'"
			onmouseenter="">
			Sortir<span>.com</span>
		</div>
		<ul class="navbar-nav mr-auto mt-2 mt-lg-0 ml-3">
			<li class="nav-item active ml-5"><a href="${pageContext.request.contextPath}/sorties"
				class="text-white"><i class="fas fa-glass-martini-alt fa-3x "></i>
					Sorties</a></li>
					
			<c:if test="${participantEnCours.administrateur }">
			<li class="nav-item active ml-5"><a href="${pageContext.request.contextPath}/villes"
				class="text-white"><i class="fas fa-map-marker-alt fa-3x "></i>
					Villes</a></li>
			<li class="nav-item active ml-5"><a href="${pageContext.request.contextPath}/sites"
				class="text-white"><i class="fas fa-map fa-3x "></i> Sites ENI</a></li>
			</c:if>
		</ul>
		<ul class="navbar-nav  mt-2 mt-lg-0 ">
			<li class="nav-item dropdown" style="font-size:18px;"><a
				class="nav-link dropdown-toggle" href="#"
				id="navbarDropdownMenuLink" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false"> Vous êtes connecté en tant que ${participantEnCours.pseudo} <i
					class="fas fa-user "></i> 
			</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" href="${pageContext.request.contextPath}/profil">Mon profil</a> <a
						class="dropdown-item" href="${pageContext.request.contextPath}/logout">Se déconnecter</a> 
				</div></li>
			<!-- 			<li class="nav-item active ml-5" title="mon profil"><a href="/SortirENI/profil" -->
			<%-- 				class="text-white"><i class="fas fa-user fa-3x "></i> ${participantEnCours.pseudo}</a> --%>
			<!-- 			</li> -->
<!-- 			<li class="nav-item active ml-3"><a -->
<!-- 				class="btn btn-sortir my-2 my-sm-0" href="/SortirENI/logout">Se -->
<!-- 					déconnecter</a></li> -->
		</ul>

	</div>
</nav>