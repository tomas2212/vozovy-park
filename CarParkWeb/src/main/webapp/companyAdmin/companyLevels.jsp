<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>


<f:message key="companyAdmin.companyLevels" var="title" />
<s:layout-render name="/layout.jsp" title="${title}" 
                 mainMenu="${actionBean.mainMenu}" subMenu="${actionBean.subMenu}">

    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.vozovypark.web.CompanyAdminActionBean" var="actionBean"/>  
        <br/>
        <table>
            <tr>
                <th>ID</th>
                <th></th>
                <th>Name</th>
                <th></th>
                <th>Level-value</th>
            </tr>
            <c:forEach items="${actionBean.allCompanyLevels}" var="acl">
                <tr>
                    <td>${acl.id}</td>
                    <td></td>
                    <td><c:out value="${acl.name}" />
                    <td></td>
                    <td><c:out value="${acl.levelValue}" />
                    <td></td>
                    <td><s:link beanclass="cz.muni.fi.pa165.vozovypark.web.CompanyAdminActionBean" event="edit"><s:param name="companyLevel.id" value="${companyLevel.id}"/>edit</s:link> </td>
                    <td></td>
                    <td><s:link beanclass="cz.muni.fi.pa165.vozovypark.web.CompanyAdminActionBean" event="delete"><s:param name="companyLevel.id" value="${companyLevel.id}"/>delete</s:link> </td>
                </tr>
            </c:forEach>            
        </table>
    </s:layout-component>
</s:layout-render>