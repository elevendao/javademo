<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/jqModal.css' />">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/box.css' />">
	<script type="text/javascript" src="<c:url value='/js/jquery-1.3.2.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/jqModal.js'/>"></script>
	<style type="text/css">
	div.jqmDialog {
    display: none;
    font-family: verdana,tahoma,helvetica;
    left: 50%;
    margin-left: -200px;
    overflow: hidden;
    position: fixed;
    top: 17%;
    width: 400px;
    }
	* html div.jqmDialog {
	    position: absolute;
	}
div.jqmdBC {
    background: url("dialog/bc.gif") repeat-x scroll center bottom transparent;
    height: 180px;
    overflow: auto;
    padding: 7px;
}
div.jqmdBL {
    background: url("dialog/bl.gif") no-repeat scroll left bottom transparent;
    padding-left: 7px;
}
div.jqmdBR {
    background: url("dialog/br.gif") no-repeat scroll right bottom transparent;
    padding-right: 7px;
}
div.jqmdMSG {
    color: #317895;
}
div.jqmdFL {
    background: url("dialog/sprite.gif") no-repeat scroll 0 -41px transparent;
    padding-left: 3px;
}
div.jqmdFR {
    background: url("dialog/sprite.gif") no-repeat scroll right 0 transparent;
    padding-right: 3px;
}
div.jqmdFC {
    background: url("dialog/sprite.gif") repeat-x scroll 0 -82px #D5FF84;
    color: #528C00;
    font-family: "sans serif",verdana,tahoma,helvetica;
    font-weight: bold;
    text-align: right;
    padding: 7px 22px 5px 5px;
}
input.jqmdX {
    background: url("images/close.gif") no-repeat scroll left top transparent;
    height: 19px;
    overflow: hidden;
    padding: 0 0 0 19px;
    position: absolute;
    right: 7px;
    top: 4px;
    width: 0;
}
input.jqmdXFocus {
    background-position: left bottom;
    outline: medium none;
}
div.jqmdBC button, div.jqmdBC input[type="submit"] {
    background-color: #FFFFFF;
    color: #777777;
    cursor: pointer;
    margin: 8px 10px 4px;
}
div.jqmDialog input:focus, div.jqmDialog input.iefocus {
    background-color: #EAFFC3;
}
	div.jqmdWide { width: 560px; margin-left: -280px; }
	</style>
	<script type="text/javascript">
	$(document).ready(function() {
		//alert("haha");
		$('#ex4').jqm({
            trigger : '#clickme',
            overlay : 0,
            modal : true,
            onShow: function(h) {
                h.w.show();
            },
            onHide : function(h) {
                h.o.remove();
                h.w.fadeOut(800);
            }
        });
	});
	function hdPostForm(data) {
		if (data == null) {
			return;
		}
        var form = $("<form name='registerForm' id='registerForm' action='<c:url value='/register.do'/>' method='post'></form>");
        for (var o in data) {
            var input = $("<input type='hidden'/>");
            input.attr("name", o);
            input.attr("value", data[o]);
            input.appendTo(form);
        }
        form.appendTo($("body"));
        form.submit();
	}
	
	/*function register() {
		var id=$("#id").val();
		var name=$("#name").val();
		var desc=$("#desc").val();
	    var data = {id:id,name:name,desc:desc};
		hdPostForm(data);
	}*/
	
	function register() {
		var id=$("#id").val();
        var name=$("#name").val();
        var desc=$("#desc").val();
        var data = {id:id,name:name,desc:desc};
        
        $.ajax({
        	url:'<c:url value="/ajax.do"/>',
        	method:'get',
        	async:false,
        	//dataType:'json',
        	data:data,
        	beforeSend: function(jqXHR, settings) {
        		//alert("before send");
        		$("#load_div").html('<p><img src="/images/bar_loader.gif" width="220" height="19" /></p>');
        	},
        	//error:function(XMLResponse){myAlert(XMLResponse.responseText);myAlert(arguments[1]); },
        	success:function(data) {
        		//alert(data['id'] + "," + data['name']);
        		//alert(data);
        		$("#load_div").html(data);
        	}
        });
	}
	</script>
  </head>
  
  <body>
    This is hello JSP page. <br>
    id: <input type="text" id="id" value=""/>
    name: <input type="text" id="name" value=""/><br/>
    desc: <textarea id="desc" rows="10" cols="20"></textarea><br/>
    <input type="button" onclick="register();" value="Register">
    <a id="clickme" href="#">click me 1</a>
    
    <div id="load_div"></div>

	<div id="ex4" class="jqmDialog jqmdWide">
		<div class="jqmdTL">
			<div class="jqmdTR">
				<div class="jqmdTC">Modal Dialog</div>
			</div>
		</div>
		<div class="jqmdBL">
			<div class="jqmdBR">
				<div class="jqmdBC">
					<div class="jqmdMSG">
						<p>
							Please wait... 
						</p>
					</div>
				</div>
			</div>
		</div>
		<div class="jqmdFL">
            <div class="jqmdFR">
                <div class="jqmdFC">
                <input type="button" value="确定">
                <input type="button" value="取消">
                </div>
            </div>
        </div>
		<input type="image" src="images/close.gif" class="jqmdX jqmClose" />
	</div>

</body>
</html>
