<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="fragments/head.jsp"/>

<jsp:include page="fragments/header.jsp"/>

<jsp:include page="fragments/nav.jsp"/>

<main>

  <h1>Homestay Network</h1>

  <c:if test="${!empty message}">
    <p style="color:red;">${message}</p>
  </c:if>

  <h2>Fonctionnement de l'application</h2>
  <p>W.I.P</p>

  <c:if test="${empty activeUser}">
    <h2>Créer un compte</h2>
    <form method="post" action="<c:url value='addUser' />">
      <p>
        <div>Nom: <input type="text" name="name"/></div>
        <div>Prénom: <input type="text" name="firstName"/></div>
        <div>Email: <input type="email" name="email"/></div>
        <div>Mot de passe: <input type="password" name="password"/></div>
        <div>Resaisir le mot de passe: <input type="password" name="equalPassword"/></div>
        <input type="submit" value="Ajouter"/>
      </p>
    </form>
    <c:if test="${empty admin}">
      <h2>Se connecter</h2>
      <form method="post" action="<c:url value='connexion' />">
        <p>
          <div>Email : <input type="email" name="email"/></div>
          <div>Mot de passe : <input type="password" name="password"/></div>
          <input type="submit" value="Envoyer"/>
        </p>
      </form>
    </c:if>
  </c:if>

</main>

<jsp:include page="fragments/footer.jsp"/>
