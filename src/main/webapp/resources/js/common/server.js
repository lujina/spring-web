define(function(require, exports, module){
	var host = "./";
	exports.server = {
		user: {
			queryInfo: function(data, success, async, type){
				var url = host + "back/user/info";
				myAjax(url, data, success, async, type);
			}

		}
	}
	//ajax函数，减少写ajax的频率，将接口写在一起方便管理
	function myAjax(url, data, success, async, type) {
		//跨域请求不过暂时没用
		if (type == "jsonp") {
			$.ajax({
				type: "get",
				url: url,
				data: data,
				dataType: type,
				jsonp: "callbackparam", //传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(默认为:callback)
				jsonpCallback: success //自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名
			});
		} else {
			$.ajax({
				type: type,
				url: url,
				data: data,
				async: async,
				dataType: 'json',
				success: success
			});
		}
	}
});