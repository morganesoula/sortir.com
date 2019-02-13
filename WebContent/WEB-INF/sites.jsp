
<%@ include file="./layout/entete.jsp"%>
<%@ include file="./layout/navBar.jsp"%>

<!-- Page Content -->

<h2 class="m-5 text-uppercase">
	Les Sites 
	<a href="<%=request.getContextPath()%>/nouveauSite"
		title="ajouter un site" class="sortir"><span
			class="fa-stack fa-xs"> <i class="fas fa-circle fa-stack-2x"></i>
				<i class="fas fa-plus fa-stack-1x fa-inverse"></i>
		</span></a>
	</small>
</h2>

<div class="row p-5 m-0">
	<div class="col-md-6 offset-md-3">
		<table class="table table-striped table-responsive table-hover">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Nom</th>
					<th scope="col">Modifier</th>
					<th scope="col">Supprimer</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach var="site" items="${listeSites}">
					<tr>
						<th>${site.nom}</th>

						<td width="5%"><a href="${pageContext.request.contextPath}/editerSite?idSite=${site.idSite}" class="btn btn-lg btn-sortir" title="modifier le site"><i class="fas fa-edit"></i></a></td>
						<td width="5%"><a href="${pageContext.request.contextPath}/supprimerSite?idSite=${site.idSite}" class="btn btn-lg btn-danger" title="supprimer le site"><i class="fas fa-trash-alt"></i></a></td>
								
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<!-- / Page Content -->
<%@ include file="./layout/piedDePage.html"%>

