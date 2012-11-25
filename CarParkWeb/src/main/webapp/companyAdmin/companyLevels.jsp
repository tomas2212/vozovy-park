<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>


<f:message key="companyAdmin.companyLevels" var="title" />
<s:layout-render name="/layout.jsp" title="${title}" 
                 mainMenu="${actionBean.mainMenu}" subMenu="${actionBean.subMenu}">

    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.vozovypark.web.CompanyAdminActionBean" var="actionBean"/>  
        <table class="basic">
            <tr>
                <th>id</th>
                <th>name</th>
                <th>level-value</th>
                <th></th>
            </tr>
            <c:forEach items="${actionBean.allCompanyLevels}" var="acl">
                <tr>
                    <th>${acl.id}</th>
                    <td><c:out value="${acl.name}" />
                    <td><c:out value="${acl.levelValue}" />
                </tr>
            </c:forEach> 
        </table>
    </s:layout-component>
</s:layout-render>