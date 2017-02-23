<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <nav>
            <p>La navigation</p>
            <ul>
                <li><a href="<c:url value='/home' />" title="">Accueil</a></li>
            <c:if test="${!empty activeUser}">
                <li><a href="<c:url value="/accountManagement/${activeUser.id}" />" title="">Gestion du compte</a></li>
                <li><a href="<c:url value='/searchProperty' />" title="">Rechercher une propriété</a></li>
            </c:if>
                <li><a href="<c:url value='/adminPanel' />" title="">Admin</a></li>
            </ul>
        </nav>