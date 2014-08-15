/*require(["dojo/fx","dojo/fx/easing","dojo/dom","dojo/dom-style","dojo/on","dojo/dom-construct","dojo/domReady!"],
	function(coreFx, easing, dom, style, on,domConstruct){

	var alert = "<div id='alertBar' style='display:none;'>" +
				"<span id='alertBarInfo'></span>" +
				"<span id='alertBarClose'>×</span>" +
				"</div>";
	var alertBar = domConstruct.toDom(alert);
	domConstruct.place(alertBar,document.body);
	  
  on(dom.byId("alertBarClose"), "click", function(){
	if(style.get("alertBar","display") == "block") {
		coreFx.wipeOut({
		  node: "alertBar",
		  duration: 800,
		  easing: easing.expoOut
		}).play();
	}
  });  
});

*/
function myAlert(info,type){
	if( type == 'succ' ) {
		noty({
			text    : info,
			type    : 'success',
			layout  : 'topCenter',
			timeout : 2000
		});
	} else {
		noty({
			text    : info,
			type    : 'error',
			layout  : 'topCenter',
			timeout : 2000
		});
	}
/*require(["dojo/fx","dojo/fx/easing","dojo/dom","dojo/dom-style","dojo/on","dojo/dom-class","dojo/domReady!"],
	function(coreFx, easing, dom, style, on,domClass){
	
	domClass.remove("alertBar");
	if(type == "succ")
		domClass.add("alertBar","alertBarSuccess");
	else
		domClass.add("alertBar","alertBarError");

	if( style.get("alertBar","display") == "none") {
		dom.byId("alertBarInfo").innerHTML = info;
		//style.set("alertBar","display","block");
		coreFx.wipeIn({
		  node: "alertBar",
		  duration: 800,
		  easing: easing.expoOut
		}).play();
		setTimeout(function(){
			coreFx.wipeOut({
			  node: "alertBar",
			  duration: 1000,
			  easing: easing.expoOut
			}).play();
		},5000);
	}
});*/
}