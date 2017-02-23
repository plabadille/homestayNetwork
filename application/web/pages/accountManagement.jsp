<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="fragments/head.jsp" />

<jsp:include page="fragments/header.jsp" />

<jsp:include page="fragments/nav.jsp" />

        <main>

            <h1>Gestion de votre compte</h1>
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

                <h2>Mes propriété(s):</h2>
                <p>W.I.P</p>
                <!-- TO DO: HOUSE BELONGING
                
                <ul>
                    <li></li>
                </ul> -->

                <h2>Ajouter une propriété:</h2>
                <p>W.I.P</p>
                <!-- TO DO: AJOUTER UNE PROPRIETE -->

                <h2>Editer mes informations</h2>
                <form method="post" action="<c:url value='editUser' />">
                <p>
                    Nom&nbsp;: 
                    <input type="text" name="name" value="${user.name}" />
                    <br />
                    Prénom&nbsp;: 
                    <input type="text" name="firstName" value="${user.firstName}" />
                    <br />
                    Email&nbsp;: 
                    <input type="email" name="email" value="${user.email}"  />
                    <br />
                    <input type="hidden" name="id" value="${user.id}" />
                    <input type="submit" value="Envoyer" />
                </p>
                </form>

                <h2>Changer mon mot de passe</h2>
                <form method="post" action="<c:url value='updatePassword' />">
                <p>
                    Ancien mot de passe&nbsp;: 
                    <input type="password" name="password" />
                    <br />
                    Nouveau mot de passe&nbsp;: 
                    <input type="password" name="newPassword" />
                    <br />
                    Resaisissez votre nouveau mot de passe&nbsp;: 
                    <input type="password" name="equalPassword" />
                    <br />
                    <input type="hidden" name="id" value="${user.id}" />
                    <input type="submit" value="Envoyer" />
                </p>
                </form>
           </c:otherwise>
        </c:choose>

        </main>
        
<jsp:include page="fragments/footer.jsp" />