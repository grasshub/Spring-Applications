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
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
<head>
    <title><spring:message code="title.details"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/app.css' />">
</head>

<body>

<h1><spring:message code="title.details"/></h1>

  <p> Information on DVD with id <!-- todo print the id here (using EL) --> ${dvd.id}</p>

    <table border="1">
      <tbody>
        <!-- todo for each property (title,releaseYear, actors) display the value in a row (Using EL)-->
        <tr>
          <td>
            Title
          </td>
          <td>
            ${dvd.title}
          </td>
        </tr>
        <tr>
          <td>
            Released
          </td>
          <td>
            ${dvd.yearMonthRelease}
          </td>
        </tr>
        <tr>
          <td>
            Actors
          </td>
          <td>
            ${dvd.starring}
          </td>
        </tr>
      </tbody>
    </table>


<br/>
<a href="../.">Back to top</a>

  </body>
</html>
