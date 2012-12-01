<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>

<script>
    $(function() {
        $( "#datepicker" ).datepicker({dateFormat: "dd.mm.yy"});
    });
    $(function() {
        $( "#datepicker2" ).datepicker({dateFormat: "dd.mm.yy"});
    });
</script>



<table>
    <tr>
                    <th><s:label for="datepicker" name="reservation.start"/></th>
                    <td><s:text formatPattern="dd.MM.YYYY" formatType="date" name="resDTO.dateFrom" id="datepicker" /></p></td>
                </tr>
                <br/>
                <tr>
                    <th><s:label for="datepicker2" name="reservation.end"/></th>
                    <td><s:text formatPattern="dd.MM.YYYY" formatType="date" name="resDTO.dateTo" id="datepicker2" /></p></td>
                </tr>
         
                <br/>
                <tr>
                    <th><s:label for="b3" name="reservation.car"/></th>
                    <td><s:select name="resDTO.car">
                             <s:options-collection value="id" label="spz" collection="${actionBean.cars}" />
                             </s:select></td>
                </tr>
                <br/>
                <tr>
                    <th><s:label for="b4" name="reservation.employee"/></th>
                    <td><s:select name="resDTO.employee">
                            <s:options-collection value="id" label="name" collection="${actionBean.employees}" />
                            </s:select>
                    </td>
                </tr>
</table>