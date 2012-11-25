<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>


<f:message key="reservationsSubMenu.myReservations" var="title" />
<s:layout-render name="/layout.jsp" title="${title}" 
                 mainMenu="${actionBean.mainMenu}" subMenu="${actionBean.subMenu}">
    
    <s:layout-component name="content">
          <s:useActionBean beanclass="cz.muni.fi.pa165.vozovypark.web.ReservationsActionBean" var="actionBean"/>  
        <br/>
        <table>
            <tr>
                <th>ID</th>
                <th>From</th>
                <th>To</th>
                <th>Start</th>
                <th>End</th>
                <th>Confirmed</th>
                <th>Employee</th>
                <th>Car</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${actionBean.reservations}" var="res">
                <tr>
                    <td>${res.id}</td>
                    <td><c:out value="${res.dateFrom}" /></td>
                    <td><c:out value="${res.dateTo}" /></td>
                    <td><c:out value="${res.startDate}" /></td>
                    <td><c:out value="${res.returnDate}" /></td>
                    <td><c:out value="${res.confirmed}" /></td>
                    <td></td>
                    <td></td>
                    <td><s:link beanclass="cz.muni.fi.pa165.vozovypark.web.CompanyAdminActionBean" event="edit"><s:param name="companyLevel.id" value="${companyLevel.id}"/>edit</s:link> </td>
                    <td><s:link beanclass="cz.muni.fi.pa165.vozovypark.web.CompanyAdminActionBean" event="delete"><s:param name="companyLevel.id" value="${companyLevel.id}"/>delete</s:link> </td>
              
                </tr>
            </c:forEach>            
        </table>
    </s:layout-component>
</s:layout-render>
