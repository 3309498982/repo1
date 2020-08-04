//创建和初始化地图函数：
function initMap(){
    createMap();//创建地图
    setMapEvent();//设置地图事件
    addMapControl();//向地图添加控件
    yueYang();
};
//创建地图函数：
//默认显示3D地图：mapType: BMAP_HYBRID_MAP
//禁止点击底图：enableMapClick: false
function createMap(){
    var map = new BMap.Map("dituContent",{enableMapClick: false,minZoom : 5});//在百度地图容器中创建一个地图
    var point = new BMap.Point(113.201603,29.43165);//定义一个中心点坐标
    map.centerAndZoom(point,13);//设定地图的中心点和坐标并将地图显示在地图容器中
    window.map = map;//将map变量存储在全局
};

//地图事件设置函数：
function setMapEvent(){
    map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
    map.enableScrollWheelZoom();//启用地图滚轮放大缩小
    map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
    map.enableKeyboard();//启用键盘上下左右键移动地图
};

//地图控件添加函数：
function addMapControl(){
	//向地图中添加缩放控件
	//var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_SMALL});
	//map.addControl(ctrl_nav);
	//向地图中添加切换地图模式控件
	var ctrl_ove = new BMap.MapTypeControl({mapTypes:[BMAP_NORMAL_MAP, BMAP_HYBRID_MAP,],anchor:BMAP_ANCHOR_TOP_LEFT});
	map.addControl(ctrl_ove);
};

initMap();//创建和初始化地图



/////////////////////////////  固定地图显示区域为“湖南省岳阳楼区”   //////////////////////////////////////
function yueYang(){
	//网格渲染
	var bdary = new BMap.Boundary();
	bdary.get("湖南省岳阳楼区", function(rs){//获取行政区域
	var count = rs.boundaries.length; //行政区域的点有多少个
	//map.clearOverlays();//清除地图覆盖物           
	//网上查了下，东西经南北纬的范围
	var EN_JW = "180, 90;";//东北角
	var NW_JW = "-180,  90;";//西北角
	var WS_JW = "-180, -90;";//西南角
	var SE_JW = "180, -90;";//东南角
	//添加环形遮罩层
	var ply1 = new BMap.Polygon(rs.boundaries[0] + SE_JW + SE_JW + WS_JW + NW_JW + EN_JW + SE_JW, { strokeColor: "none", fillColor: "rgba(0,0,0,0.5)", fillOpacity:1, strokeOpacity: 0.5 }); //建立多边形覆盖物
	map.addOverlay(ply1);
	//给目标行政区划添加边框，其实就是给目标行政区划添加一个没有填充物的遮罩层
	var ply = new BMap.Polygon(rs.boundaries[0], { strokeWeight: 2, strokeColor: "#0094FF",fillColor: "" });
	map.addOverlay(ply);
	//map.setViewport(ply.getPath());//调整视野
	});
};
