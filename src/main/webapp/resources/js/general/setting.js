define(function(require,exports,module) {
	require("../../plugins/iCheck/icheck.min.js");

	this.renderDom = function(){
		$('#remember_me').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
	}
	exports.init = function(){
		this.renderDom();
	};

});