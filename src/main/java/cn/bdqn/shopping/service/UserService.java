package cn.bdqn.shopping.service;

import cn.bdqn.shopping.utils.Result;
import cn.bdqn.shopping.vo.ChangePasswordReq;
import cn.bdqn.shopping.vo.LoginReq;
import cn.bdqn.shopping.vo.LoginResult;
import cn.bdqn.shopping.entity.User;
import cn.bdqn.shopping.vo.RegisterReq;

public interface UserService {
    //注册
    Result<?> register(RegisterReq registerReq);

    //登录
    Result<LoginResult> login(LoginReq loginReq);

    //改密
    Result<?> changePassWord(User user, ChangePasswordReq changePasswordReq);
}
