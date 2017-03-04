define(function(require,exports,module) {
	/*
	 *	注意该js的资源需要在html页面进行加载，因为在用户失去会话信息后会出现不能调用define的bug，原因暂时还不知道
	 */
	// require("../../plugins/ajaxform/jquery.form.js");
	// require("../../bootstrap/js/bootstrap.min.js");
	// require("../../plugins/iCheck/icheck.min.js");

	this.renderDom = function(){
		$('#remember_me').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
	}
	this.formEvent = function(){
		$('#login').ajaxForm({
        	success: function(data) {
        		if(data.status==0){
        			window.location.href='index';
        		}else{
        			$('.alert').find('strong').html(data.msg);
        			$('#warning_block').prop('hidden',false);
        		}
				
			},
			error: function(data) {
				$('.alert').find('strong').html('提交信息失败，请联系管理员');
				$('#warning_block').prop('hidden',false);
			},
			setTimeout: 3000
        });
	}
	exports.init = function(){
		this.renderDom();
        this.formEvent();
	};

});