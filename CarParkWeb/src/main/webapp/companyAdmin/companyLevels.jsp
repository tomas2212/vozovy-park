<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>


<f:message key="companyAdmin.companyLevels" var="title" />
<s:layout-render name="/layout.jsp" title="${title}" 
                 mainMenu="${actionBean.mainMenu}" subMenu="${actionBean.subMenu}">

    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.vozovypark.web.CompanyAdminActionBean" var="actionBean"/>
        <table>
            <c:forEach items="${actionBean.companyLevels}" var="acl">
                <tr>
                    <td>${acl.id}</td>
                    <td><c:out value="${acl.levelValue}" />
                    <td><c:out value="${acl.name}" />
                </tr>
            </c:forEach>  
        </table>
    </s:layout-component>
</s:layout-render>