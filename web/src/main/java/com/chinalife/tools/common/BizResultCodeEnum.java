package com.chinalife.tools.common;

/**
 * 操作返回码枚举
 * 
 * @author zhenggaojie 2015-5-15.
 */
public enum BizResultCodeEnum {

    /* 操作成功 */
    SUCCESS("SUCCESS", 1000, "操作成功"),

    /* 系统错误 */
    SYSTEM_FAILURE("SYSTEM_FAILURE", 1001, "系统错误，稍后再试"),

    /* 参数为空 */
    NULL_ARGUMENT("NULL_ARGUMENT", 1002, "参数为空"),

    /* 新增的参数已经存在(唯一性约束) */
    DUPLICATE_KEY("DUPLICATE_KEY", 1003, "新增的参数已经存在(唯一性约束)"),

    /* 参数不正确 */
    ILLEGAL_ARGUMENT("ILLEGAL_ARGUMENT", 1004, "参数不正确"),

    /* 逻辑错误 */
    LOGIC_ERROR("LOGIC_ERROR", 1005, "逻辑错误"),

    /** 外部接口调用失败 */
    DEPEND_FAILURE("DEPEND_FAILURE", 1006, "依赖外部接口失败"),

    /** 认证失败 */
    AUTHORIZE_FAILURE("AUTHORIZE_FAIL", 1007, "用户认证失败"),

    /** 连接超时 */
    SESSION_TIMEOUT("SESSION_TIMEOUT", 1008, "会话连接超时"),

    /** 连接超时 */
    DEAPRTMENT_EXIST("DEAPRTMENT_EXIST", 1009, "此部门已经存在"),

    /** 验证失败 */
    VALIDATE_FAILURE("VALIDATE_FAILURE", 1010, "验证失败"),

    /*重复提交错误*/
    RESUBMIT_ERROR("RESUBMIT_ERROR", 1011, "重复提交错误"),
    
    /** 数据不完整 */
    DATA_NOT_COMPLETE("DATA_NOT_COMPLETE",1012,"数据不完整"),
    /** 功能重复运行 */
    FUNCTION_REPEATRUN("FUNCTION_REPEATRUN",1013,"重复运行，请稍后再试"),

    NO_RELATED_DATA("NO_RELATED_DATA",1014,"没有相关内容"),

    EXPORT_FAIL("EXPORT_FAIL",1015,"数据异常，导出失败"),

    /* 商品管理相关 */
    HOT_GOODS_COUNT_LACK("VALIDATE_FAILURE",1101,"商品不满20件，无法提交修改"),

    /* 供应商入驻相关 */
    SUPPLEMENT_DETAILS_AUDIT_NOT_FINISHED("SUPPLEMENT_DETAILS_AUDIT_NOT_FINISHED", 1102, "尚有补仓明细未审核完成"),

    /** 售后相关 **/
    AFTER_SALE_RESHIP_SN_INVALID("AFTER_SALE_RESHIP_SN_INVALID",1211,"退换货订单编号不存在"),

    AFTER_SALE_NOT_FOUND_REFUND_ORDER("AFTER_SALE_NOT_FOUND_REFUND_ORDER",1212,"未找到退货单"),
    
    GOODS_SUPPLEMENT_INAUDIT("GOODS_SUPPLEMENT_INAUDIT", 1103, " 该商品正在补仓审核中，供货价不可变更！"),
    
    /*库存不足*/
    AFTER_SALE_LOW_STOCK("AFTER_SALE_LOW_STOCK",1201 ,"库存不足" ),
    
    /*业绩商品相关*/
    PERFORMANCE_ACITITY_EXIST("PERFORMANCE_ACITITY_EXIST",1302,"同一个商品不能同时设置两个业绩活动!"),

    ORDER_NOT_FOUND("ORDER_NOT_FOUND",1303,"订单查询失败"),

    WEIDIAN_NOT_FOUND("WEIDIAN_NOT_FOUND",1304,"微店查询失败"),

    PERFORMANCE_OWNER_NOT_FOUND("PERFORMANCE_OWNER_NOT_FOUND",1305,"业绩人查询失败"),

    AFTER_SALE_ORDER_NOT_FOUND("AFTER_SALE_ORDER_NOT_FOUND",1306,"售后订单查询失败"),
    
    ACTIVITY_NOT_FOUND("ACTIVITY_NOT_FOUND",1307,"活动查询失败"),
    
    ACITITY_ERROR_START("ACITITY_ERROR_START",1308,"开始时间不得早于当前系统时间"),
    
    ACITITY_ERROR_END("ACITITY_ERROR_END",1309,"结束时间不得早于开始时间"),
	
	ACITITY_REWARD_NUM_ZERO("ACITITY_REWARD_NUM_ZERO",1320,"奖励发放实际值为0"),

    /*新品推荐*/
    KALEMAO_ACTIVITY_PLAY_ORDER_EXISTS("KALEMAO_ACTIVITY_PLAY_ORDER_EXISTS", 1401, "轮播序号已存在"),
    
    /*订单修改*/
    ORDER_ADDRESS_NOT_ALLOW_MODIFY("ORDER_ADDRESS_NOT_ALLOW_MODIFY",2011,"订单地址不能修改"),
    /*地址修改参数相同*/
    ORDER_ADDRESS_MODIFY_SAME("ORDER_ADDRESS_MODIFY_SAME",2012,"新旧内容相同"),
    /*运行未结束*/
    RUN_NOT_FINISH("RUN_NOT_FINISH",9997,"");
    /** 枚举值 */
    private final String code;

    /** 数值型错误码 */
    private final int    codeNumber;

    /** 枚举信息 */
    private final String description;

    /**
     * 构造函数
     *
     * @param code 枚举值
     * @param codeNumber 数值型错误码
     * @param description 枚举信息
     */
    BizResultCodeEnum(String code, int codeNumber, String description){
        this.code = code;
        this.codeNumber = codeNumber;
        this.description = description;
    }

    /**
     * 根据code获取枚举
     * 
     * @param code
     * @return
     */
    public static BizResultCodeEnum getByCode(String code) {
        for (BizResultCodeEnum item : values()) {
            if (code.equals(item.getCode())) {
                return item;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public int getCodeNumber() {
        return codeNumber;
    }
}
