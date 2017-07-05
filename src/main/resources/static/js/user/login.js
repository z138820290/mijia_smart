/**
 * Created by hzy on 17-7-5.
 */
$(document).ready(function () {
    $("button").click(function () {
        var _data={};
        _data.login=$("#account").val();
        _data.password=$("#password").val();
        $.ajax({
            type:"post",
            url:"/user/login",
            data:JSON.stringify(_data),
            contentType:"application/json",
            success:function (data) {
                function message() {
                    $("#message").css("top",window.innerHeight/2-$("#message").height()/2);
                    $("#message").css("left",window.innerWidth/2-$("#message").width()/2);
                    $("#message").css("z-index","2");
                }
                $("#message").click(function () {
                    $("#message").text("");
                    $("#message").css("z-index","-1");
                })
                if(data=="1"){
                    message();
                    $("#message").text("手机号不存在");
                }else if (data=="2"){
                    window.location="/index";
                }else {
                    message();
                    $("#message").text("密码输入有误");
                }
            }
        })
    })
})
$(".back").click(function () {
    window.location="/index";
})
$(".register").click(function () {
    window.location="/user/register"
})