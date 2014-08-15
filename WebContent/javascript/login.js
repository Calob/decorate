require(["dojo/dom","dojo/on","dojo/request","dojo/domReady!"],function(dom,on,request){
/*	var username = dom.byId("login_username");
	var password = dom.byId("login_password");
	var submit = dom.byId("submit_login");

	on(submit,"click", function() {
		var url = "ajax/login?username="+username.value+"&password="+password.value;
	    request.get(url,{
			handleAs:"json"
		}).then( 
		function(response){	
			if(response.Status == "success")
				window.location.href = "dashboard";		
			else if(response.Status == "Incorrect")
				myAlert("Incorrect");
			else
				myAlert("error");
		},
			function(error){
				myAlert("error sir");
			}
		);
	});*/
	on(dom.byId("submit_register"),"click",function(){		
		window.location.href="register";
	});
	expireAt = new Date;
	expireAt.setMonth(expireAt.getMonth() - 1);

	if (document.cookie != "") {
	    crumbs = document.cookie.split(";");
	    for(var i=0; i < crumbs.length; i++) {
	       crumbName = crumbs[i].split("=")[0];
	       document.cookie = crumbName + "=;expires=" + expireAt.toGMTString();
	    }
	}	
	if(getParam("error")=="true"){
		myAlert("用户名或密码错误，请重新登录！");
	}
});

