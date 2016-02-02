$(function () {
    // 删除提交
    $('body').on('click', '.js-delete', function() {
        var data = {
            id: $(this).data("id")
        };
        var url = $(this).attr('href');
        $.confirm({
            closeIcon: true,
            title: '确定删除？',
            confirmButton: '确定',
            cancelButton: '取消',
            confirmButtonClass: 'btn-primary btn-sm',
            cancelButtonClass: 'btn-sm',
            icon: 'glyphicon glyphicon-warning-sign',
            confirm: function() {
                $.post(url, data, function(data) {
                    if (data && data.success) {
                        $.alert('删除成功!');
                        page = 1;
                        bindData();
                    } else {
                        $.alert('删除失败!');
                    }
                }, 'json');
            },

        });

        return false;
    });

    if ($("#ajaxloading").size() == 0) {
        $('body').append('<div id="ajaxloading"><i></i></div>');
    }
    if(typeof noloading =="undefined") {
        $.ajaxSetup({
            beforeSend:function() {
                $('#ajaxloading').show();
            },
            complete:function() {
                $('#ajaxloading').hide();
            },error:function() {
                $('#ajaxloading').hide();
            }
        });
    }
});