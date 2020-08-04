//详情--修改--弹出层
function indexModOpen(){
    layer.open({
        type: 2,//模态框的类型
        offset: '50px',//弹出的位置
        skin: 'layui-layer-molv',//引用的CSS
        title: '详情--修改--弹出层',//标题
        content:['modal/indexMod.html'] ,//弹出的HTML界面
        area:['800px', '450px'],//模态框的宽高
        maxmin: true,//开启全屏显示
    });
};



