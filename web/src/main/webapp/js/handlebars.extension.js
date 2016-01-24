Handlebars.registerHelper('equal', function(v1, v2, options) {
    if (v1 == v2) {
        return options.fn(this);
    } else {
        return options.inverse(this); //不满足条件执行{{else}}部分
    }
});
Handlebars.registerHelper('notEqual', function(v1, v2, options) {
    if (v1 != v2) {
        return options.fn(this);
    } else {
        return options.inverse(this); //不满足条件执行{{else}}部分
    }
});
//注册if判断或者的并列条件
Handlebars.registerHelper('if_or', function(v1, v2,v3,v4, options) {
    if (v1 == v2||v3==v4) {
        return options.fn(this);
    } else {
        return options.inverse(this);
    }
});
//注册百分比计算
Handlebars.registerHelper('percent', function(number1,number2,saveNum) {
    if (number2 == 0){
        return "-";
    }
    saveNum = saveNum || 2;
    return (number1*100/number2).toFixed(saveNum);
});
Handlebars.registerHelper('over', function(v1, v2, options) {
    if (v1 > v2) {
        return options.fn(this);
    } else {
        return options.inverse(this);
    }
});
Handlebars.registerHelper('addOne', function(index) {
    return index + 1;
});
Handlebars.registerHelper('addTwo', function(index) {
    return index + 2;
});
Handlebars.registerHelper('equalLength', function(data,v1) {
    if (v1 == data.length) {
        return options.fn(this);
    } else {
        return options.inverse(this);
    }
});
//将数字保留两位小数显示
Handlebars.registerHelper('float', function (num) {
    return parseFloat(num).toFixed(2);
});
//时间格式化yyyy-MM-dd HH:mm:ss
Handlebars.registerHelper('formatDate', function (data) {
    return formatDate(data);
});
//时间格式化yyyy-MM-dd HH:mm:ss
Handlebars.registerHelper('formatIntDate', function (dataInt,formatStr) {
    formatStr = formatStr || "yyyy-MM-dd HH:mm:ss";
    return formatDate(new Date(dataInt*1000),formatStr);
});
Handlebars.registerHelper('isNull', function (v1,options) {
    if(v1 == null){
        return options.fn(this);
    }else {
        return options.inverse(this);
    }
});
Handlebars.registerHelper('isNotNull', function (v1,options) {
    if(v1 != null){
        return options.fn(this);
    }else {
        return options.inverse(this);
    }
});