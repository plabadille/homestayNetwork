<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="fragments/head.jsp" />

<jsp:include page="fragments/header.jsp" />

<jsp:include page="fragments/nav.jsp" />

        <main>
            <h1>Pannel d'administration</h1>
          <c:if test="${empty admin}">
            <h2>Se connecter</h2>
            <form method="post" action="<c:url value='adminConnexion' />">
            <p>
                Login&nbsp;: 
                <input type="text" name="login" />
                <br />
                Mot de passe&nbsp;: 
                <input type="password" name="password" />
              <br />
              <input type="submit" value="Envoyer" />
            </p>
            </form>
          </c:if>

            <c:if test="${!empty message}"><p style="color:red;">${message}</p></c:if>

          <c:if test="${!empty admin}">
            <h2>Gestion des utilisateurs</h2>

            <c:choose>
              <c:when test="${empty allPersons}"><p>Il n'y a pour le moment aucun utilisateur sur cette application.</p></c:when>
              <c:otherwise>
                <ul>
                  <c:forEach var="person" items="${allPersons}">
                    <li>
                      ${person.firstName} ${person.name} - <a href="<c:url value="deleteUser?id=${person.id}" />">Supprimer</a>
                    </li> 
                  </c:forEach>
                </ul>
              </c:otherwise>
            </c:choose>

            <h2>Gestion des offres</h2>
            <p>W.I.P</p>
            <!-- TO DO: 
            - admin can choose a user in a list
            - when a user is choosen we display the property link
            - the administrator can delete an offer -->
          </c:if>

        </main>
        
<jsp:include page="fragments/footer.jsp" />