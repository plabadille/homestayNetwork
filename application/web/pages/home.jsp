<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="fragments/head.jsp" />

<jsp:include page="fragments/header.jsp" />

<jsp:include page="fragments/nav.jsp" />

        <main>
            <h1>Homestay Network</h1>

            <p id="test">test</p>

            <h2>Tous nos utilisateurs</h2>

            <c:choose>
              <c:when test="${empty allPersons}"><p>Il n'y a pour le moment aucun utilisateur sur cette application.</p></c:when>
              <c:otherwise>
                <ul>
                  <c:forEach var="person" items="${allPersons}">
                    <li>
                      <a href="<c:url value="viewUser/${person.id}" />">${person.firstName} ${person.name} </a> - <a href="<c:url value="deleteUser?id=${person.id}" />">Supprimer</a>
                    </li> 
                  </c:forEach>
                </ul>
              </c:otherwise>
            </c:choose>

            <h2>Ajouter un utilisateur</h2>
            <c:if test="${!empty message}"><p style="color:red;">${message}</p></c:if>
            <form method="post" action="<c:url value='addUser' />">
            <p>
                Nom&nbsp;: 
                <input type="text" name="name" />
                <br />
                Prénom&nbsp;: 
                <input type="text" name="firstName" />
                <br />
                Email&nbsp;: 
                <input type="email" name="email" />
              <br />
              <input type="submit" value="Ajouter" />
            </p>
            </form>

        </main>
        
<jsp:include page="fragments/footer.jsp" />