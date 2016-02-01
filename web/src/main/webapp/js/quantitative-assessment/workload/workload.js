$(function () {
    $('#datetimepicker').datetimepicker({
        format: 'yyyymm',
        weekStart: 1,
        autoclose: true,
        startView: 3,
        minView: 3,
        forceParse: false,
        language: 'zh-CN'
    });

    $('#saveBtn').click(function () {
        var yearMonth = $('input[name="yearMonth"]').val();
        if (yearMonth == '') {
            $.alert("请选择月份");
            return false;
        }
        $.post('/quantitative-assessment/workload/import/save', {
            yearMonth: yearMonth
        }, function(result) {
            if (result && result.success) {
                history.go(-1);
            } else if (result && result.message) {
                $.alert(result.message);
            } else {
                $.alert('保存失败');
            }
        });
    });

});

function uploadFile(val) {
    $('#fileShowInput').val(val);
    $.ajaxFileUpload({
        url: '/quantitative-assessment/workload/import',
        secureuri: false,
        fileElementId: 'fileToUpload',
        dataType: 'json',
        success: function (data) {
            if (data && data.success) {
                $("#hiddenSearch").click();
            } else if (data && data.message) {
                $.alert(data.message);
            } else {
                $.alert('导入失败');
            }
        },
        error: function () {
            $.alert('导入失败');
        }
    });
}