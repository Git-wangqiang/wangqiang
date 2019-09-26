function cd() {
    var trs = "";
    var dtcd = document.getElementById("dtcd");
    var data = JSON.parse(decodeURIComponent(sessionStorage.getItem("data"))).data;
    if (data.length < 1) {
        alert("请添加权限");
        return false;
    }
    for (var i in data) {
        console.log(i);
        console.log(data[i]);
        var dataj = data[i];
        // console.log("ddd"+dataj[j].name+dataj[j].url);
        trs += "<li class=\"layui-nav-item layui-nav-itemed\"><a href = \"#\"" + ">" + "<i class=\"layui-icon layui-icon-layer \"></i> &nbsp;" + i + "</a>";
        trs += "<dl class=\"layui-nav-child\">"
        for (var j in dataj) {
            trs += "<dd><a href=\"" + dataj[j].url + "\">" + "<i class=\"layui-icon layui-icon-file\" style=\"font-size: 3px\"></i>&nbsp;"
                + dataj[j].name + "</a></dd>"
        }
        trs += "</dl></li>";
    }
    dtcd.innerHTML = trs;
    //jztable(1, 10);
    var str = "";
    var logininfo = document.getElementById("loginusername");
    var logusername = sessionStorage.getItem("logusername");
    str += "<ul class=\"layui-nav\"><div class=\"pagefix\" style=\"float: right;\"><li class=\"layui-nav-item\">"
        + "<a href=\"#\"><i class=\"layui-icon layui-icon-user\" style=\"font-size: 16px;\"></i>&nbsp;"
        + "欢迎回来," + logusername + "</a>" + "<dl class=\"layui-nav-child\"><dd><a href=\"/html/ModifyUserInfo.html\">修改信息</a></dd>"
        + "<dd><a href=\"\" onclick=\"logout();\">退了</a></dd>"
        + "</dl></li></div></ul>";
    logininfo.innerHTML = str;
    element.init();
}

function jztable(pNum, pSize) {
    var rdnf = $('.rdnf').val();
    var mc = $('.mc').val();
    var yyzt = $('.yyzt').val();
    var str = "";
    //var tbody = document.getElementById("tbody");
    var tbody = $('#tbody');
    $.ajax({
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        url: "/api/zckj",
        data: JSON.stringify({rdnf: rdnf, mc: mc, yyzt: yyzt, pageNum: pNum, pageSize: pSize}),
        success: function (data) {
            if (data.isSuccess == true) {
                //如果成功就显示数据
                str += "<table style='text-align: center' class=\"layui-table \" lay-even>";
                str += "<colgroup> <col width=\"150\"> <col width=\"200\"> <col> </colgroup>" + "<thead> <tr> <th>全选</th> <th>昵称</th> <th>加入时间</th> <th>签名</th> <th>操作</th> </tr> </thead>"
                var datalen = data.data.list;
                for (i = 0; i < datalen.length; i++) {
                    str += " <tbody> <tr> <td> <input type = \"checkbox\" > </td>";
                    str += "<td>" + datalen[i].mode_name + "</td>" + "<td>" + datalen[i].updatedate + "</td>" + "<td>" + datalen[i].yyzt + "</td>";
                    //str += "<td> <a href = \"#\">修改删除</a></td >" + "</tr>";
                    str += "<td>" + "<div class=\"layui-btn-group\"> <button type=\"button\" class=\"layui-btn layui-btn-xs\">编辑</button> <button type=\"button\" class=\"layui-btn layui-btn-xs\">删除</button> </div>" + "</td></tr>";
                }
                str += "</tbody></table>";
                //tbody.innerHTML(str);
                tbody.empty();
                tbody.append(str);
                var dd = encodeURIComponent(JSON.stringify(data))
                sessionStorage.setItem("cou", dd);
                element.render();
            } else {
                alert(data.errorMessage)
            }
        },
        error: function () {
            alert(data.errorMessage);
        }
    });
}

function daochudaoru() {
    layer.open({
        type: 2,
        area: ['450px', '230px'],
        fixed: false, //不固定
        maxmin: false,
        scrollbar: false,
        title: false,
        shadeClose: true,//点击其他区域是否显示
        shade: [0.8, '#393D49'],
        content: 'daochudaoru.html',
        /*   end: function () {
               layer.msg("已经销毁");
           }*/
    });
}

function jztableForcount(pNum, pSize) {
    $.ajax({
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        url: "/api/zckj",
        data: JSON.stringify({rdnf: null, mc: null, yyzt: null, pageNum: pNum, pageSize: pSize}),
        success: function (data) {
            if (data.isSuccess == true) {
                //如果成功就显示数据
                alert(data.data.total);
                return data.data.total;
            } else {
                alert(data.errorMessage)
            }
        },
        error: function () {
            alert(data.errorMessage);
        }
    });
}


function delthis(id, u) {
    $.ajax({
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        url: u,
        data: JSON.stringify({id: id}),
        success: function (data) {
            if (data.isSuccess == true) {
                return data;
            } else {
                alert(data.errorMessage)
            }
        },
        error: function () {
            alert(data.errorMessage);
        }
    });
}


function downloadmoban() {
    downloadTemplate('/api/downloadexcel', 'filename', 'project');
}

function downloadTemplate(action, type, value) {
    var ids = [];
    ids.push(333);//获取数组中的id
    ids.push(334);//获取数组中的id

    var form = document.createElement('form');
    document.body.appendChild(form);
    form.style.display = "none";
    form.action = action;
    form.id = 'excel';
    form.method = 'get';

    var newElement = document.createElement("input");
    newElement.setAttribute("type", "hidden");
    newElement.name = type;
    newElement.value = JSON.stringify(ids);
    form.appendChild(newElement);
    form.submit();
}

function daochu() {
    downloadTemplateByPost('/api/exportexcel', 'filename', 'project');
}

function daochu1() {
    downloadTemplate('/api/exportexcel', 'filename', 'project');
}

function downloadTemplateByPost(action, type, value) {
    var ids = [];
    ids.push(333);//获取数组中的id
    ids.push(334);//获取数组中的id
    $.ajax({
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        url: action,
        data: JSON.stringify({ids: ids}),
        success: function (data) {
            if (data.isSuccess == true) {
                var content = res.data
                var blob = new Blob([content])
                var fileName = '语音文本.txt'  //导出文件名称自定义
                if ('download' in document.createElement('a')) { // 非IE下载
                    var elink = document.createElement('a')
                    elink.download = fileName
                    elink.style.display = 'none'
                    elink.href = URL.createObjectURL(blob)
                    document.body.appendChild(elink)
                    elink.click()
                    URL.revokeObjectURL(elink.href) // 释放URL 对象
                    document.body.removeChild(elink)
                } else { // IE10+下载
                    navigator.msSaveBlob(blob, fileName)
                }
            }
        },
        error: function () {
            alert(data.errorMessage);
        }
    });
}

function chongzhimima() {
    var password = "123456";
    var username = $("#username-edit").val();
    $.ajax({
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        url: "/api/tresetpassword",
        data: JSON.stringify({password: password, username: username}),
        success: function (data) {
            debugger;
            if (data.isSuccess == true) {
                layer.msg("重置成功，初始密码为123456");
            }
        },
        error: function () {
            layer.msg("重置成功，初始密码为123456");
        }
    });
}

function updaterole() {
    var checkData = tree.getChecked('demoId');
    var selectrole = $('#select-role option:selected').val();
    var ids = [];
    if (checkData != null && checkData.length > 0) {
        for (i = 0; i < checkData.length; i++) {
            ids.push(checkData[i].id);
            var children = checkData[i].children;
            if (children != null && children.length > 0) {
                for (j = 0; j < children.length; j++) {
                    ids.push(children[j].id);
                }
            }
        }
    }
    if (selectrole == null || selectrole == "") {
        layer.msg("请选择相应的角色信息");
    } else {
        $.ajax({
            type: "post",
            contentType: 'application/json',
            url: "/api/updaterole",
            data: JSON.stringify({ids: ids, roleid: selectrole}),
            success: function (data) {
                if (data.isSuccess == true) {
                    layer.msg("修改成功");
                }
            },
            error: function () {
                layer.msg("修改失败");
            }
        });
    }
}

function gettreeselect() {
    $.ajax({
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        url: "/api/selectrole",
        data: JSON.stringify({id: '', username: ''}),
        success: function (data) {
            if (data.isSuccess == true) {
                var getselect = document.getElementById("gettreeselect");
                var str = "";
                str += "<label class=\"layui-form-label\">角色名称</label>\n" +
                    "                <div class=\"layui-input-block\">\n" +
                    "                <select name=\"city\" id=\"select-role\" lay-filter=\"roleselect\" lay-verify=\"required\">\n" +
                    "                <option value=\"\"></option>";
                var d = data.data;
                for (i = 0; i < d.length; i++) {
                    str += "<option value=\"" + d[i].id + "\">" + d[i].description + "</option>";
                }
                str += "</select>\n" +
                    "                </div>";
                getselect.innerHTML = str;
                form.render('select');
            }
        },
        error: function () {
            layer.msg("重置成功，初始密码为123456");
        }
    });
}

function gettreedata(id) {
    $.ajax({
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        url: "/api/permissionselect",
        data: JSON.stringify({id: id}),
        success: function (data) {
            if (data.isSuccess == true) {
                //树形控件
                tree.render({
                    elem: '#test7'
                    , id: 'demoId'
                    , data: data.data
                    , showCheckbox: true
                });
            }
        },
        error: function () {
            layer.msg("系统异常");
        }
    });
}


function getUser(logusername) {
    var d = "";
    $.ajax({
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        async: false,
        url: "/api/findbyusername",
        data: logusername,
        success: function (data) {
            if (data.isSuccess == true) {
                d = data.data;
            }
        },
        error: function () {
            layer.msg("系统异常");
        }
    });
    return d;
}

function logout() {
    sessionStorage.clear();  //清除所有session值
    window.location.replace("/login.html");
}

function addresource() {
    layui.data('adddatasource',{
        "cloumname": "uid",
        "cloumtype": "int",
        "cloumlength": 11,
        "cloumpoint": "",
        "isnull": 0,
        "iskey": 1,
        "isautoincrement": 1
    });
    /*var adddata = [{
        "cloumname": "uid",
        "cloumtype": "int",
        "cloumlength": 11,
        "cloumpoint": "",
        "isnull": 0,
        "iskey": 1,
        "isautoincrement": 1
    }];
    Data.push(adddata);
    addresourcetable.reload({
        data: Data
    });*/
}