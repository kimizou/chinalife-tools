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

    $('#fileToUpload').bind('change', function() {
        $('#fileShowInput').val(this.value);
        $.ajaxFileUpload({
            url: '/quantitative-assessment/price/import',
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