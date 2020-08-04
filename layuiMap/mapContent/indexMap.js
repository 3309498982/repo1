//定义json，接收表格数据，使子页面获取到父页面的数据
var json;
layui.use('table', function(){
  var table = layui.table;
  //表格实例
  table.render({
    elem: '#demo'
    ,height: 'full-100'
    ,url: './mapContent/indexMap.json' //数据接口
    ,page: true //开启分页
    ,cols: [[ //表头
    	//开启排序:sort: true
    	//隐藏列：hide:true
      {field: 'id', title: 'ID', align:'center', width:100}
      ,{field: 'name', title: '数据名称', align:'center'}
      ,{field: 'name2', title: '数据名称', align:'center'}
      ,{field: 'name3', title: '数据名称', align:'center',hide:true} 
      ,{field: 'name4', title: '数据名称', align:'center',hide:true}
      ,{fixed: 'right', align:'center', toolbar: '#barDemo'}//自定义按钮
    ]]
  });
  
  //监听工具条
  table.on('tool(test)', function(obj){
  	var data = obj.data;
    if(obj.event === 'detail'){
      //layer.msg('ID：'+ data.id + ' 的查看操作');
      json = JSON.stringify(data);
      //打开弹出层
      layui.use('layer', function (){
          layer.open({
          title: '查看详情',
          maxmin: true,
          type: 2,
          content: './modal/indexMod.html',
          area: ['800px', '500px']
       });
      });
    }else if(obj.event === 'del'){
    	layer.confirm('真的删除行么', function(index){
    		obj.del();
    		layer.close(index);
    	});
    }else if(obj.event === 'gis'){
      //layer.alert('ID：'+ data.id + ' 定位')
      var longitude = data.name;//获取经度
      var latitude = data.name2;//获取纬度
      // 百度地图信息提示窗
			var sContent ="<div style='width:400px; height:200px; margin-top: 15px;'>";
				sContent +="<fieldset class='layui-elem-field layui-field-title'>";
				sContent +="<legend style='font-size:16px;'>"+data.id+"</legend>";
				sContent +="</fieldset>";
				sContent +="<ul class='layui-timeline' style='padding-top:10px;'>";
				sContent +="<li class='layui-timeline-item'>";
				sContent +="<i class='layui-icon layui-timeline-axis'></i>";
				sContent +="<div class='layui-timeline-content layui-text'>";
				sContent +="<p class='layui-timeline-title mapcolLar'>定位信息</p>";
				sContent +="<p class='mapcolLarVice'>经度信息："+data.name+"</p>";
				sContent +="<p class='mapcolLarVice'>纬度信息："+data.name2+"</p>";
				sContent +="</div>";
				sContent +="</li>";
				sContent +="</ul";
				sContent +="</div>";
				//点击详情进行定位
				if(longitude != "" && latitude != ""){
					map.clearOverlays();//清除地图覆盖物
					yueYang();//重新设置显示区域为岳阳市岳阳楼区
					var new_point = new BMap.Point(longitude,latitude);
					var marker = new BMap.Marker(new_point);//创建标注
					map.addOverlay(marker);//将标注添加到地图中
					map.panTo(new_point);
					var infoWindow = new BMap.InfoWindow(sContent); // 创建信息窗口对象
					map.openInfoWindow(infoWindow,new_point); //开启信息窗口
				}else{
					layer.alert('ID：'+ data.id + ' 定位失败，经纬度信息为空')
				}
      //百度地图定位 End
    }
  });
  
});
