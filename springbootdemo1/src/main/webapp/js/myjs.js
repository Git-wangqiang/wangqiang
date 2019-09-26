function diy_submit() {
    var inputname = $('.inputname').val();
    var inputpassword = $('.inputpassword').val();
    var inputvcode = $('.inputvcode').val();
    if (inputname == "") {
        alert("用户名不能为空");
        return false;
    } else if (inputpassword == "") {
        alert("密码不能为空");
        return false;
    } else if (inputvcode == "") {
        alert("请输入验证码");
        return false;
    }
    aVerify();
}

function login() {
    var inputname = $('.inputname').val();
    var inputpassword = $('.inputpassword').val();
    var inputvcode = $('.inputvcode').val();
    $.ajax({
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        url: "/hello/user/login",
        //data: $('#formajax').serialize(),
        data: JSON.stringify({username: inputname, password: inputpassword, verifyCode: inputvcode}),
        success: function (data) {
            if (data.isSuccess == true) {
                var dd = encodeURIComponent(JSON.stringify(data))
                sessionStorage.setItem("data", dd);
                sessionStorage.setItem("logusername", inputname);
                window.location.href = "/html/info2.html";
            } else {
                alert(data.errorMessage)
            }
        },
        error: function () {
            alert(data.errorMessage);
        }
    });
}

//获取验证码
function getVerify() {
    $("#imgVerify").attr("src", '/hello/user/getVerify?' + Math.random());//jquery方式
}

function aVerify() {
    var value = $("#verify_input").val();
    // alert(value);
    $.ajax({
        async: false,
        type: 'post',
        url: '/hello/user/checkVerify',
        dataType: "json",
        data: {
            verifyInput: value
        },
        success: function (result) {
            if (result) {
                //alert("success!");
                login();
            } else {
                alert("验证码不正确");
                getVerify();
            }
            //window.location.reload();

        }
    });
}

function test(data) {
    var da = data;
    alert(da);
}

function aa() {
    alert("aa");
}
