         //全选
		var oall=document.getElementById("all");
		var oid=document.getElementsByName("ibId");
		oall.onclick=function(){
			for(var i=0;i<oid.length;i++){
				//所有的选择框和全选一致
				oid[i].checked=oall.checked;		
			}
		};
		//点击复选框
		for(var i=0;i<oid.length;i++){
			oid[i].onclick=function(){
				//判断是否全部选中,遍历集合
				for(var j=0;j<oid.length;j++){
				  if(oid[j].checked==false){
					oall.checked=false;
					break;
				  }else{
				    oall.checked=true;
				  }
				}
			};
		}
		//点击批量删除
		$("#delMore").click(function(){
			if($("input[name='ibId']:checked").length==0){
				alert("请至少选择一项");
				return false;
			}

			if(confirm('确定要删除所选数据吗?')){
				
			}else{
				return false;
			};
			var ids="";
			var n=0;
			for(var i=0;i<oid.length;i++){
				if(oid[i].checked==true){//选中为true
					var id=oid[i].value;
					if(n==0){
						ids+="ids="+id;
					}else{
						ids+="&ids="+id;
					}
					n++;
				}			
			}
            //上面会拼接出一个名为ids的数组ids=1&ids=2&ids=3&ids=4……
			$.get("delItemBank.action",ids,function(data){
				//存在中间表信息
				if(data=="Consider"){
					if(confirm('删除的数据中有数据关联到试卷,确定删除吗?')){
						$.get("deleteItemBank2.action",ids,function(data){
							if(data=="ok"){
								///alert("删除成功!");
								//删除成功后，调用action方法刷新页面信息
								location.href="list.action";
							}else{
								alert("删除失败!");
							}
						});
					}else{
						return false;
					};
					
				}else{//不存在中间表信息，直接删除所选试题
					$.get("deleteItemBank.action",ids,function(data){
						if(data=="ok"){
						//	alert("删除成功!");
							//删除成功后，调用action方法刷新页面信息
							location.href="list.action";
						}else{
							alert("删除失败!");
						}
					});
				}
			});
		});
		//点击批量删除
		function deleteOne(id){
			if(confirm('确定要删除此记录吗?')){
				
			}else{
				return false;
			};
			var ids="";
			ids+="ids="+id;
			//
			$.get("delItemBank.action",ids,function(data){
				//存在中间表信息
				if(data=="Consider"){
					if(confirm('删除的数据中有数据关联到试卷,确定删除吗?')){
						$.get("deleteItemBank2.action",ids,function(data){
							if(data=="ok"){
								//alert("删除成功!");
								//删除成功后，调用action方法刷新页面信息
								location.href="list.action";
							}else{
								alert("删除失败!");
							}
						});
					}else{
						return false;
					};
					
				}else{//不存在中间表信息，直接删除所选试题
					$.get("deleteItemBank.action",ids,function(data){
						if(data=="ok"){
							//alert("删除成功!");
							//删除成功后，调用action方法刷新页面信息
							location.href="list.action";
						}else{
							alert("删除失败!");
						}
					});
				}
			});
		};
