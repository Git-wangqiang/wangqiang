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
        + "欢迎回来," + logusername + "</a>" + "<dl class=\"layui-nav-child\">" +
        "<dd style=\"margin-left: 30px;\"><a href=\"/html/ModifyUserInfo.html\"><i class=\"layui-icon layui-icon-password\"></i>&nbsp;&nbsp;修改密码</a></dd>"
        + "<dd style=\"margin-left: 30px;\"><a href=\"\" onclick=\"logout();\"><i class=\"layui-icon layui-icon-close-fill\"></i>&nbsp;&nbsp;退出系统</a></dd>"
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
    index = layer.open({
        type: 2,
        area: ['470px', '230px'],
        fixed: false, //不固定
        maxmin: false,
        scrollbar: false,
        title: false,
        shadeClose: true,//点击其他区域是否显示
        shade: [0.8, '#393D49'],
        content: 'daochudaoru.html'
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

function delthis(id, tablename, u) {
    $.ajax({
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        url: u,
        data: JSON.stringify({id: id, tablename: tablename}),
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
    downloadTemplate('/api/downloadexcel', 'formworkname', 'formworkname');
}

function downloadTemplate(action, type, value) {
    /*var ids = [];
    ids.push(333);//获取数组中的id
    ids.push(334);//获取数组中的id*/
    var form = document.createElement('form');
    document.body.appendChild(form);
    form.style.display = "none";
    form.action = action;
    form.id = 'excel';
    form.method = 'get';

    var newElement = document.createElement("input");
    newElement.setAttribute("type", "hidden");
    newElement.name = type;
    newElement.value = "wangqiang";
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
    $.ajax({
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        async: false,
        url: "/hello/user/logout"
        , success: function (data) {
            if (data.isSuccess == true) {
                window.location.href = "/login.html";
            } else {
                layer.msg("操作失败");
            }
        },
        error: function () {
            layer.msg("操作失败");
        }
    });

}

function addresource(data) {
    $.ajax({
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        async: false,
        url: "/api/createtable",
        data: data,
        success: function (data) {
            if (data.isSuccess == true) {
                tableins.reload('reldata', {
                    url: '/api/selectdatasource'
                    , where: {} //设定异步数据接口的额外参数
                });
                layer.msg("新增成功");
            } else {
                layer.msg("新增失败");
            }
        },
        error: function () {
            layer.msg("新增失败");
        }
    });
}

function codegeneration(data) {
    var d = "";
    $.ajax({
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        async: false,
        url: "/api/codegeneration",
        data: data,
        success: function (data) {
            if (data.isSuccess == true) {
                d = data.data;
            } else {
                layer.msg("操作失败");
            }
        },
        error: function () {
            layer.msg("操作失败");
        }
    });
    return d;
}

function downselect() {
    layer.open({
        type: 1,
        content: $("#downloadselect").html(),
        title: '修改数据',
        area: ['550px', '400px'],
        offset: 'auto',
        fixed: false,
        scrollbar: false,
        maxHeight: 100
    });
    var str = "";
    var createformselect = document.getElementById("createformselect");
    $.ajax({
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        url: "/api/selectdatasourcenopage",
        data: JSON.stringify({id: ''}),
        success: function (data) {
            if (data.isSuccess == true) {
                d = data.data;
                str +=
                    "   <div class=\"layui-form-item\" style=\"margin-top: 20px\">" +
                    "                <label class=\"layui-form-label\">模板名称</label>\n" +
                    "                <div class=\"layui-input-block\">\n" +
                    "                    <input type=\"text\" id=\"mobanname\" required lay-verify=\"required\"\n" +
                    "                           autocomplete=\"off\"\n" +
                    "                           class=\"layui-input\" style=\"width: 300px;\">\n" +
                    "                    <input type=\"hidden\" name=\"id\">\n" +
                    "                </div>\n" +
                    "            </div>" +
                    " <div class=\"layui-inline\" style=\"margin-top: 20px\">\n" +
                    "                <label class=\"layui-form-label\">选择数据源</label>\n" +
                    "                <div class=\"layui-input-inline\">\n" +
                    "                    <select id=\"select-id\" name=\"modules\" lay-verify=\"required\" lay-search=\"\">\n" +
                    "                        <option value=\"\">直接选择或搜索选择</option>\n";
                for (i = 0; i < d.length; i++) {
                    str += "<option value=\"" + d[i].id + "\">" + d[i].tablename + "</option>";
                }
                str += "                    </select>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "<div class=\"layui-form-item layui-form-text\" style=\"margin-top: 20px\">\n" +
                    "    <label class=\"layui-form-label\">备注</label>\n" +
                    "    <div class=\"layui-input-block\">\n" +
                    "      <textarea id=\"remark\" placeholder=\"请输入内容\" class=\"layui-textarea\"></textarea>\n" +
                    "    </div>\n" +
                    "  </div>" +
                    "            <button class=\"layui-btn layui-btn-sm\" type=\"button\" style=\"margin-left: 220px\" onclick=\"submitcreateform();\">立即提交</button>";
                createformselect.innerHTML = str;
                form.render('select');
            } else {
                layer.msg("操作失败");
            }
        },
        error: function () {
            layer.msg("操作失败");
        }
    });
}

function submitcreateform() {
    var id = document.getElementById("select-id").value;
    var formworkname = document.getElementById("mobanname").value;
    var remark = document.getElementById("remark").value;
    var logusername = sessionStorage.getItem("logusername");
    $.ajax({
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        url: "/api/createformwork",
        data: JSON.stringify({id: id, formworkname: formworkname, remark: remark, username: logusername}),
        success: function (data) {
            if (data.isSuccess == true) {
                layer.closeAll();
                tableins.reload('reldata', {
                    url: '/api/selectformwork'
                    , where: {} //设定异步数据接口的额外参数
                });
                layer.msg("操作成功");
            } else {
                layer.msg(data.errorMessage);
            }
        },
        error: function () {
            layer.msg("操作失败");
        }
    });
}

function getDataFormworkRole() {
    $.ajax({
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        url: "/api/getcategoryandrole",
        data: JSON.stringify({id: ''}),
        success: function (data) {
            if (data.isSuccess == true) {
                var dd = data.data;
                var datasourceMOList = dd['datasourceMOList'];
                var formworkMOList = dd['formworkMOList'];
                var roleMOList = dd['roleMOList'];
                var str = "<fieldset class=\"layui-elem-field layui-field-title fieldset-roleinfo\" style=\"margin-top: 30px;\">\n" +
                    "                    <legend>选择数据源</legend>\n";
                if (datasourceMOList != null && datasourceMOList.length > 0) {
                    for (i = 0; i < datasourceMOList.length; i++) {
                        str += "<input type=\"radio\" name=\"datasource\" value=\"" + datasourceMOList[i].id + "\" title=\"" + datasourceMOList[i].tablename + "\" checked>";
                    }
                }
                str += "                </fieldset>\n" +
                    "                <fieldset class=\"layui-elem-field layui-field-title\" style=\"margin-top: 50px;\">\n" +
                    "                    <legend>选择模板</legend>\n";
                if (formworkMOList != null && formworkMOList.length > 0) {
                    for (i = 0; i < formworkMOList.length; i++) {
                        str += "<input type=\"radio\" name=\"formwork\" value=\"" + formworkMOList[i].id + "\" title=\"" + formworkMOList[i].formworkname + "\" checked>";
                    }
                }

                str += "                </fieldset>\n" +
                    "\n" +
                    "                <fieldset class=\"layui-elem-field layui-field-title\" style=\"margin-top: 50px;\">\n" +
                    "                    <legend>选择角色</legend>\n";
                if (roleMOList != null && roleMOList.length > 0) {
                    for (i = 0; i < roleMOList.length; i++) {
                        str += "<input type=\"radio\" name=\"role\" value=\"" + roleMOList[i].id + "\" title=\"" + roleMOList[i].description + "\" checked>";
                    }
                }
                str += "                </fieldset>\n" +
                    "                <div class=\"layui-form-item roleinfo-btn-save\" style=\"margin-top: 200px;\">\n" +
                    "                    <button class=\"layui-btn layui-btn-sm\" onclick=\"saveCategory();\">保存</button>\n" +
                    "                    </button>\n" +
                    "                </div>";
                var ht = document.getElementById("dataformworkrole");
                ht.innerHTML = str;
                form.render('radio');
            } else {
                layer.msg(data.errorMessage);
            }
        },
        error: function () {
            layer.msg("操作失败");
        }
    });
}

function saveCategory() {
    var datasourceid = $("input[name='datasource']:checked").val();
    var formworkid = $("input[name='formwork']:checked").val();
    var roleid = $("input[name='role']:checked").val();
    var roletitle = $("input[name='role']:checked")[0].title;
    var formworktitle = $("input[name='formwork']:checked")[0].title;
    var datasourcetitle = $("input[name='datasource']:checked")[0].title;
    $.ajax({
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        url: "/api/savecategory",
        data: JSON.stringify({
            datasourceid: datasourceid,
            formworkid: formworkid,
            roleid: roleid,
            roletitle: roletitle,
            formworktitle: formworktitle,
            datasourcetitle: datasourcetitle
        }),
        success: function (data) {
            if (data.isSuccess == true) {
                layer.msg("操作成功");
            } else {
                layer.msg(data.errorMessage);
            }
        },
        error: function () {
            layer.msg("操作失败");
        }
    });
}

function getDataBt() {
    var mycols = [];
    var logusername = sessionStorage.getItem("logusername");
    $.ajax({
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        url: "/api/getdatabtcols",
        async: false,
        data: JSON.stringify({
            logusername: logusername
        }),
        success: function (data) {
            if (data.isSuccess == true) {
                mycols = data.data;
            } else {
                layer.msg(data.errorMessage);
            }
        },
        error: function () {
            layer.msg("操作失败");
        }
    });
    return mycols;
}

function gettabtitle(tablename) {
    var str = "";
    var straddinfo = "";
    $.ajax({
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        url: "/api/getRemarkBytablename",
        data: JSON.stringify({
            tablename: tablename
        }),
        success: function (data) {
            if (data.isSuccess == true) {
                var d = data.data.remark;
                var dd = document.getElementById("tab-title");
                str += "<li class=\"layui-this\">" + d + "信息查看" + "</li>";
                str += "<li>" + d + "信息添加" + "</li>";
                dd.innerHTML = str;

                straddinfo += "<form class=\"layui-form layui-form-pane gcjs-add\">";
                var datas = data.data.cloumsPropertyRequestMOList;
                for (i = 0; i < datas.length; i++) {
                    straddinfo += "<div class=\"layui-form-item gcjs-add\">";
                    straddinfo += "<label class=\"layui-form-label\">" + datas[i].cloumtnote + "</label>";
                    straddinfo += "<div class=\"layui-input-block\">";
                    straddinfo += "<input type=\"text\" name=\"" + datas[i].cloumtname + "\" class=\"layui-input tjform\" id=\"" + datas[i].cloumtname + "\">";
                    straddinfo += "</div>\n" +
                        "    </div>";
                }
                straddinfo += "    <div class=\"layui-form-item gcjs-add-but\">\n" +
                    "    <div class=\"layui-input-block\">\n" +
                    "    <button class=\"layui-btn layui-btn-sm\" lay-submit  lay-filter=\"but-submit\"" +
                    ">保存\n" +
                    "    </button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                    "    <button type=\"reset\" class=\"layui-btn layui-btn-primary layui-btn-sm\">重置</button>\n" +
                    "    </div>\n" +
                    "    </div>";
                straddinfo += " </form>";
                var addinfo = document.getElementById("addinfo");
                addinfo.innerHTML = straddinfo;

            } else {
                layer.msg(data.errorMessage);
            }
        },
        error: function () {
            layer.msg("操作失败");
        }
    });
}

function getExit() {
    var col = getDataBt();
    var str = "<form class=\"layui-form layui-form-pane\" action=\"\" lay-filter=\"edit-filter-form\">";
    if (col != null && col.length > 0) {
        for (i = 0; i < col.length; i++) {
            str += "<div class=\"layui-form-item\">";
            str += "<label class=\"layui-form-label\">" + col[i].cloumtnote + "</label>";
            str += "<div class=\"layui-input-block\">";
            str += "<input type=\"text\" name=\"" + col[i].cloumtname + "\" required lay-verify=\"required\" placeholder=\"请输入标题\"   autocomplete=\"off\" class=\"layui-input\">";
            str += "</div></div>";
        }
    }
    str += "<div class=\"layui-form-item\">\n" +
        "            <div class=\"layui-input-block\">\n" +
        "                <button class=\"layui-btn layui-btn-sm\" lay-submit lay-filter=\"but-submit-editinfo\">立即提交</button>\n" +
        "                <button type=\"reset\" class=\"layui-btn  layui-btn-sm layui-btn-primary\">重置</button>\n" +
        "            </div>\n" +
        "        </div>";
    str += "</form>";
    return str.toString();
}

function getclounmsbytablename(tablename) {
    var str = "";
    $.ajax({
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        url: "/api/getclounmsbytablename",
        data: JSON.stringify({
            tablename: tablename
        }),
        success: function (data) {
            if (data.isSuccess == true) {
                var formselectdata = data.data;
                if (formselectdata != null && formselectdata.length > 0) {
                    for (i = 0; i < formselectdata.length; i++) {
                        str += "<div class=\"layui-inline flexbox1child\">\n" +
                            "                    <label class=\"layui-form-label cxlable\">" + formselectdata[i].cloumtnote + "</label>\n" +
                            "                <div class=\"layui-input-inline colformstyle\"\n" +
                            "                style=\"width: 200px; line-height: 30px\">";
                        str += "<input type=\"text\" name=\"\" autocomplete=\"off\" class=\"layui-input colformstyle rdnf\" id=\"" + formselectdata[i].cloumtname + "\"></div> </div>";

                    }
                }


                /*                str += "<button type=\"button\" class=\"layui-btn layui-btn-normal layui-btn-sm\" id=\"chaxun\">查询 </button>";
                                str += "<button type=\"button\" onclick=\"daochudaoru();\" class=\"layui-btn layui-btn-primary layui-btn-sm\">导入导出 </button>";
                                str += "<button type=\"button\" class=\"layui-btn layui-btn-danger layui-btn-sm\" id=\"piliangshanchu\">批量删除  </button>";*/
                document.getElementById("form-select").innerHTML = str;
            } else {
                layer.msg(data.errorMessage);
            }
        },
        error: function () {
            layer.msg("操作失败");
        }
    });
}

function dataDaochu() {
    var test = parent.$("#999test").val();
    var table = parent.layui.table;
    var checkStatus = table.checkStatus("xitongshezhit");
    var col = getDataBt();
    var tablename = col[0].tablename;
    $.ajax({
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        url: "/api/saveExcle",
        data: JSON.stringify({checkdata: checkStatus.data, tablename: tablename}),
        success: function (data) {
            if (data.isSuccess == true) {
                //假设这是iframe页
                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.layer.close(index); //再执行关闭
                layer.msg("导出成功");
            }
        },
        error: function () {
            layer.msg("操作失败");
        }
    });
}