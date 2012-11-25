<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>


<f:message key="companyAdmin.companyLevels" var="title" />
<s:layout-render name="/layout.jsp" title="${title}" 
                 mainMenu="${actionBean.mainMenu}" subMenu="${actionBean.subMenu}">

    <s:layout-component name="content">
        <table>
            <c:forEach items="${actionBean.allCompanyLevels}" var="acl">
                <tr>
                    <td>${acl.id}</td>

                    <td><c:out value="${acl.name}" />
                </tr>
            </c:forEach> 
        </table>
    </s:layout-component>
</s:layout-render>