/**
 * Created by Luck on 2017/6/29.
 */
$(document).ready(function () {
    //折叠菜单开始
    $(function(){
        var changeType=$('.nav_city').find('div');
        $.each(changeType,function(){
            $(this).on('click',function(){
                if(false==$(this).next().is(':visible')){
                    $('.nav_city ul').slideUp(0);
                }
                $(this).next().slideToggle(0);
            });
        });
        var hoverType=$('.nav_city .nav_area').find('div');
        $.each(hoverType,function(){
            $(this).mouseenter(function(){
                $(this).css('color',"#F44268")
            })
            $(this).mouseleave(function(){
                $(this).css('color',"black")
            })
        })
    })
    //折叠菜单结束


    //城市分类菜单上城市首字母----开始

    //<![CDATA[
    $(function () {
        var divs =new Array();
        var i=0;
        $(".word").each(function () {
            var word=$(this).attr("id");
            div="<div style=' width: 100%;height: 30px;line-height: 30px;background-color:  #EBE9EA;text-indent: 1em;font-size: 1.5em;margin-top: 2px;margin-bottom: 2px;' class='first' id='#"+word+"'>"+word+"</div>";
            divs[i]=div;
            i++
        })
        var i=0;
        $(".nav_city").each(function () {
            $(this).before(divs[i]);
            i++;
        })
    })
    //]]>

    //城市分类菜单上城市首字母----结束

    //点击小区 跳转到注册页面
    $(".area").click(function () {
        var city=$(this).parent().parent().siblings(".city").text();
        var area=$(this).text();
        var city_area=city+"-"+area;
        window.location="/user/register?city="+city_area;
    })

})