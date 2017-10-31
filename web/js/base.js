// url 编码
// 用于传递 from url
function uriEncode(from){
    from = from.replace("?", "---");
    while (from.indexOf("&") >= 0){
        from = from.replace("&", "--");
    }
    return from;
}

// url 反编码
function unUriEncode(from){
    from = from.replace("---", "?");
    while (from.indexOf("--") >= 0){
        from = from.replace("--", "&");
    }
    return from;
}

//获取项目根路径
function getRootPath(){ 
     var pathName = window.location.pathname.substring(1);
    // console.log(pathName);
     var webName = (pathName.indexOf('jnews') == -1 ) ? '' : pathName.substring(0, pathName.indexOf('/')+1);
    // console.log(webName);
     return window.location.protocol + '//' + window.location.host + '/'+ webName ;


}

var root=getRootPath();		// 全局根路径

// 获取浏览器参数
function getUrlParam(name){
    //alert(name);

	var reg = new RegExp("(^|[&|?])"+ name +"=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg);  // 匹配目标参数
    /*if(name=='tId') {
        alert(r);
        alert(window.location.search);
    }*/
	if (r!=null) return unescape(r[2]); return null; // 返回参数值
}
//去掉字符串两边的空格并去掉双引号，如:"    dd" aa  ",格式化后"dd aa";
function trimAndDelQuotation(str){
	if(str==""){
		return str;
	}
	var _temp=str.replace(/\"*/g,"");
	_temp = $.trim(_temp);
	return _temp;
}
$.views.converters("getSubStr", function(str, len) {
	if(str==undefined || str==null){
		return "";
	}
	if(str.length>len){
		 return str.substr(0,len)+"...";
	}
	return str;
});

$.views.converters("pageSizeSelected",function(first,last){
	if(first==null) return "";
	return first==last? "selected=selected":"";
});
//以指定字符串结尾
String.prototype.endWith=function(s){
  if(s==null||s==""||this.length==0||s.length>this.length)
     return false;
  if(this.substring(this.length-s.length)==s)
     return true;
  else
     return false;
  return true;
 };
 //以指定字符串打头
String.prototype.startWith=function(s){
  if(s==null||s==""||this.length==0||s.length>this.length)
   return false;
  if(this.substr(0,s.length)==s)
     return true;
  else
     return false;
  return true;
 };
 Array.prototype.contains = function(item){
    for(i=0;i<this.length;i++){
        if(this[i]==item){return true;}
    }
    return false;
};
Array.prototype.remove=function(dx){
　if(isNaN(dx)||dx>this.length){return false;}
　for(var i=0,n=0;i<this.length;i++){
　　　if(this[i]!=this[dx])
　　　{
　　　　　this[n++]=this[i]
　　　}
　}
　this.length-=1
};