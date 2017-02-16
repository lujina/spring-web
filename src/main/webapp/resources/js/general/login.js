define(function(require,exports,module) {
	require("../../plugins/ajaxform/jquery.form.js");
	require("../../bootstrap/js/bootstrap.min.js");
	require("../../plugins/iCheck/icheck.min.js");

	function renderDom(){
		$('#remember_me').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
	}
	function formEvent(){
		$('#login').ajaxForm({
        	success: function(data) {
        		if(data.status==0){
        			window.location.href='home';
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
		renderDom();
        formEvent();
	};

});