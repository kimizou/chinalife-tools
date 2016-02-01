package com.chinalife.tools.web.exception;

import com.chinalife.tools.common.exception.BizException;
import com.chinalife.tools.web.WebResult;
import com.chinalife.tools.web.util.HttpRequestHelper;
import com.chinalife.tools.web.util.WebResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerExceptionResolver extends AbstractHandlerExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(HandlerExceptionResolver.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                              Exception ex) {

        if (ex instanceof BizException) {
            LOGGER.warn("异常捕获:", ex);
        } else {
            LOGGER.error("异常捕获:", ex);
        }

        ModelAndView resultModelAndView = new ModelAndView();

        if (HttpRequestHelper.isAjaxRequest(request) && HttpRequestHelper.canReceivedJsonData(request)) {
            WebResult<?> webResult;
            if (ex instanceof BizException) {
                webResult = WebResultUtil.bizExceptionToWebResult((BizException) ex);
            } else {
                webResult = new WebResult<>(null, "系统错误，请重试", false);
            }
            resultModelAndView.setView(WebResultUtil.wrapIntoJsonView(webResult));

        } else {
            if (ex instanceof BizException) {
                resultModelAndView.addObject("errorMsg",ex.getMessage());
            } else {
                resultModelAndView.addObject("errorMsg","系统错误，请重试");
            }
            resultModelAndView.setViewName("error/error");
        }

        return resultModelAndView;

    }
}
