<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="fragments/head.jsp"/>

<jsp:include page="fragments/header.jsp"/>

<main>
  <h1>Modifier une propriété</h1>
  <c:if test="${!empty message}">
    <p style="color:green;">${message}</p>
  </c:if>
  <c:choose>
    <c:when test="${empty activeUser}">
      <p>Vous devez être connecté pour accéder à cette page</p>
    </c:when>
    <c:when test="${empty housing}">
      <p>Vous ne pouvez pas visualiser une propriété qui ne vous appartient pas.</p>
    </c:when>
    <c:otherwise>
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
    </c:otherwise>
  </c:choose>
</main>

<jsp:include page="fragments/footer.jsp"/>
