// 运营后台 - 广告投放系统 updata: 2015-12-08;
(function(window, $){
	//文件上传
	$(".up-btn").each(function(){
		var thas = $(this);
		thas.children().uploadify({
　　		uploader: root + '/oss/uploadFiles.do',  
			swf: root + '/res/uploadify/uploadify.swf',  
			queueID: 'null', // 上传进度列表;
			fileTypeDesc: "jpg",
			fileTypeExts: '*.jpg;*.png', //控制可上传文件的扩展名，启用本项时需同时声明fileTypeDesc
			multi: false,
			wmode: "transparent",
			buttonText: '选择图片',
			width: "100%",
			height: "100%",
			onUploadStart: function(){
				var id = this.button.parents(".up-btn").data("id");
				$("#" + id + "-pic").html('');
			},
			onUploadSuccess: function(file,data,response) {
				//上传完成时触发（每个文件触发一次）;
				//console.log([file,data,response]);
				var data = JSON.parse(data), id = this.button.parents(".up-btn").data("id");
				if(!data.success){
					 Toast.show(data.msg);
					 $("#" + id + "-pic").html('<img src="'+ root +'/res/images/photo-default.png"/>');
				}else{
					var img = new Image();
					img.src = "http://bgo.oss-cn-hangzhou.aliyuncs.com/" + data.fileName;
					// 不校验尺寸;
					//$("#" + id + "-pic").html('<img src="http://bgo.oss-cn-hangzhou.aliyuncs.com/'+ data.fileName +'"/>');
					//$("#" + id + "-hidden").val(data.fileName);
					// 校验尺寸;
					$(img).load(function(){
						var size = thas.closest('.control-group').find('.js-pic-size').data('size');
						size = size && size.split('x') || MA.have.config.picSize;
						if(size[0] != this.width || size[1] != this.height){
							Toast.show("请上传指定尺寸图片：" + size.join("x"));
							$("#" + id + "-pic").html('<img src="'+ root +'/res/images/photo-default.png"/>');
						}else{
							$("#" + id + "-pic").html('<img src="http://bgo.oss-cn-hangzhou.aliyuncs.com/'+ data.fileName +'"/>');
							$("#" + id + "-hidden").val(data.fileName);
						}
					});
				}
			}
		}); 
	});
	
	// 投放系统;
	MA.have = {
		config: {
			modal: ["", "#js-have-home", "#js-have-home", "#js-have-home", "#js-have-gold", "#js-have-diamond", "#js-have-category", "#js-have-diamond"],  // 1闪屏广告 2头部广告 3ICON广告区域 4黄金区域 5钻石区域 6类目区域 7底部区域;
			week : ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
			nolink: [1, 6, 7]
		},
		info: {},
		init: function(op){
			var self = this, o = self.info, c = self.config;
			$.extend(c, op);
			
			
			o.header = $(".js-have-header");
			o.title = $(".js-have-title");
			o.prev = $(".js-have-prev");
			o.next = $(".js-have-next");
			o.table = $("#js-have-table");
			o.under = $(".js-have-under");
			o.by = $(".js-have-by");
			o.edit = $(".js-have-edit");
			
			
			self.upDate();
			self.onEvent();
			
			
		},
		onEvent: function(){
			var self = this, o = self.info, c = self.config;
			// 前7天;
			o.prev.on("click", function(){
				var _date = new Date(c.beginDate);
				var end = new Date( _date.setDate( _date.getDate() - 1 ) ).format("yyyy-MM-dd");
				var begin = new Date( _date.setDate( _date.getDate() - 6 ) ).format("yyyy-MM-dd");
				window.location.href = root + "/homeBanner/getBannerSchedule.do?beginDate=" + begin + "&endDate=" + end;
			});
			// 后7天;
			o.next.on("click", function(){
				var _date = new Date(c.endDate);
				var begin = new Date( _date.setDate( _date.getDate() + 1 ) ).format("yyyy-MM-dd");
				var end = new Date( _date.setDate( _date.getDate() + 6 ) ).format("yyyy-MM-dd");
				window.location.href = root + "/homeBanner/getBannerSchedule.do?beginDate=" + begin + "&endDate=" + end;
			});
			// 每天广告位;
			o.table.find("a").on("click", function(){
				self.show(this);
			});
			// 编辑;
			o.edit.on("click", function(){
				self.edit();
			});
			// 通过审核;
			o.by.on("click", function(){
				self.bannerAudit(1);
			});
			// 拒绝、撤销、下线广告;
			o.under.on("click", function(){
				self.bannerAudit(0);
			});
			
		},
		bannerAudit: function(choice){
			var self = this, o = self.info, c = self.config;
			var data = {id: c.json.id, status: c.json.status, choice: choice, cellDate:c.json.cellDate};
			if(c.json.status == 2 && choice == 0){
				var refuseReason = o.modal.find(".js-under-content");
				if(isForm.isTrim(refuseReason.val())){
					refuseReason.focus();
					Toast.show("拒绝理由不能为空");
					return;
				}
				data.refuseReason = refuseReason.val();
			}
			Matrix.JSON({
				showLoad: true,
				type: "POST",
				url: root + "/homeBanner/bannerAudit.do",
				val: data,
				fun: function(res){
					Toast.show(res.msg);
					if(res.success){
						setTimeout(function(){window.location.reload();},1500);
					}
				}
			});
		},
		upDate: function(){
			var self = this, o = self.info, c = self.config;
			// 计算时间;
			if(!c.beginDate && !c.endDate){
				var _date = c.today == "" ? new Date() : new Date(c.today);
				c.beginDate = new Date( _date.setDate( _date.getDate() - 2 ) ).format("yyyy-MM-dd");
				c.endDate = new Date( _date.setDate( _date.getDate() + 6 ) ).format("yyyy-MM-dd");
			}
			var arr = ['<td width="200"></td>'];
			for(var i = 0; i < 7; i++){
				var d = new Date(c.beginDate);
				d = new Date(d.setDate( d.getDate() + i ))
				arr.push('<td>'+ d.format('MM月dd日') + '<i>'+ c.week[d.format("Z")] +'</i>' +'</td>');
			}
			o.header.html(arr.join(''));
			o.title.html(new Date(c.beginDate).format("MM月dd日") + " - " + new Date(c.endDate).format("MM月dd日"));
		},
		show: function(obj){
			var self = this, o = self.info, c = self.config;
			var thas = $(obj), prev = thas.parent();
			c.json = prev.data("json");
			//console.log(c.json);
			if(!c.json.role) return; // 0或空跳出;
			o.modal = $(c.modal[c.json.type]);
			// 初始化表单;
			o.modal.find("input[type=text],input[type=hidden],select,textarea").val("").removeAttr("disabled");
			// title;
			o.modal.find(".modal-header h3").html(c.json.title);
			o.modal.modal();
			// 取消;
			o.modal.off("hide").on("hide", function(){
				self.hide();
			});
			// 默认区显示;
			var def = o.modal.find(".js-default-block"), defbtn = o.modal.find(".js-is-default");
			defbtn.data("is", c.json.isDefault);
			defbtn.off("click").on("click", function(){
				var thas = $(this);
				//console.log(c.json.role);
				if(thas.data("is") == 1){
					thas.data("is", 0);
					def.hide();
				}else if(thas.data("is") == 0){
					thas.data("is", 1);
					def.show();
				}
				// 加载已添加的默认广告数据;
				//console.log(c.json.role, c.json.isDefault);
				if(!c.isLoadDefault && c.json.isDefault == 1){
					self.showDefault();
				}
			});
			defbtn.trigger("click");
			var linkType = o.modal.find("select[name=linkType]"), // 落地类型;
			linkTo = o.modal.find("input[name=linkTo]"), // 落地id;
			beansBlock = o.modal.find(".js-beans-block");
			// 黄金坑位选择妈豆商品;
			if(c.json.type == 4){
				c.isBeans = false;
				linkType.off("change").on("change", function(){
					var thas = $(this), index = linkType.index(this);
					// 无、类目主页;
					if(c.nolink.getIndex(thas.val()) != -1){
						linkTo.eq(index).val("").attr("disabled", true);
					}else{
						linkTo.eq(index).val("").removeAttr("disabled");
					}
					// 妈豆商品;
					if(thas.val() == 5){
						beansBlock.eq(index).show().find("table tbody").html('<tr><td colspan="6">请填写妈豆商品</td></tr>');
						//console.log(beansBlock.eq(index));
						linkTo.eq(index).val("");
						c.isBeans = true;
						//妈豆商品需要上传老版本图片
						o.modal.find(".form-arr-item").eq(index).find(".js-oldPic").show();
						o.modal.find(".form-arr-item").eq(index).find(".js-monthlyBuyPic").show();
					}else{
						beansBlock.eq(index).hide();
						c.isBeans = false;
						o.modal.find(".form-arr-item").eq(index).find(".js-oldPic").hide();
						o.modal.find(".form-arr-item").eq(index).find(".js-monthlyBuyPic").hide();
					}
					
				});
				linkTo.off("blur").on("blur", function(){
					var thas = $(this), index = linkTo.index(this);
					if(c.isBeans && (isForm.isTrim(thas.val()) || !isForm.isImp(thas.val()))){
						return Toast.show("请输入正确的妈豆商品ID");
					}else if(c.isBeans){
						self.getBeans($.trim(thas.val()), beansBlock.eq(index));
					}
				});
			}else{
				if(c.json.type == 2){
					o.modal.find(".js-oldPic").show();
				}
				linkType.off("change").on("change", function(){
					var thas = $(this), index = linkType.index(this);
					// 无、类目主页;
					if(c.nolink.getIndex(thas.val()) != -1){
						linkTo.eq(index).val("").attr("disabled", true);
					}else{
						linkTo.eq(index).val("").removeAttr("disabled");
					}
				});
			}
			console.log(c.json);
			switch(Number(c.json.type)){
				case 1:
					// 1闪屏广告
					c.nameSize = 20;
					c.picSize = [1000, 1334];
					self.startHome();
					break;
				case 2:
					// 2头部广告;
					c.nameSize = 20;
					c.picSize = [750, 300];
					self.startHome();
					break;
				case 3:
					// 3ICON广告区域;
					c.nameSize = 4;
					c.picSize = [129, 129];
					self.startHome();
					break;
				case 4:
					// 4黄金区域;
					c.nameSize = 20;
					c.picSize = c.json.weight == 1 ? [519, 519] : [546, 252];
					self.startCategory();
					break;
				case 5:
					// 5钻石区域;
					c.nameSize = 20;
					c.picSize = [750, 200];
					self.startDiamond();
					break;
				case 6:
					// 6类目区域;
					c.nameSize = 20;
					c.picSize = [250, 250];
					self.startCategory();
					break;
				case 7:
					// 7底部区域;
					c.nameSize = 20;
					c.picSize = [750, 200];
					self.startDiamond();
					break;
			}
		},
		hide: function(){
			var self = this, o = self.info, c = self.config;
			c.isLoadDefault = false;
		},
		get: function(op){
			var self = this, o = self.info, c = self.config;
			Matrix.JSON({
				showLoad: true,
				type: "POST",
				url: root + "/homeBanner/getBanner.do",
				val: op.data,
				fun: function(res){
					//console.log(res);
					if(res.success){
						op.callback && op.callback.call(op.callback, res);
					}else{
						Toast.show(res.msg);
					}
					// console.log(res);
				}
			});
		},
		save: function(op){
			var self = this, o = self.info, c = self.config;
			Matrix.JSON({
				showLoad: true,
				type: "POST",
				url: root + "/homeBanner/editBanner.do",
				val: op.data,
				fun: function(res){
					console.log(res);
					Toast.show(res.msg);
					op.callback && op.callback.call(op.callback, res);
				}
			});
		},
		// 编辑;
		edit: function(){
			var self = this, o = self.info, c = self.config;
			o.modal.find(".modal-footer").children().hide();
			o.modal.find(".modal-footer .status-2").show(); // 显示操作按钮;
			o.modal.find(".form-horizontal :input").removeAttr("disabled");
			o.modal.find(".form-horizontal .up-btn").show();
			// 修改是判断是否需要填写落地页内容;
			var linkType = o.modal.find("select[name=linkType]");
			linkType.each(function(){
				var thas = $(this);
				if(c.nolink.getIndex(thas.val()) != -1){
					thas.parents(".form-arr-item").find("input[name=linkTo]").attr("disabled", true);
				}
			});
			
		},
		// 初始化;
		start: function(){
			var self = this, o = self.info, c = self.config;
			o.modal.find("input[name=name]").attr("maxlength", c.nameSize).attr("placeholder", "最多不超过"+ c.nameSize +"字");
			o.modal.find(".modal-footer").children().hide();
			o.modal.find(".modal-footer .status-" + c.json.role).show(); // 显示操作按钮;
			o.modal.find(".up-pic img").attr("src", root + "/res/images/photo-default.png");
			o.modal.find(".js-beans-block").hide();
			o.modal.find(".js-oldPic .up-pic img").attr("src", root + "/res/images/photo-default.png");
			console.log(c.json.type)
			if(c.json.type!=2){
				o.modal.find(".js-oldPic").hide();
			}else{
				console.log(1)
				o.modal.find(".js-oldPic").show();
			}
			$.each(o.modal.find(".js-pic-size"),function(){
					var _this = $(this),size_str = "(" + (_this.data('size') || c.picSize.join("x")) + ")";
					_this.html(size_str);
			});
			
			if(c.json.role == 1 || c.json.role == 4 || c.json.role == 5 || c.json.role == 6){
				o.modal.find(".form-horizontal :input").attr("disabled", true);
				o.modal.find(".form-horizontal .up-btn").hide();
			}else if(c.json.role == 2 || c.json.role == 3){
				o.modal.find(".form-horizontal :input").removeAttr("disabled");
				o.modal.find(".form-horizontal .up-btn").show();
				o.modal.find("input[name=linkTo]").attr("disabled", true); // 默认为无;
			}
			// 默认填充开始时间;
			if(c.json.role == 2){
				o.modal.find(".form-horizontal input[name=beginTime]").val(c.json.cellDate);
				o.modal.find(".form-horizontal input[name=endTime]").val(c.json.cellDate);
			}
		},
		// 首页banner;
		startHome: function(){
			var self = this, o = self.info, c = self.config;
			self.start(); // 初始化设置;
			if(c.json.role != 2){
				self.get({
					data: {bannerId: c.json.bannerId, id: c.json.id, isDefault: 0},
					callback: function(res){
						//console.log(res);
						self.assData(res.data);
					}
				});
			}
		},
		// 首页banner;
		saveHome: function(){
			var self = this, o = self.info, c = self.config;
			var data = self.isForm(); // 通用验证;
			if(!data) return;
			if(c.json.id) data.id = c.json.id; // 广告id;
			console.log(data);
			self.save({
				data: {banners: JSON.stringify([data])},
				callback: function(res){
					if(res.success){
						setTimeout(function(){window.location.reload();},1500);
					}
				}
			});
			//var val = o.modal.find(".form-horizontal").serialize();
			//console.log(val);
		},
		// 钻石区域;
		startDiamond: function(){
			var self = this, o = self.info, c = self.config;
			var radio = o.modal.find(".form-horizontal input[name=isAges]"),
			ages = o.modal.find(".js-ages-list");
			self.start(); // 初始化设置;
			ages.hide();
			radio.removeAttr("checked").eq(0).attr("checked", true);
			if(c.json.role != 2){
				self.get({
					data: {bannerId: c.json.bannerId, id: c.json.id, isDefault: 0},
					callback: function(res){
						var data = res.data;
						self.assData(data);
						// 人群定向;
						if(data.applyAges.length == 1 && data.applyAges[0] == -2){
							//radio.eq(0).trigger("click");
							radio.eq(0).attr("checked", true);
						}else{
							//radio.eq(1).trigger("click");
							radio.eq(1).attr("checked", true);
							ages.show();
							ages.find("input[type=checkbox]").each(function(i, e){
								var thas = $(this), val = thas.val();
								if(data.applyAges.getIndex(val) == -1){
									thas.removeAttr("checked");
								}else{
									thas.attr("checked", true);
								}
							});
						}
					}
				});
			}
			// 人群定向;
			radio.off("click").on("click", function(){
				var thas = $(this);
				//console.log(thas.val());
				if(thas.val() == 1){
					ages.show();
				}else{
					ages.hide();
				}
			});
		},
		saveDiamond: function(){
			var self = this, o = self.info, c = self.config;
			var data = self.isForm(); // 通用验证;
			if(!data) return;
			// 单独验证;
			var radio = o.modal.find(".form-horizontal input[name=isAges]:checked");
			data.applyAges = [];
			//console.log(data.applyAges);
			if(radio.val() == 1){
				var chec = o.modal.find(".form-horizontal .js-ages-list :checked");
				if(chec.length){
					chec.each(function(i, e){
						data.applyAges.push($(e).val());
					});
				}else{
					Toast.show("请选择人群定向");
					return;
				}
			}else{
				data.applyAges.push(-2);
			}
			if(!data) return;
			if(c.json.id) data.id = c.json.id; // 广告id;
			self.save({
				data: {banners: JSON.stringify([data])},
				callback: function(res){
					if(res.success){
						setTimeout(function(){window.location.reload();},1500);
					}
				}
			});
			
			//var val = o.modal.find(".form-horizontal").serialize();
			//console.log(val);
		},
		// 类目广告;
		startCategory: function(){
			var self = this, o = self.info, c = self.config;
			self.start(); // 初始化设置;
			if(c.json.role != 2){
				self.get({
					data: {bannerId: c.json.bannerId, id: c.json.id, isDefault: 0},
					callback: function(res){
						var form = o.modal.find(".form-arr-item"), data = res.data;
						self.assData(data);
						form.eq(0).find("input[name=title]").val(data.title);
						form.eq(0).find("input[name=subTitle]").val(data.subTitle);
						// 妈豆商品;
						if(c.json.type == 4 && data.linkType == 5){
							var beansBlock = form.eq(0).find(".js-beans-block");
							c.isBeans = true;
							beansBlock.show().data("success", true);
							beansBlock.find("input[name=limitBuy]").val(data.limitBuy);
							beansBlock.find("input[name=buyBeginTime]").val(data.buyBeginTime);
							beansBlock.find("input[name=buyEndTime]").val(data.buyEndTime);
							beansBlock.find("table tbody").html(self.retBeans(data.items));
							if(c.json.role != 2 && c.json.role != 3){
								beansBlock.find("input[type=text]").attr("disabled", true);
							}
						}
					}
				});
			}
		},
		saveCategory: function(){
			var self = this, o = self.info, c = self.config;
			var form = o.modal.find(".form-arr-item"), dataArr = [];
			var data = self.isForm(form.eq(0)); // 通用验证;
			var linkType = o.modal.find("select[name=linkType]"), // 落地类型;
			linkTo = o.modal.find("input[name=linkTo]"), // 落地id;
			beansBlock = o.modal.find(".js-beans-block");
			if(!data) return;
			// 单独验证 - 标题;
			var obj = {
				title: form.eq(0).find("input[name=title]"),
				subTitle: form.eq(0).find("input[name=subTitle]")
			};
			if(isForm.isTrim(obj.title.val())){
				Toast.show(obj.title.data("toast") + "不能为空");
				return false;
			}else if(isForm.isTrim(obj.subTitle.val())){
				Toast.show(obj.subTitle.data("toast") + "不能为空");
				return false;
			}
			// 妈豆商品;
			if(c.json.type == 4 && data.linkType == 5){
				obj.table = beansBlock.eq(0);
				obj.limitBuy = form.eq(0).find("input[name=limitBuy]");
				obj.buyBeginTime = form.eq(0).find("input[name=buyBeginTime]");
				obj.buyEndTime = form.eq(0).find("input[name=buyEndTime]");
				if(isForm.isTrim(obj.limitBuy.val())){
					Toast.show(obj.limitBuy.data("toast") + "不能为空");
					return false;
				}else if(isForm.isTrim(obj.buyBeginTime.val())){
					Toast.show(obj.buyBeginTime.data("toast") + "不能为空");
					return false;
				}else if(isForm.isTrim(obj.buyEndTime.val())){
					Toast.show(obj.buyEndTime.data("toast") + "不能为空");
					return false;
				}else if(new Date("2015/01/01 " + obj.buyBeginTime.val()) > new Date("2015/01/01 " + obj.buyEndTime.val())){
					Toast.show("开始时间不能大于结束时间!");
					return false;
				}else if(!obj.table.data("success")){
					Toast.show("请填写正确的妈豆商品!");
					return false;
				}
				var i = 0, bin = obj.table.find("table :input"), l = bin.length;
				for(; i < l; i++){
					if(isForm.isTrim(bin.eq(i).val()) || !isForm.isNUM(bin.eq(i).val())){
						bin.eq(i).focus();
						return Toast.show("请输入正确值");
					}
				}
				// 妈豆商品数据;
				data.items = [];
				var tr = obj.table.find("table tbody tr"), i = 0, l = tr.length;
				for(; i < l; i++){
					data.items.push({
						itemNumId: tr.eq(i).data("itemnumid"),
						itemId: tr.eq(i).data("itemid"),
						colorName: tr.eq(i).data("color"),
						sizeName: tr.eq(i).data("size"),
						beans: tr.eq(i).find("input[name=beans]").val(),
						money: tr.eq(i).find("input[name=money]").val(),
						stock: tr.eq(i).find("input[name=stock]").val()
					});
				}
				
				$.extend(data, {
					limitBuy: obj.limitBuy.val(),
					buyBeginTime: obj.buyBeginTime.val(),
					buyEndTime: obj.buyEndTime.val()
				});
				
			}
			
			
			
			data.title = obj.title.val();
			data.subTitle = obj.subTitle.val();
			data.isDefault = 0; // 设置为非默认广告;
			if(c.json.id) data.id = c.json.id; // 广告id;
			dataArr.push(data);
			if(o.modal.find(".js-is-default").data("is")){
				// 拼装默认广告值;
				var data = self.isForm(form.eq(1)); // 通用验证;
				if(!data) return;
				// 单独验证 - 标题;
				var obj = {
					title: form.eq(1).find("input[name=title]"),
					subTitle: form.eq(1).find("input[name=subTitle]")
				};
				if(isForm.isTrim(obj.title.val())){
					Toast.show(obj.title.data("toast") + "不能为空");
					return false;
				}else if(isForm.isTrim(obj.subTitle.val())){
					Toast.show(obj.subTitle.data("toast") + "不能为空");
					return false;
				}
				// 默认广告妈豆商品;
				if(c.json.type == 4 && data.linkType == 5){
					obj.table = beansBlock.eq(1);
					obj.limitBuy = form.eq(1).find("input[name=limitBuy]");
					obj.buyBeginTime = form.eq(1).find("input[name=buyBeginTime]");
					obj.buyEndTime = form.eq(1).find("input[name=buyEndTime]");
					if(isForm.isTrim(obj.limitBuy.val())){
						Toast.show(obj.limitBuy.data("toast") + "不能为空");
						return false;
					}else if(isForm.isTrim(obj.buyBeginTime.val())){
						Toast.show(obj.buyBeginTime.data("toast") + "不能为空");
						return false;
					}else if(isForm.isTrim(obj.buyEndTime.val())){
						Toast.show(obj.buyEndTime.data("toast") + "不能为空");
						return false;
					}else if(new Date("2015/01/01 " + obj.buyBeginTime.val()) > new Date("2015/01/01 " + obj.buyEndTime.val())){
						Toast.show("开始时间不能大于结束时间!");
						return false;
					}else if(!obj.table.data("success")){
						Toast.show("请填写正确的妈豆商品!");
						return false;
					}
					var i = 0, bin = obj.table.find("table :input"), l = bin.length;
					for(; i < l; i++){
						if(isForm.isTrim(bin.eq(i).val()) || !isForm.isNUM(bin.eq(i).val())){
							bin.eq(i).focus();
							return Toast.show("请输入正确值");
						}
					}
					// 妈豆商品数据;
					data.items = [];
					var tr = obj.table.find("table tbody tr"), i = 0, l = tr.length;
					for(; i < l; i++){
						data.items.push({
							itemNumId: tr.eq(i).data("itemnumid"),
							itemId: tr.eq(i).data("itemid"),
							colorName: tr.eq(i).data("color"),
							sizeName: tr.eq(i).data("size"),
							beans: tr.eq(i).find("input[name=beans]").val(),
							money: tr.eq(i).find("input[name=money]").val(),
							stock: tr.eq(i).find("input[name=stock]").val()
						});
					}
					
					$.extend(data, {
						limitBuy: obj.limitBuy.val(),
						buyBeginTime: obj.buyBeginTime.val(),
						buyEndTime: obj.buyEndTime.val()
					});
					
				}
				data.title = obj.title.val();
				data.subTitle = obj.subTitle.val();
				data.isDefault = 1; // 设置为默认广告;
				var id = form.eq(1).data("id");
				if(id) data.id = id; // 默认广告id;
				dataArr.push(data);
			}else if(c.json.isDefault == 0){
				return Toast.show("请填写默认广告内容!");
			}
			console.log(dataArr);
//			return;
			self.save({
				data: {banners: JSON.stringify(dataArr)},
				callback: function(res){
					if(res.success){
						setTimeout(function(){window.location.reload();},1500);
					}
				}
			});
		},
		// 加载默认广告数据;
		showDefault: function(){
			var self = this, o = self.info, c = self.config;
			c.isLoadDefault = true;
			self.get({
				data: {bannerId: c.json.bannerId, id: c.json.id, isDefault: 1},
				callback: function(res){
					//console.log(res);
					var form = o.modal.find(".form-arr-item").eq(1)
					self.assData(res.data, form);
					form.find("input[name=title]").val(res.data.title);
					form.find("input[name=subTitle]").val(res.data.subTitle);
					form.data("id", res.data.id); // 默认广告ID;
					// 妈豆商品;
					if(c.json.type == 4 && res.data.linkType == 5){
						var beansBlock = form.find(".js-beans-block");
						c.isBeans = true;
						beansBlock.show().data("success", true);
						beansBlock.find("input[name=limitBuy]").val(res.data.limitBuy);
						beansBlock.find("input[name=buyBeginTime]").val(res.data.buyBeginTime);
						beansBlock.find("input[name=buyEndTime]").val(res.data.buyEndTime);
						beansBlock.find("table tbody").html(self.retBeans(res.data.items));
						if(c.json.role != 2 && c.json.role != 3){
							beansBlock.find("input[type=text]").attr("disabled", true);
						}
					}
				}
			});
		},
		// 获取妈豆商品数据;
		getBeans: function(id, block){
			var self = this, o = self.info, c = self.config;
			var table = block.find("table tbody");
			table.html('<tr><td colspan="6">数据加载中</td></tr>');
			Matrix.JSON({
				showLoad: true,
				type: "POST",
				url: root + "/homeBanner/getMbeanGoods.do",
				val: {id: c.json.id, linkTo: id},
				fun: function(res){
					if(res.success){
						if(res.data.length){
							table.html(self.retBeans(res.data));
							block.data("success", true);
						}else{
							block.data("success", false);
							table.html('<tr><td colspan="6">暂无相关数据</td></tr>');

						}
					}else{
						block.data("success", false);
						Toast.show(res.msg);
					}
					//console.log(res, block);
				}
			});
		},
		retBeans: function(data){
			var i = 0, d = data, l = d.length, arr = [];
			for(; i < l; i++){
				arr.push('<tr data-itemnumid="'+ d[i].itemNumId +'" data-itemid="'+ d[i].itemId +'" data-color="'+ (d[i].colorName || "") +'" data-size="'+ (d[i].sizeName || "") +'"><td>'+ (d[i].colorName ? d[i].colorName : "/") +'</td><td>'+ (d[i].sizeName ? d[i].sizeName : "/") +'</td><td>'+ d[i].itemId +'</td><td>/</td><td>');
				arr.push('<input type="text" placeholder="妈豆" name="beans" value="'+ (d[i].beans || '') +'">妈豆 <input type="text" placeholder="元" name="money" value="'+ (d[i].money || '') +'">元 ');
				arr.push('</td><td><input type="text" placeholder="可用库存" name="stock" value="'+ (d[i].stock || "") +'"></td></tr>');
			}
			return arr.join('');
		},
		isForm: function(from){
			var self = this, o = self.info, c = self.config;
			o.modal.find(":input").delBlank(); // 删除前后空格;
			var from = from ? from : o.modal.find(".form-horizontal");
			var obj = {
				name: from.find("input[name=name]"),
				pic: from.find("input[name=pic]"),
				oldPic:from.find(".js-oldPic:visible input[name=oldPic]"),//--------wml
				monthlyBuyPic:from.find(".js-monthlyBuyPic:visible input[name=monthlyBuyPic]"),
				linkType: from.find("select[name=linkType]"),
				linkTo: from.find("input[name=linkTo]"),
				beginTime: from.find("input[name=beginTime]"),
				endTime: from.find("input[name=endTime]")
			};
			// 测试跳过图片上传;
			if(Matrix.tools.search("debug")){
				obj.pic.val("6140d9e7-38f7-40aa-8745-e354de16749e.jpg");
			}
			if(isForm.isTrim(obj.name.val())){
				Toast.show(obj.name.data("toast") + "不能为空");
				return false;
			}else if(isForm.isTrim(obj.pic.val())){
				Toast.show(obj.pic.data("toast") + "不能为空");
				return false;
			}else if(obj.oldPic.length && isForm.isTrim(obj.oldPic.val())){
				Toast.show(obj.oldPic.data("toast") + "不能为空");//-------wml
				return false;
			}else if(obj.monthlyBuyPic.length && isForm.isTrim(obj.monthlyBuyPic.val())){
				Toast.show(obj.monthlyBuyPic.data("toast") + "不能为空");//-------wml
				return false;
			}else if((c.nolink.getIndex(obj.linkType.val()) == -1) && isForm.isTrim(obj.linkTo.val())){
				Toast.show(obj.linkTo.data("toast") + "不能为空");
				return false;
			}else if(obj.beginTime.length && isForm.isTrim(obj.beginTime.val())){
				Toast.show(obj.beginTime.data("toast") + "不能为空");
				return false;
			}else if(obj.endTime.length && isForm.isTrim(obj.endTime.val())){
				Toast.show(obj.endTime.data("toast") + "不能为空");
				return false;
			}else if(obj.beginTime.length && new Date(obj.beginTime.val()) > new Date(obj.endTime.val())){
				Toast.show("结束时间不能小于开始时间");
				return false;
			}
			console.log(c.json)
			return {
				name: obj.name.val(),
				pic: obj.pic.val(),
				//pic: "pic.jpg",
				oldPic:obj.oldPic.val(),
				monthlyBuyPic:obj.monthlyBuyPic.val(),
				linkType: obj.linkType.val(),
				linkTo: obj.linkTo.val(),
				beginTime: obj.beginTime.val(),
				endTime: obj.endTime.val(),
				bannerId: c.json.bannerId,
				cellDate:c.json.cellDate
			}
		},
		assData: function(data, from){
			var self = this, o = self.info, c = self.config;
			var from = from ? from : o.modal.find(".form-arr-item").eq(0);
			from.find("input[name=name]").val(data.name);
			from.find("input[name=pic]").val(data.pic);
			from.find(".up-pic img").attr("src", c.imgurl + data.pic);
			if(data.oldPic){
				from.find(".js-oldPic input[name=oldPic]").val(data.oldPic);
				from.find(".js-oldPic").show();
				from.find(".js-oldPic .up-pic img").attr("src", c.imgurl + data.oldPic);	
			}else{
				from.find(".js-oldPic").hide();
			}
			if(data.monthlyBuyPic){
				from.find(".js-monthlyBuyPic input[name=monthlyBuyPic]").val(data.monthlyBuyPic);
				from.find(".js-monthlyBuyPic").show();
				from.find(".js-monthlyBuyPic .up-pic img").attr("src", c.imgurl + data.monthlyBuyPic);	
			}else{
				from.find(".js-monthlyBuyPic").hide();
			}
			from.find("select[name=linkType]").val(data.linkType);
			// 落地类型;
			if(c.nolink.getIndex(data.linkType) != -1){
				from.find("input[name=linkTo]").attr("disabled", true);
			}
			from.find("input[name=linkTo]").val(data.linkTo);
			from.find("input[name=beginTime]").val(data.beginTime);
			from.find("input[name=endTime]").val(data.endTime);
			// 显示状态信息;
			if(data.refuseReason){
				var label = ' <span class="label label-warning">'+ (data.status == 4 ? "拒绝理由：" : "") + data.refuseReason +'</span>';
				o.modal.find(".modal-header h3").append(label);
			}
		}
	};
	
	// 资源位管理;
	MA.setHave = {
		info: {},
		init: function(){
			var self = this, o = self.info;
			o.block = $("#MA-have-leafs");
			self.onEvent();
		},
		onEvent: function(){
			var self = this, o = self.info;
			// 删除;
			o.block.on("click", ".js-leafs-del", function(){
				var thas = $(this), tr = thas.parents("tr"), tbody = thas.parents("table").children("tbody");;
				var data = {
					bannerType: thas.data("type")
				};
				if(data.bannerType == 4){
					data.batchId = thas.data("batchid");  // 黄金坑位值batchid;
					tr = thas.parents("table").children("tbody").find(".js-batchid-" + data.batchId);
				}else{
					data.bannerId = thas.data("bannerid"); // 非黄金坑位传bannerid;
				}
				// 删除空行;
				if(thas.data("new")){
					tr.remove();
					if(!tbody.children().length){
						tbody.html('<tr class="null"><td colspan="3">暂无相关内容</td></tr>');
					}
					return;
				}
				if(confirm("确定删除该条广告吗？")){
					self.del({
						data: data,
						callback: function(res){
							if(res.success){
								// 删除行;
								tr.remove();
								if(!tbody.children().length){
									tbody.html('<tr class="null"><td colspan="3">暂无相关内容</td></tr>');
								}
							}
						}
					});
				}
			});
			// 增加;
			o.block.on("click", ".js-leafs-new", function(){
				var thas = $(this), tbody = thas.parents("table").children("tbody");
				var type = thas.data("type");
				tbody.children(".null").remove();
				if(type == 4){
					var arr = [], rid = Math.floor(Math.random() * 10000), tr = '<td><input type="text" name="name" placeholder="位置名称" maxlength="20" /></td>';
					arr.push('<tr class="js-batchid-'+ rid +'">'+ tr +'<td rowspan="3" class="rows"><input type="text" name="orderBy" class="span5" placeholder="排序" value="" maxlength="2" /></td><td rowspan="3" class="rows"><a class="btn btn-danger btn-mini js-leafs-del" href="javascript:;" data-new="true" data-type="4" data-batchid="'+ rid +'">删除</a> </td></tr>');
					arr.push('<tr class="js-batchid-'+ rid +'">'+ tr +'</tr>');
					arr.push('<tr class="js-batchid-'+ rid +'">'+ tr +'</tr>');
					tbody.append(arr.join(''));
				}else{
					var arr = ['<tr><td><input type="text" name="name" placeholder="位置名称" maxlength="20" /></td><td><input type="text" name="orderBy" class="span5" placeholder="排序" maxlength="2" /></td><td>'];
					arr.push('<a class="btn btn-danger btn-mini js-leafs-del" href="javascript:;" data-new="true">删除</a>');
					arr.push('</td></tr>');
					tbody.append(arr.join(''));
				}
				//console.log(tbody);
			});
			// 保存;
			o.block.on("click", ".js-leafs-seve", function(){
				var thas = $(this), tbody = thas.parents("table").children("tbody");
				var inputs = tbody.find(":input"), tr = tbody.children();
				if(!inputs.length) Toast.show("请添加相关数据");
				data = {
					bannerType: thas.data("type"),
					bannerGroup: []
				};
				// 验证;
				inputs.delBlank();
				for(var i = 0; i < inputs.length; i++){
					var thas = inputs.eq(i);
					if(isForm.isTrim(thas.val())){
						thas.focus();
						return Toast.show("不能为空");
					}else if(isForm.isCheck(thas.val()) || (thas.attr("name") == "orderBy" && !isForm.isImp(thas.val()))){
						thas.focus();
						return Toast.show("请输入正确格式的内容");
					}
				}
				// 数据整理;
				var orderBy;
				for(var i = 0; i < tr.length; i++){
					var d = {
						bannerName: tr.eq(i).find("input[name=name]").val()
					};
					if(tr.eq(i).find("input[name=orderBy]").length){
						var newOrderBy = tr.eq(i).find("input[name=orderBy]").val();
						orderBy = newOrderBy;
					}
					d.orderBy = orderBy;
					//if(tr.eq(i).find("input[name=orderBy]").length){
					//	d.orderBy = tr.eq(i).find("input[name=orderBy]").val();
					//}
					//console.log(tr.eq(i).data("json"));
					$.extend(d, tr.eq(i).data("json"));
					//if(tr.eq(i).data("bannerid")){
					//	d.bannerId = tr.eq(i).data("bannerid");
					//}
					data.bannerGroup.push(d);
				}
				if(data.bannerType == 2 && data.bannerGroup.length > 5){
					return Toast.show("广告位不能大于5个");
				}else if(data.bannerType == 3 && (data.bannerGroup.length > 8 || data.bannerGroup.length < 3)){
					return Toast.show("广告位最少3个、最多8个");
				}else if(data.bannerType == 6 && data.bannerGroup.length > 9){
					return Toast.show("广告位最多9个");
				}
				// 判断排序是否有重复;
				var by = tbody.find("input[name=orderBy]"), len = by.length;
				for(var i = 0; i < len; i++){
					for(var j = i+1; j < len; j++){
						if(by.eq(i).val() == by.eq(j).val()) return Toast.show("排序不能重复");
					}
				}
				console.log(data);
				//return;
				self.edit({
					data: data,
					callback: function(res){
						if(res.success){
							setTimeout(function(){window.location.reload();},1500);
						}
					}
				});
			});
			// 上下线;
			o.block.on("click", ".js-leafs-onlie", function(){
				var thas = $(this);
				self.updateOnlie({
					data: {bannerType: thas.data("type"), online: thas.data("online")},
					callback: function(res){
						if(res.success){
							window.location.reload();
						}
					}
				});
			});
		},
		// 删除;
		del: function(op){
			Matrix.JSON({
				showLoad: true,
				type: "POST",
				url: root + "/homeBanner/deleteBannerSource.do",
				val: op.data,
				fun: function(res){
					Toast.show(res.msg);
					op.callback && op.callback.call(op.callback, res);
				}
			});
		},
		// 新建、修改;
		edit: function(op){
			var self = this, o = self.info;
			//console.log({bannerMgrData: JSON.stringify(op.data)});
			Matrix.JSON({
				showLoad: true,
				type: "POST",
				url: root + "/homeBanner/editBannerSource.do",
				val: {bannerMgrData: JSON.stringify(op.data)},
				fun: function(res){
					Toast.show(res.msg);
					op.callback && op.callback.call(op.callback, res);
				}
			});
		},
		// 上下线;
		updateOnlie: function(op){
			var self = this, o = self.info;
			Matrix.JSON({
				showLoad: true,
				type: "POST",
				url: root + "/homeBanner/updateOnlie.do",
				val: op.data,
				fun: function(res){
					Toast.show(res.msg);
					op.callback && op.callback.call(op.callback, res);
				}
			});
		}
	};
	
	
	
})(window, jQuery);