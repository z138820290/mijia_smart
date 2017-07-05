/**
 * Created by Luck on 2017/7/2.
 */
$(document).ready(function () {
    var sortId=1;
    $(".sortId").each(function () {
        $(this).text(sortId);
        sortId++;
    })
    var _token = null;
    $.ajax({
        url: '/admin/token',
        type: 'get',
        success: function (data) {
            _token = data;
            console.debug('A: ' + _token);
        }
    });
    // 上传空间URL
    var uploadSpace = 'http://os4yv5mgw.bkt.clouddn.com';

    qiniu.config({
        url: uploadSpace,
        uploadUrl: 'up-z0.qiniu.com' // 七牛统一的一个上传域名，固定
    });

    qiniu.bind($('#submit'), {
        filter: 'image'
    }).on('file', function(file) {
        var imagesBucket = qiniu.bucket('mijia', {
            putToken: _token
        });
        var fileName = file.name;

        fileName = 'image/' + fileName;

//                    return;

        console.debug('文件名：'  + fileName);


        // Upload
        imagesBucket.putFile(fileName, file)
            .then(
                function(reply) {
                    console.debug(reply.hash);
                    console.debug(reply.key);
                    var se = new Date().getMilliseconds();
                    var _data={};
                    _data.url=uploadSpace + '/' + fileName+"?v="+se;
                    $.ajax({
                        type:"post",
                        url:"/admin/adv",
                        data:JSON.stringify(_data),
                        contentType:"application/json",
                        success:function (data) {
                            if (data=="1"){
                                window.location.reload();
                            }
                        }
                    })
                },
                function(err) {
                    // 上传失败
                    console.error(err);
                }
            );
    });


    $.extend({
        upload2Qiniu:function () {

        }
    });
    //<![CDATA[
    $(".delete").click(function () {
        var _data={};
        _data.id=$(this).parent().parent().parent().children(".advertId").text();
        _data.url=$(this).parent().parent().siblings(".imageUrl").children("img").attr("src").substring(33);
        $.ajax({
            type:"delete",
            url:"/admin/delete/"+_data.id,
            data:JSON.stringify(_data),
            contentType:"application/json",
            success:function (data) {
                if (data=="1"){
                    window.location.reload();
                }
            },
            error: function (data, status, er) {
                console.debug("error: "+data+" status: "+status+" er:"+er);
            }
        })

    })
    //]]>
})
