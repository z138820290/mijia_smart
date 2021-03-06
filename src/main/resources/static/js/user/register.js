/**
 * Created by hzy on 17-7-5.
 */
$(document).ready(function () {
    //<![CDATA[
    $(function () {
        if ($("#button").text()==""){
            $("#button").text("获取验证码");
        }
    })
    $("#city").click(function () {
        var phone=$("#phone").val();
        var name=$("#name").val();
        var address=$("#address").val();
        var password=$("#password").val();
        var valitation=$("#input_validation").val();
        var $form=$(document.createElement('form')).css({display:'none'}).attr("method","POST").attr("action","/user/address");
        var $input2=$(document.createElement('input')).attr('name','phone1').val(phone);
        var $input=$(document.createElement('input')).attr('name','name1').val(name);
        var $input3=$(document.createElement('input')).attr('name','address1').val(address);
        var $input4=$(document.createElement('input')).attr('name','password1').val(password);
        var $input5=$(document.createElement('input')).attr('name','validation1').val(valitation);
        $form.append($input).append($input2).append($input3).append($input4).append($input5);
        $("body").append($form);
        $form.submit();
    });
    <!-- 获取验证码 -->
    $("#button").click(function () {
        var rand="";
        var i=0;
        rand= Math.floor(Math.random()*10000);
        if (rand<1000){
            rand=rand*10;
        }
        $(this).text(rand)
    });
    <!-- 消息弹出 -->
    function message() {
        $("#message").css("top",window.innerHeight/2-$("#message").height()/2);
        $("#message").css("left",window.innerWidth/2-$("#message").width()/2);
        $("#message").css("z-index","2");
    }
    var flag=false;
    <!-- 手机号码验证 -->
    $("#phone").blur(function () {
        var phone=$(this).val();
        if (phone==""){
            flag=false;
            message();
            showTime();
            $("#message").text("手机号不能为空");
            $("#phone").focus();
        }else if(!$("#phone").val().match(/^(((13[0-9]{1})|159|153)+\d{8})$/)){
            flag=false
            message();
            $("#message").text("手机格式不正确");
        }else {
            flag=true;
        }
    })
    $("#input_validation").blur(function () {
        var validation=$(this).val();
        if(validation=="" && $("#button").text()!="获取验证码"){
            flag=false;
            $("#message").text("验证码错误");
        }else if (validation!=$("#button").text() && validation!="" ){
            flag=false
            message();
            $("#message").text("验证码错误");
        }else {
            flag=true;
        }
    })
    $("#btn_register").mouseenter(function () {
        $(this).css("cursor","pointer");
    })
    $("#sure_password").blur(function () {
        if ($(this).val()!=$("#password").val()){
            flag=false;
            message()
            $("#message").text("两次密码输入不相同");
        }else {
            flag=true;
        }
    })
    $("#btn_register").click(function () {
        if (flag==true){
            var _data={};
            _data.phone=$("#phone").val();
            _data.name=$("#name").val();
            _data.city=$("#city").val();
            _data.address=$("#address").val();
            _data.password=$("#password").val();
            $.ajax({
                type:"post",
                url:"/user/register",
                data:JSON.stringify(_data),
                contentType:"application/json",
                success:function (data) {
                    window.location="/index";
                }
            })
        }else {
            message();
            $("#message").text("信息输入有误");
        }
    })
    $("#message").click(function () {
        $(this).css("z-index","-1")
    })
    $(".back").click(function () {
        window.history.back();
    })
    //]]>
})