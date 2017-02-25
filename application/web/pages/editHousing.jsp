<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="fragments/head.jsp"/>

<jsp:include page="fragments/header.jsp"/>

<main>
  <h1>Gestion de propriété</h1>
  <c:choose>
    <c:when test="${empty activeUser}">
      <p>Vous devez être connecté pour accéder à cette page</p>
    </c:when>
    <c:when test="${empty housing}">
      <p>Vous ne pouvez pas visualiser une propriété qui ne vous appartient pas.</p>
    </c:when>
    <c:otherwise>
      <h2>Editer la propriété</h2>
      <form method="post" action="<c:url value='/editHousing/${housing.id}' />">
        <p>
          <div>Surface: <input type="number" min="1" value="${housing.surface}" name="surface" required></div>
          <div>Nombre de pièces: <input type="number" min="1" value="${housing.nbRoom}" name="nbRoom" required></div>
          <div>Surface du jardin: <input type="number" min="0" value="${!isApartment ? housing.gardenSurface : 0}" name="gardenSurface" required></div>
          <div>Appartement ? <input type="checkbox" name="isApartment" ${isApartment ? 'checked' : ''}></div>
          <div>Adresse: <input type="text" name="address" value="${housing.address}" required></div>
          <div>Pays: <input type="text" name="country" value="${housing.country}" required></div>
          <input type="hidden" name="id" value="${housing.id}">
          <input type="submit" value="Envoyer">
        </p>
      </form>

      <h2>Gérer les offres</h2>
      <p>W.I.P</p>

      <h2>Ajouter une offre</h2>
      <form method="post" action="<c:url value='/addOffer' />">
        <p>
          <div>Date de début: <input type="text" name="startDate" placeholder="15/10/2017" required></div>
          <div>Date de fin: <input type="text" name="endDate" placeholder="20/10/2017" required></div>
          <input type="hidden" name="housingId" value="${housing.id}">
          <input type="submit" value="Envoyer">
        </p>
      </form>
    </c:otherwise>
  </c:choose>
</main>

<jsp:include page="fragments/footer.jsp"/>
