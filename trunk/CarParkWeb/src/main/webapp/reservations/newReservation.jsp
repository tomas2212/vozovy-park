<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <script src="http://code.jquery.com/jquery-1.8.2.js"></script>
    <script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
    <script>
    $(function() {
        $( "#datepicker" ).datepicker();
    });
    </script>
    <script>
    $(function() {
        $( "#datepicker2" ).datepicker();
    });
    </script>
    <script>
    $(function() {
        $( "#datepicker3" ).datepicker();
    });
    </script>
    <script>
    $(function() {
        $( "#datepicker4" ).datepicker();
    });
    </script>


<f:message key="reservationsSubMenu.newReservation" var="title" />
<s:layout-render name="/layout.jsp" title="${title}" 
                 mainMenu="${actionBean.mainMenu}" subMenu="${actionBean.subMenu}">
    
   <s:layout-component name="content">
        <s:form beanclass="cz.muni.fi.pa165.vozovypark.web.ReservationsActionBean">
            <fieldset><legend> New Reservation </legend>
                <br/>
                <tr>
                    <th><s:label for="b1" name="start.date"/></th>
                    <td><p><input type="text" id="datepicker" /></p></td>
                </tr>
                <br/>
                <tr>
                    <th><s:label for="b2" name="end.date"/></th>
                    <td><p><input type="text" id="datepicker2" /></p></td>
                </tr>
                <br/>
                <tr>
                    <th><s:label for="b1" name="rent.date"/></th>
                    <td><p><input type="text" id="datepicker3" /></p></td>
                </tr>
                <br/>
                <tr>
                    <th><s:label for="b2" name="return.date"/></th>
                    <td><p><input type="text" id="datepicker4" /></p></td>
                </tr>
                <br/>
                <tr>
                    <th><s:label for="b3" name="car"/></th>
                    <td><select name="cars">
                            <c:forEach items="${actionBean.cars}" var="car">
                                <option>${car.spz}</option>
                            </c:forEach>
                        </select></td>
                </tr>
                <br/>
                <tr>
                    <th><s:label for="b4" name="employee"/></th>
                    <td><select name="employees">
                            <c:forEach items="${actionBean.employees}" var="emp">
                                <option>${emp.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <br/>
                <s:submit name="addReservation">Create</s:submit>
            </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>
