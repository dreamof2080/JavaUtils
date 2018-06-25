// ajax提交附件到后台
var formData = new FormData();
formData.append("action","fileupload");
formData.append("file",document.getElementById("excelfile").files[0]);
$.ajax({
    type: "post",
    url: "<%=request.getContextPath()%>/ServiceAction/com.eweaver.excel.servlet.UsersCategoryExcelAction_b_system?action=fileupload",
    data: formData,
    dataType: "json",
    processData:false,
    contentType:false,
    async: false,
    success: function (data) {

    },
    error: function (data) {

    }
});

