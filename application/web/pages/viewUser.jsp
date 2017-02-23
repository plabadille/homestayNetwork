<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="fragments/head.jsp" />

<jsp:include page="fragments/header.jsp" />

<jsp:include page="fragments/nav.jsp" />

        <main>
        <p>${user.name}</p>
            <c:choose>
                <c:when test="${empty user.name}"><p>Cet utilisateur n'existe pas.</p></c:when>
                <c:otherwise>
                    <h1>${user.firstName} ${user.name}</h1>
                    <c:if test="${!empty message}"><p style="color:red;">${message}</p></c:if>
                    <p>Email: ${user.email}</p>

                    <!-- TO DO: HOUSE BELONGING
                    <h2>Propriété(s):</h2>
                    <ul>
                        <li></li>
                    </ul> -->

                    <h2>Edition de l'utilisateur</h2>
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
                        <input type="submit" value="Ajouter" />
                    </p>
                    </form>

                </c:otherwise>
            </c:choose>

        </main>
        
<jsp:include page="fragments/footer.jsp" />