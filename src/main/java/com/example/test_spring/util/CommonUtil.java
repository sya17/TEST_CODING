package com.example.test_spring.util;

import com.example.test_spring.constant.ResponRequestConstant;
import com.example.test_spring.util.response.ResponseUtils;

import jakarta.servlet.http.HttpServletRequest;

public class CommonUtil {

    public static CommonUtil INSTANCE = new CommonUtil();

    public static CommonUtil getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        } else {
            return new CommonUtil();
        }
    }
    
    public boolean isNullOrEmpty(String val) {
        return StringUtil.getInstance().isNullOrEmpty(val);
    }

    public String getPathUri(HttpServletRequest request) {
        return request.getRequestURL().toString();
    }

    public ResponseUtils setGeneralResponse(
            Object data,
            String path,
            String method,
            String message) {
        ResponseUtils resUtils = new ResponseUtils();
        resUtils.setEndpoint(path);
        if (!isNullOrEmpty(message)) {
            resUtils.setMessage(message);
        } else {
            if (!isNullOrEmpty(method)) {
                switch (method) {
                    case ResponRequestConstant.MethodConstant.POST:
                        resUtils.setMessage("Create Successfully");
                        break;
                    case ResponRequestConstant.MethodConstant.PUT:
                        resUtils.setMessage("Updated Successfully");
                        break;
                    case ResponRequestConstant.MethodConstant.GET:
                        resUtils.setMessage("Get Successfully");
                        break;
                    case ResponRequestConstant.MethodConstant.DELETE:
                        resUtils.setMessage("Delete Successfully");
                        break;
                    case ResponRequestConstant.MethodConstant.ERROR:
                        resUtils.setMessage("There is an Error");
                        break;
                    default:
                        resUtils.setMessage(message);
                        break;
                }
            }
        }
        resUtils.setData(data);
        return resUtils;
    }
}
