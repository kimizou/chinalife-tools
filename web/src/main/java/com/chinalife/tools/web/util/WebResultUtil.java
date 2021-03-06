package com.chinalife.tools.web.util;

import com.chinalife.tools.common.exception.BizException;
import com.chinalife.tools.web.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.lang.reflect.Field;

/**
 * 业务异常转为页面结果对象
 * 
 * @author zhenggaojie 2015-05-18.
 */
public class WebResultUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebResultUtil.class);

    private WebResultUtil(){

    }

    public static WebResult bizExceptionToWebResult(BizException bizException) {
        WebResult webResult = new WebResult();
        webResult.setSuccess(false);
        webResult.setMessage(bizException.getMessage());
        webResult.setResultCode(bizException.getCode());
        return webResult;
    }

    /**
     * Wrap webResult into MappintJackson2jsonView To keep the convert result JsonView structure same with WebResult
     * 
     * @param webResult
     * @return
     */
    public static View wrapIntoJsonView(WebResult<?> webResult) {

        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        Class<?> webResultClass = WebResult.class;

        Field[] fields = webResultClass.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                jsonView.addStaticAttribute(field.getName(), field.get(webResult));
            } catch (IllegalArgumentException | IllegalAccessException exception) {
                exception.printStackTrace();
                LOGGER.error("捕获异常后，转化出错！", exception);
            }
        }

        return jsonView;
    }

}
