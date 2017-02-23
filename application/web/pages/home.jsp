<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="fragments/head.jsp" />

<jsp:include page="fragments/header.jsp" />

<jsp:include page="fragments/nav.jsp" />

        <main>
            <h1>Homestay Network</h1>

            <c:if test="${!empty message}"><p style="color:red;">${message}</p></c:if>
            
            <h2>Fonctionnement de l'application</h2>
            <p>W.I.P</p>

            <c:if test="${empty activeUser}">
              <h2>Créer un compte</h2>
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
                  Mot de passe&nbsp;: 
                  <input type="password" name="password" />
                  <br />
                  Resaisir le mot de passe&nbsp;: 
                  <input type="password" name="equalPassword" />
                <br />
                <input type="submit" value="Ajouter" />
              </p>
              </form>
            <c:if test="${empty admin}">
              <h2>Se connecter</h2>
              <form method="post" action="<c:url value='connexion' />">
              <p>
                  Email&nbsp;: 
                  <input type="email" name="email" />
                  <br />
                  Mot de passe&nbsp;: 
                  <input type="password" name="password" />
                <br />
                <input type="submit" value="Envoyer" />
              </p>
              </form>
            </c:if>
            </c:if>

        </main>
        
<jsp:include page="fragments/footer.jsp" />