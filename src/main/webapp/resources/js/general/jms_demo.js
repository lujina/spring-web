define(function(require, exports, module){
	require("../../plugins/datatables/jquery.dataTables.js");
	require("../../plugins/ajaxform/jquery.form.js");
	var orderTable = function(){
		this.table = $('#order').DataTable({
				  ajax: {
				    url: "./back/order/status",
				    dataSrc: function ( res ) {
				      return res.data;
				    }
				  },
				  columns:[
			        {data:"id"},
			        {data:"productName"},
			        {data:"status"}
			  	  ],
			  	  "bPaginate": true,
		          "bLengthChange": false,
		          "bFilter": false,
		          "bSort": true,
		          "bInfo": true,
		          "bAutoWidth": false
		});
	};
	var userEvent = function(oTable){
		var orderModal = $('#productOrder');
		var orderForm = orderModal.find('form').eq(0); 
		orderForm.ajaxForm({
			success: function(result) {
	        		if(result.status==0){
	        			userInfo = result.data;
						alert('保存成功');
						oTable.table.ajax.reload();
	        		}else{
	        			alert('保存失败');
	        		}
			},
			error: function() {
				alert('提交信息失败，请联系管理员');
			},
			setTimeout: 3000
		});
		this.init = function(){
			orderModal.click(function(event){
				switch(event.target.name){
					case "save":   orderForm.submit();
					case "cancel": orderModal.modal("hide");
								   orderForm.resetForm();
								   break;
				};
			});
		};
	};
	exports.init = function(){
		var oTable = new orderTable();
		var uEvent = new userEvent(oTable);
		uEvent.init();
	};

});