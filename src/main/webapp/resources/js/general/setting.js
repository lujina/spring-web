define(function(require,exports,module) {
	require("../../plugins/ajaxform/jquery.form.js");
	require("../../plugins/validate/jquery.validate.min.js");
	require('../../plugins/cropper/cropper.js');
	var server = require("../common/server");
	//注意这里使用cropperjs(无使用jquery版)进行剪裁
	var userForm = function(){
		var userInfo = {};
		var infoForm = $('#personalInfo').children('form').eq(0);
		var editBtn = infoForm.find('button[name = edit]').eq(0);
		var saveBtn = infoForm.find('button[name = save]').eq(0);
		var cancelBtn = infoForm.find('button[name = cancel]').eq(0);
		var passwordBtn = infoForm.find('a[name = passwordBtn]').eq(0);
		function initFormEvent(){
			editBtn.off().on('click',function(event){
				infoForm.find('input[name = email]').eq(0).prop('readonly',false);
				saveBtn.show();
				cancelBtn.show();
				passwordBtn.show();
				editBtn.hide();
			});
			cancelBtn.off().on('click',function(event){
				validator.resetForm();
				resetForm();
			});
			passwordBtn.off().on('click',function(event){
				passwordBtn.parents('div').eq(0).append($('#resetPassword').html());
				passwordBtn.hide();
			})
			var validator = infoForm.validate({
			    rules:{
	                oldPassword: {
	                	validPassword: true
	                },
	                password: 'required',
	                password_again: {
	                    equalTo: '#password'
	                }
	            },
	            messages:{
	                oldPassword:'密码输入错误',
	                password: '请输入密码',
	                password_again: '密码不一致',
	                email: '请输入合法的邮件地址'
	            }
			});

			//自定义密码验证方法  
			jQuery.validator.addMethod("validPassword",function(value, element){  
		        var returnVal = false;
		        if(value == userInfo.password){  
		            returnVal = true;  
		        }  
		        return returnVal;  
		    },"密码输入错误");  

			infoForm.ajaxForm({
				beforeSubmit: function() {
					return infoForm.valid();
				},
				success: function(result) {
	        		if(result.status==0){
	        			userInfo = result.data;
						resetForm();
	        		}else{
	        			alert('修改失败');
	        		}
				},
				error: function() {
					alert('提交信息失败，请联系管理员');
				},
				setTimeout: 3000
			});
		}
		function resetForm(){
			editBtn.show();
			saveBtn.hide();
			cancelBtn.hide();
			passwordBtn.hide();
			infoForm.find('input[name = username]').eq(0).val(userInfo.username);
			infoForm.find('input[name = email]').eq(0).val(userInfo.email).prop('readonly',true);
			$('img[alt="User Image"]').attr('src',userInfo.userimg);
			$(".pr").remove();
		}
		this.getUserInfo = function(){
			return userInfo;
		}
		this.init = function(){
			server.server.user.queryInfo({},function(result){
				userInfo = result.data;
				$('#personalInfo').children('div').children('h3').html(userInfo.username);
				resetForm();
			},true,'get');
			initFormEvent();
		}
	}

	var cropperEvent = function(userForm){
		var cropper;
		var cropperContainer = $('#cropperContainer');
		var imgHandler = function(input,cropper){
			var originPhoto = input.files[0];
			if(originPhoto.type.split('/')[0] != 'image'){
				alert('不是图片');
				return;
			}
			cropper.originFileType = originPhoto.type; //暂存图片类型
	        cropper.originFileName = originPhoto.name; //暂存图片名称
	        var originPhotoURL = window.URL.createObjectURL(originPhoto);//创建一个新的URL对象
	        cropper.replace(originPhotoURL);				// 动态设置图片预览
	        cropperContainer.show();
		};
		var cropAndUpload = function(cropper){
			// 此处注意，生成的Canvas长宽比应与之前规定的裁剪比例一致
	    	// 否则生成的图片会有失真
	    	var size = {
	    		width:100,
	    		height:100
	    	};
	    	var userInfo = userForm.getUserInfo();
	    	var cropedCanvas = cropper.getCroppedCanvas(size);
	    	//var croppedCanvasUrl = croppedCanvas.toDataURL(originFileType); // Base64
	    	cropedCanvas.toBlob(function(blob){
	    		var formData = new FormData();
	    		formData.append('userImg',blob);
	    		formData.append('userId',userInfo.id);
		    	$.ajax('./back/user/uploadImg', {
				    method: "POST",
				    data: formData,
				    processData: false,
				    contentType: false,
				    success: function (res) {
				    	if (res.status == 0) {
				    		cropper.reset();
				    		this.init();
							$('img[alt="User Image"]').attr('src',res.data.fileFullUrl);
				    	}else{
				    		alert('上传失败');
				    	};
				    }.bind(this),
				    error: function () {
				    	alert('上传失败');
				    }
				 });
	    	}.bind(this),cropper.originFileType); //Base64 转Blob对象
		}

		this.init = function(){
			var image = document.getElementById('cropping');
			cropper = cropper || new Cropper(image,{
								        aspectRatio: 1 / 1,   			// 固定裁剪比例1:1，裁剪后的图片为正方形
								        viewMode: 1,					// 剪裁框应该在canvas里面
								   });
		    cropperContainer.hide();
		    $('#imgInp').replaceWith($('#imgInp').prop('outerHTML'));
			$('#imgInp').off().on('change',function(){
				imgHandler(this,cropper);
			});
			cropperContainer.children('button').eq(0).off().on('click',cropAndUpload.bind(this,cropper));
			cropperContainer.children('button').eq(1).off().on('click',this.init.bind(this));
		}
	
	}
	
	
	
	exports.init = function(){
		var uForm = new userForm();
		uForm.init();
		var cEvent = new cropperEvent(uForm);
		cEvent.init();
	};

});