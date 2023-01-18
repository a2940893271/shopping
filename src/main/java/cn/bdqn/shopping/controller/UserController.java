package cn.bdqn.shopping.controller;

import cn.bdqn.shopping.utils.Result;
import cn.bdqn.shopping.vo.ChangePasswordReq;
import cn.bdqn.shopping.vo.LoginReq;
import cn.bdqn.shopping.vo.LoginResult;
import cn.bdqn.shopping.entity.User;
import cn.bdqn.shopping.service.UserService;
import cn.bdqn.shopping.vo.RegisterReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
@ResponseBody
@Api(tags = "用户服务相关接口描叙")
@RequestMapping("/user")
//http://localhost:8080/doc.html   Knife4j用这个访问
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    //注册
    @PostMapping("/register")
    @ApiOperation(value = "用户注册", notes = "注册账号和密码的接口")
    public Result<?> register(RegisterReq registerReq) {
        Result<?> result = userService.register(registerReq);
        return result;
    }

    //登录
    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "登录的接口")
    public Result<LoginResult> login(LoginReq loginReq, HttpServletResponse response) {
        // 登录
        Result<LoginResult> result = userService.login(loginReq);
        return result;
    }

    //改密
    @PutMapping("/password")
    @ApiOperation(value = "修改密码", notes = "修改密码的接口")
    public Result<?> changePassWord(ChangePasswordReq changePasswordReq, HttpServletRequest request) {
        // 取出jwt中的用户
        User jwtUser = (User) request.getAttribute("user");
        if (jwtUser == null) {
            return Result.error("token不正确");
        }
        // 合并jwt中用户的用户名与传入用户的新密码
        // 此处不能直接使用传入的用户名，防止恶意修改其他用户的密码
        User user = new User();
        user.setId(jwtUser.getId());
        // 改密
        Result<?> result = userService.changePassWord(user, changePasswordReq);
        return result;
    }
}
