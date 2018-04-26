$.ajax({
    url:"/err/getAjaxerror",
    type:POST,
    async:false,
    success:function (data) {
        if(data.status == 200 && data.msg == "OK")
        {
            alert("seccess");
        }else{
            alert("发生异常"+data.msg);
        }
        },
    error:function (response,ajaxOptions,throwsError) {
        alert("error");
    }
    });