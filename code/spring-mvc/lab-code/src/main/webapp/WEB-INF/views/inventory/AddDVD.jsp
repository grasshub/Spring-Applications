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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
  <title><spring:message code="title.add_dvd"/></title>
</head>
<body>

<a href="?language=en"><spring:message code="choose_language.english"/></a> | <a href="?language=de"><spring:message code="choose_language.german"/> </a>

<h1><spring:message code="title.add_dvd"/></h1>

<c:url var="add_dvd_url" value="/inventory/addDVD.html"/>

<table>
    <form:form action="${add_dvd_url}" name="addDVD"
               method="POST"
               commandName="command">
    <tr> <td> DVD id:</td> <td><form:input  path="id"/> </td>
        <td> <form:errors path="id" cssStyle="color: red;"/></td> </tr>
    <tr> <td> DVD Title:</td> <td><form:input path="title"/> </td>
        <td> <form:errors path="title" cssStyle="color: red;"/> </td> </tr>
    <tr> <td>  Actors :</td> <td><form:input path="actors"/> </td>
        <td> <form:errors path="actors" cssStyle="color: red;"/> </td> </tr>
    <tr> <td>  Release Year:</td> <td><form:input path="releaseYear"/> </td>
        <td> <form:errors path="releaseYear" cssStyle="color: red;"/> </td> </tr>
    <tr> <td colspan=3>   <input type="submit"> </td>
        </form:form>
</table>
<!-- declare a form (action and method parameter) -->
<%--
<form name="addDVD" action="${add_dvd_url}" method="POST">
  <table>

    <tr>
      <!-- todo for each property on the DVD (id, title, actors and releaseYear)
           add a row with a label and an input field bound to the appropriate command property
      -->
      <td>
        DVD id
      </td>
      <td>
        <spring:bind path="command.id">
          <input name="id" value="${status.value}"/>
        </spring:bind>
      </td>
    </tr>
    <tr>
      <td>
        Title
      </td>
      <td>
        <spring:bind path="command.title">
          <input name="title" value="${status.value}"/>
        </spring:bind>
      </td>
    </tr>

    <tr>
      <td>
        Actors
      </td>
      <td>
        <spring:bind path="command.actors">
          <input name="actors" value="${status.value}"/>
        </spring:bind>
      </td>
    </tr>


    <tr>
      <td>
        Release Year
      </td>
      <td>
        <spring:bind path="command.releaseYear">
          <input name="releaseYear" value="${status.value}"/>
        </spring:bind>
      </td>
    </tr>
    <tr>
      <td>
        <input type="submit"/>
      </td>
    </tr>
  </table>
</form>
--%>


<br/>
<a href="../.">Back to top</a>

</body>
</html>