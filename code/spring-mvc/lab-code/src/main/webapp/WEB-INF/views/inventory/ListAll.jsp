<!--

 This component and its source code representation are copyright protected
 and proprietary to The Trivera Group, Inc., Worldwide D/B/A Trivera Technologies

 This component and source code may be used for instructional and
 evaluation purposes only. No part of this component or its source code
 may be sold, transferred, or publicly posted, nor may it be used in a
 commercial or production environment, without the express written consent
 of the Trivera Group, Inc.

 Copyright (c) 2014 The Trivera Group, LLC.
 http://www.triveratech.com   http://www.triveragroup.com
 </p>
 @author The Trivera Group Tech Team.

-->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="title.list_all"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/app.css' />">
</head>
<body>

<a href="?language=en"><spring:message code="choose_language.english"/></a> | <a href="?language=de"><spring:message code="choose_language.german"/></a>

<h1><spring:message code="title.list_all"/></h1>

<p>Below is a list of the DVDs in the system</p>
<p>System contains (${catalog.size()}) DVD's</p>

<table>
  <tbody>


    <%--//-------------------------------------------------------------------//--%>
    <%--//-------------------------------------------------------------------//--%>
    <%-- todo iterate though the model element (catalog) using the forEach JSTL tag --%>
    <c:forEach items="${catalog}" var="dvdinfo">
      <tr>
        <td>
          <!-- todo for each dvdinfo add a link to details.view?dvdID= with the value of the id, and display the title. -->
          <a href="details.html?dvdID=${dvdinfo.id}">${dvdinfo.title}</a>
        </td>
      </tr>
    </c:forEach>



  </tbody>
</table>

<br/>
<a href="../.">Back to top</a>


</body>
</html>
