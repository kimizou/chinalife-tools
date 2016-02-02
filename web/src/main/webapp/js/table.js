$(function() {

    $('form.searchForm').each(function() {
        var page = 1;
        var nodata = "没有相关内容";
        var me = $(this);
        function bindData(b) {
            var data = {
                'currentPage': page,
                rows: me.find("#Pagination").data('pagesize') || 10
            };
            var args = getParam(me) || {};
            data = $.extend(data, args);
            var url = me.find('#table').attr('href');
            $.post(url, data, function(result) {
                if (!b) {
                    initPagination(result, me);
                }
                if (!result.success) {
                    return;
                } else if (result.data.page.totalRows == 0) {
                    var td = me.find('#table th').size();
                    $('#table>tbody').html('<tr><td colspan="' + td + '" align="center">' + nodata + '</td></tr>');
                } else {
                    formatHtml(result, me);
                }
                //formatBreadcrumb(result);
            }, 'json');
        }
        function getParam(form) {
            var result = {};
            $(form).find('*[name]').each(function(i, v) {
                var nameSpace,
                    name = $(v).attr('name'),
                    val = $.trim($(v).val()),
                    tempArr = [],
                    tempObj = {};
                if (name == '') {
                    return;
                }
                val = val == $(v).attr('placeholder') ? "" : val;
                //处理radio add by yhx  2014-06-18
                if ($(v).attr("type") == "radio") {
                    var tempradioVal = null;
                    $("input[name='" + name + "']:radio").each(function() {
                        if ($(this).is(":checked"))
                            tempradioVal = $.trim($(this).val());
                    });
                    if (tempradioVal) {
                        val = tempradioVal;
                    } else {
                        val = "";
                    }
                }

                if ($(v).attr("type") == "checkbox") {
                    var tempradioVal = [];
                    $("input[name='" + name + "']:checkbox").each(function() {
                        if ($(this).is(":checked"))
                            tempradioVal.push($.trim($(this).val()));
                    });
                    if (tempradioVal.length) {
                        val = tempradioVal.join(',');
                    } else {
                        val = "";
                    }
                }
                //构建参数
                if (name.match(/\./)) {
                    tempArr = name.split('.');
                    nameSpace = tempArr[0];
                    tempObj[tempArr[1]] = val;
                    if (!result[nameSpace]) {
                        result[nameSpace] = tempObj;
                    } else {
                        result[nameSpace] = $.extend({}, result[nameSpace], tempObj);
                    }

                } else {
                    result[name] = val;
                }

            });
            var obj = {};
            for (var o in result) {
                var v = result[o];
                if (typeof v == "object") {
                    obj[o] = JSON.stringify(v);
                } else {
                    obj[o] = result[o]
                }
            }
            return obj;
        }
        function initPagination(result, context) {
            if (!result || !result.success) {
                return $.alert((result.exception ? result.exception.cause.errorCode + ':' + result.exception.cause.message : null) ||result.message|| '分页初始化失败');
            }
            // 创建分页
            context.find("#Pagination").pagination(result.data.page.totalPages, {
                num_edge_entries: 1, //边缘页数
                num_display_entries: 4, //主体页数
                callback: pageselectCallback,
                items_per_page: 1, //每页显示1项
                prev_text: "前一页",
                next_text: "后一页",
                current_page: page - 1
            });

            if (context.find(".ml10.total-items").length !== 0) {
                context.find(".ml10.total-items").html('总条数: ' + result.data.page.totalRows);
            } else {
                context.find("#Pagination").after('<span class="ml10 total-items"> 总条数: ' + result.data.page.totalRows + '</span>');
            }
        }
        function pageselectCallback(page_index, jq) {
            page = page_index + 1;
            console.log(page);
            bindData(true);
            return false;
        }
        function formatHtml(result, context) {
            var source = context.find("#table-template").html();
            var template = Handlebars.compile(source);
            var html = template(result);
            context.find('#table tbody').html(html);
        }

        if ($("#table-template").size()) {
            bindData();
        }
        $('.js-search').click(function() {
            bindData();
        });
    });

    // 删除提交
    $('body').on('click', '.js-delete', function() {
        var content = $(this).data('content');
        var sectitle = $(this).data('sectitle') || '';
        if (sectitle) {
            content += '<div class="confirm-title2">' + sectitle + '</div>';
        }
        var data = {
            id: $(this).data("id")
        };
        var url = $(this).attr('href');
        $.confirm(content, null, function(type) {
            var _this = this;
            if (type == 'yes') {
                $.post(url, data, function(data) {
                    if (data && data.success) {
                        $.alert('删除成功!');
                        page = 1;
                        bindData();
                        _this.hide();
                    } else {
                        $.alert('删除失败!');
                    }
                }, 'json');
            } else {
                _this.hide();
            }
        });
        return false;
    });

    //if ($("#ajaxloading").size() == 0) {
    //    $('body').append('<div id="ajaxloading"><i></i></div>');
    //}
    //if(typeof noloading =="undefined"){
    //    $.ajaxSetup({
    //        beforeSend:function(){
    //            $('#ajaxloading').show();
    //        },
    //        complete:function(){
    //            $('#ajaxloading').hide();
    //        },error:function(){
    //            $('#ajaxloading').hide();
    //        }
    //    });
    //}

});