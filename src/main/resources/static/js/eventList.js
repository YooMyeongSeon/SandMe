	var view = {
			tab:function(ts, status) {
				$("#tabList").children("li").removeClass("active");
				$(ts).parent("li").addClass("active");
				$("input:hidden[name=eventState]").val(status);
				view.search();
			},
			view: function(no) {
				var query = $("#searchForm").serialize();
				location.href = "./eventView?eventIdx="+no+"&query=" + encodeURIComponent(query);
			},
			search: function() {
				$("input:hidden[name=page]").val(1);
				$("#searchForm")[0].action = "#tabList";
				$("#searchForm")[0].submit();
				return true;
			},
			more: function(ts) {
				$.showMask();
				var paramObj = new Object();
				paramObj.page = Number($("input:hidden[name=page]").val()) + 1;
				paramObj.eventState = $("input:hidden[name=eventState]").val();

				$.post("./ajaxEventList", "POST", JSON.stringify(paramObj), function(data) {
					$("input:hidden[name=page]").val(paramObj.page);
					if (data != null && data.length > 0) {
						var item = {};
						item["data"] = data;
						$("#eventTemplate").tmpl(item).appendTo("#eventListContent");
						if(paramObj.page >= $(ts).data("listcount")) {
							$(ts).hide();
						} else {
							$(ts).show();
						}
						$.hideMask();
					} else {
						$.hideMask("泥섎━媛� �ㅽ뙣�섏뿀�듬땲��.");
					}
				}, function(message) {
					$.hideMask("泥섎━媛� �ㅽ뙣�섏뿀�듬땲��./nErr:" + message);
				});
			}
	}
	