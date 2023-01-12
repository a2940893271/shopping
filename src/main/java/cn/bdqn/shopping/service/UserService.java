package cn.bdqn.shopping.service;

import cn.bdqn.shopping.bean.ServiceRes;
import cn.bdqn.shopping.bean.User;

public interface UserService {
    //注册
    ServiceRes register(User user);

    //登录
    ServiceRes login(User user);

    //改密
    ServiceRes changePassWord(User user);
}
