require(["dojo/dom","dojo/on","dojo/request","dojo/domReady!"],function(dom,on,request){

	on(dom.byId('exit') , 'click', function(){
		if(confirm("确定要退出吗？")){
			window.location.href="j_spring_security_logout";//"logout";
		}
		return false;
	});

});

$(document).ready(function(){
	$("nav li").hover(function(){
		$(this).find("ul").slideDown("slow");	
	},function(){
		$(this).find("ul").slideUp("fast");	
	});
});

/*=====获取链接中的参数========*/
function getParam(sname) {
	var params = location.search.substr(location.search.indexOf("?") + 1);
	var sval = "";
	params = params.split("&");
	// split param and value into individual pieces
	for ( var i = 0; i < params.length; i++) {
		temp = params[i].split("=");
		if ([ temp[0] ] == sname) {
			sval = temp[1];
		}
	}
	return sval;
}
/*=====验证文本框输入的是否是数字========*/
function validate(node,info){
	var reg=new RegExp("^-?\\d+$");
	if(!reg.test(node.val())){
		myAlert(info);
		return false;
	}else{
		return true;
	}
}
/*=====表格中的行选择========*/
function row_selected(checkAll,oTable){
	$(checkAll).on("click",function(){
		if (this.checked) {
		$('input[name="subck"]',oTable).each(function() {
			this.checked = true;
			$(this).parent().parent().addClass('row_selected');
		});
	} else {
		$('input[name="subck"]',oTable).each(function() {
			this.checked = false;
			$(this).parent().parent().removeClass('row_selected');
		});
	}			
	});
	oTable.on('click', 'input[name="subck"]', function() {
		$(this).parent().parent().toggleClass('row_selected');
	});
}
//取消行选择
function cancel_selected(checkAll,oTable){
	if($(checkAll).checked){			
		$('input[name="subck"]',oTable).each(function(){
			this.checked = false;
			$(this).parent().parent().removeClass('row_selected');
		});
	}else{
		$('input[name="subck"]',oTable).each(function(){
			if(this.checked){
				$(this).attr("checked",false);
				$(this).parent().parent().removeClass('row_selected');
			}
		})
	}
	$(checkAll).attr("checked",false);	
}

//获取选中行
function fnGetSelectedRows(oTable) {
		return oTable.$("tr.row_selected");
	}

function fnGetSelectedRowData(oTable) {
	var aReturn = new Array();
	var aTrs = oTable.fnGetNodes();
	for ( var i = 0; i < aTrs.length; i++) {
		if ($(aTrs[i]).hasClass('row_selected')) {
			aReturn.push(oTable.fnGetData(aTrs[i]));
		}
	}
	return aReturn;
}

//表头的自定义
function fnShowHide(iCol) {
	var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
	oTable.fnSetColumnVis(iCol, bVis ? false : true);
}
