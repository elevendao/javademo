(function($){
	
	_getUrl = function(url) {
    	var pathName = window.location.pathname.substring(1);
        var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
        
        return "/" + webName + url;
    };
    
    $.fn.hzmcAjax = function(options) {
        var check_session_url = "";
        var checkFlag = false;
        $.ajax({
             type: "get",
             async: false,
             url: _getUrl('/check_session.do'),
             dataType: "json",
             success: function(msg){
                  if(msg!=null && msg["sessionCheck"]=="false"){
                     window.location.href=_getUrl('/register.do');  
                  } else {
                 	 checkFlag=true;
                  }
             },
	         error:function(){
	        	 alert("系统程序错误");
	         }
        });
        
        if (checkFlag) {
            var opts = options;
            
            if(opts.error == undefined) {
            	opts.error = function(XMLResponse) {
            		//alert(XMLResponse.responseText);
            		//alert(arguments[1]);
            		//alert(XMLResponse.status);
            		if (XMLResponse.status == 404) {
            			alert("访问的页面不存在！");
            		} else if (XMLResponse.status == 500) {
            			alert("服务器程序错误！");
            		}
            	}
            }
            $.ajax(options);
        } else {
        	alert("会话超时，请重新登录");
        }
    }
        
})(jQuery);