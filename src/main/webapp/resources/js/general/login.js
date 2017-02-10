define(function(require,exports,module) {
	require("../../plugins/jQuery/jQuery-2.1.4.min.js");
	require("../../plugins/ajaxform/jquery.form.js");
	require("../../bootstrap/js/bootstrap.min.js");
	require("../../plugins/iCheck/icheck.min.js");

	exports.init = function(){
		$('input').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
        $('#login').ajaxform({
        	success: function(data) {
				if (data.errorNo == 0) {
					window.location.href="home";
				} else {
					alert("登陆失败");
				}
			},
			error: function() {
				alert("提交失败");
			},
			setTimeout: 3000
        });
	};

});