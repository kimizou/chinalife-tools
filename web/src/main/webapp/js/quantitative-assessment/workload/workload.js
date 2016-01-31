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

    $('#fileToUpload').bind('change', function() {
        $('#fileShowInput').val(this.value);
        $.ajaxFileUpload({
            url: '/quantitative-assessment/workload/import',
            secureuri: false,
            fileElementId: 'fileToUpload',
            dataType: 'json',
            success: function (data, status) {
                $("#hiddenSearch").click();
            },
            error: function (data, status, e) {
                $("#result").append(data);
            }
        });
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