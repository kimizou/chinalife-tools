$(function () {
    //$('#importBtn').click(function () {
    //    var d = new Dialog();
    //    d.init({
    //        target: $('#importDialog'),
    //        width: 800,
    //        show: true,
    //        mask: true
    //    });
    //});

    $('#saveBtn').click(function () {
        $.post('/quantitative-assessment/price/import/save', function(result) {
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
        url: '/quantitative-assessment/price/import',
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