<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="fragments/head.jsp"/>

<jsp:include page="fragments/header.jsp"/>

<main>

  <h1>Gestion de votre compte</h1>
  <c:if test="${!empty message}">
    <p style="color:green;">${message}</p>
  </c:if>
  <c:choose>
    <c:when test="${empty activeUser}">
      <p>Vous devez être connecté pour accéder à cette page</p>
    </c:when>
    <c:when test="${activeUser.id != user.id}">
      <p>Vous ne pouvez pas visualiser un compte auquel vous n'êtes pas connecté</p>
    </c:when>
    <c:otherwise>
      <h2>Mon profil</h2>
      <ul>
        <li>Prénom: ${user.firstName}</li>
        <li>Nom: ${user.name}</li>
        <li>Email: ${user.email}</li>
      </ul>

      <hr/>
      <h2>Mes propriété(s):</h2>
      <c:choose>
        <c:when test="${empty housings}">
          <p>Il n'y a pour le moment aucun logement d'enregistré.</p>
        </c:when>
        <c:otherwise>
          <ul>
            <c:forEach var="housing" items="${housings}">
              <li>${housing.address} - <a href="<c:url value="/editHousing/${housing.id}" />">Modifier</a></li>
            </c:forEach>
          </ul>
        </c:otherwise>
      </c:choose>

      <hr/>
      <h2>Mes Reservation(s):</h2>
      <c:choose>
        <c:when test="${empty reservations}">
          <p>Il n'y a pour le moment aucune reservation d'enregistré.</p>
        </c:when>
        <c:otherwise>
          <ul>
            <c:forEach var="reservation" varStatus="loop" items="${reservations}">
              <li>Du ${reservation.beginDateObject} au ${reservation.endDateObject}: ${reservedHousings[loop.index].address}</li>
            </c:forEach>
          </ul>
        </c:otherwise>
      </c:choose>

      <hr/>
      <h2>Ajouter une propriété:</h2>
      <form method="post" action="<c:url value='/addHousing' />">
        <p>
          <div>Surface: <input type="number" value="0" min="1" name="surface" required></div>
          <div>Nombre de pièces: <input type="number" value="0" min="1" name="nbRoom" required></div>
          <div>Surface du jardin: <input type="number" value="0" min="0" name="gardenSurface" required></div>
          <div>Appartement ? <input type="checkbox" name="isApartment"></div>
          <div>Adresse: <input type="text" name="address" required></div>
          <div>Pays: <input type="text" name="country" required></div>
          <input type="submit" value="Envoyer">
        </p>
      </form>

      <hr/>
      <h2>Editer mes informations</h2>
      <form method="post" action="<c:url value='editUser' />">
        <p>
          <div>Nom: <input type="text" name="name" value="${user.name}"/></div>
          <div>Prénom: <input type="text" name="firstName" value="${user.firstName}"/></div>
          <div>Email: <input type="email" name="email" value="${user.email}"/></div>
          <input type="hidden" name="id" value="${user.id}"/>
          <input type="submit" value="Envoyer"/>
        </p>
      </form>

      <hr/>
      <h2>Changer mon mot de passe</h2>
      <form method="post" action="<c:url value='updatePassword' />">
        <p>
          <div>Ancien mot de passe: <input type="password" name="password"/></div>
          <div>Nouveau mot de passe: <input type="password" name="newPassword"/></div>
          <div>Resaisissez votre nouveau mot de passe: <input type="password" name="equalPassword"/></div>
          <input type="hidden" name="id" value="${user.id}"/>
          <input type="submit" value="Envoyer"/>
        </p>
      </form>
    </c:otherwise>
  </c:choose>

</main>

<jsp:include page="fragments/footer.jsp"/>
