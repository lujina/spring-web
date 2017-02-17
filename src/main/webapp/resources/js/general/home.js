define(function(require,exports,module) {
	// require("../../bootstrap/js/bootstrap.min.js");
	// require("../../js/app.min.js.js");
	//hashchange的注册回调
	function Router(){
		this.routes = {};
		this.curUrl = '';

		this.route = function(path, callback){
			this.routes[path] = callback || function(){};
			return this;
		};
		
		this.refresh = function(){
			this.curUrl = location.hash.slice(1) || '/homepage';
			// $('li.active').removeClass('active');
			// $('ul.menu-open').removeClass('menu-open');
			// var activeLi=$('#' + this.curUrl.slice(1));
			// //渲染菜单
			// while(activeLi.length != 0){
			// 	$(activeLi).addClass('active');
			// 	$(activeLi).children('ul').addClass('menu-open');
			// 	activeLi = $(activeLi).parent('ul').parent('li');
			// }
			this.routes[this.curUrl]();
		};
		
		this.init = function(){
			//load事件必须等到网页中所有内容全部加载完毕之后才被执行
			window.addEventListener('load', this.refresh.bind(this), false);
            window.addEventListener('hashchange', this.refresh.bind(this), false);
		}

	};
	 //点击触发url的hash改变
    var R = new Router();
    R.init();
	exports.init = function(){
		R.route('/homepage',function(){
			$('#page-content').load('homepage.html');
		}).route('/mailbox',function(){
			$('#page-content').load('mailbox.html');
		});
	};

});