define(function(require,exports,module) {
	require("../../plugins/ajaxform/jquery.form.js");
	require("../../bootstrap/js/bootstrap.min.js");
	require("../../plugins/iCheck/icheck.min.js");
	require("../../plugins/validate/jquery.validate.min.js");

	function renderDom(){
		$('#remember_me').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
	}
	function formEvent(){
        //设置错误信息提示的位置
        jQuery.validator.setDefaults({
            errorPlacement: function(error, element) {
                if ($(element).next().hasClass('glyphicon')) {
                    error.insertAfter(element.next());
                } else {
                    error.insertAfter(element);
                }
            }
        });
        //信息验证
        $('#registe').validate({
            rules:{
                username: 'required',
                password: 'required',
                password_again: {
                    equalTo: '#password'
                }
            },
            messages:{
                username: '请输入用户名',
                password: '请输入密码',
                password_again: '密码不一致',
                email: '请输入合法的邮件地址'
            }
        });
		$('#registe').ajaxForm({
        	success: function(data) {
        		if(data.status==0){
        			window.location.href='home';
        		}else{
        			$('.alert').find('strong').html(data.msg);
        			$('#warning_block').prop('hidden',false);
        		}
				
			},
			error: function() {
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