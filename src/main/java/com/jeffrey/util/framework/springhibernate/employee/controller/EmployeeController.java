package com.jeffrey.util.framework.springhibernate.employee.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
@Api("员工信息")
public class EmployeeController {

    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.GET, produces ="application/text;charset=utf-8")
    @ApiOperation(value = "根据用户编号获取用户名", notes = "不存在则返回未知")
    @ApiImplicitParam(paramType = "query", name = "userNumber", value = "用户编号", required = true, dataType = "int")
    public String getUserName(@RequestParam int userNumber) {
        if (userNumber == 1) {
            return "Test1";
        } else if (userNumber == 2) {
            return "Test2";
        } else {
            return "unknown";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/updatePassword", method = RequestMethod.GET, produces ="application/text;charset=utf-8")
    @ApiOperation(value = "修改用户密码", notes = "根据用户id修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "password", value = "旧密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "newPassword", value = "新密码", required = true, dataType = "String")
    })
    public String updatePassword(@RequestParam(value = "userId") Integer userId, @RequestParam(value = "password") String password,
                                 @RequestParam(value = "newPassword") String newPassword) {
        if (userId <= 0 || userId > 2) {
            return "未知的用户";
        }
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(newPassword)) {
            return "密码不能为空";
        }
        if (password.equals(newPassword)) {
            return "新旧密码不能相同";
        }
        return "密码修改成功!";
    }
}
