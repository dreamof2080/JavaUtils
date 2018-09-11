// ajax提交表单数据到后台
$.ajax({
    type: "post",
    url: "<%=request.getContextPath()%>/ServiceAction/com.eweaver.excel.servlet.UsersCategoryExcelAction_b_system?action=fileupload",
    data: $("#form").serialize(),
    dataType: "json",
    processData:false,
    contentType:false,
    async: false,
    success: function (data) {

    },
    error: function (data) {

    }
});