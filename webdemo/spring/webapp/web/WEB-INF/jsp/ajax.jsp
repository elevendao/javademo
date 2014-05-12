<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value="/js/jquery-1.3.2.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/hzmcAjax.js"/>"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			var info = {};
			$.fn.hzmcAjax({
				type: "POST",
	            dataType: "text",
	            url: "<c:url value='/test.do'/>",
	            data: info,
	            async: false,
	            success: function(data){
	            	alert(data);
	            }
			});
		});
	</script>
  </head>
  
  <body>
    <div>ttt</div>
  </body>
</html>
