$(function () {
    $('#importBtn').click(function () {
        var d = new Dialog();
        d.init({
            target: $('#importDialog'),
            width: 800,
            show: true,
            mask: true
        });
    });

    $('#chooseFile').click(function () {
        $('#fileToUpload').click();
    });

    $('#fileToUpload').bind('change', function () {
        $.ajaxFileUpload({
            url: '/quantitative-assessment/price/import',
            secureuri: false,
            fileElementId: 'fileToUpload',
            dataType: 'json',
            success: function (data, status) {
                $("#result").append(data);
            },
            error: function (data, status, e) {
                $("#result").append(data);
            }
        });
    });
});