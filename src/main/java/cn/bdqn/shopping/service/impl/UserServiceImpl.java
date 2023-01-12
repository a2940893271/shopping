package cn.bdqn.shopping.service.impl;

import cn.bdqn.shopping.bean.ServiceRes;
import cn.bdqn.shopping.bean.User;
import cn.bdqn.shopping.mapper.UserMapper;
import cn.bdqn.shopping.service.UserService;
import cn.bdqn.shopping.utils.JwtUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
   private UserMapper userMapper;

    /**
     * 注册
     * @param user 用户类
     * @return ServiceRes
     */
    @Override
    public ServiceRes register(User user) {

        // 判断用户名是否唯一
        if(this.checkUserNameIsUnique(user)) {

            // 判断用户名密码是否合法
            if(this.checkUserNameAndPassword(user)) {

                // 密码MD5加密
                user.setPassWord(this.MD5Code(user.getPassWord()));
                // 加入创建时间
                user.setCreateTime(new Date());
                // 入库
                userMapper.insert(user);
                return new ServiceRes(1, "注册成功");

            } else return new ServiceRes(-1, "用户名或密码不合法");

        } else return new ServiceRes(-1, "用户名已存在");

    }

    /**
     * 登录
     * @param user 用户类
     * @return ServiceRes
     */
    @Override
    public ServiceRes login(User user) {

        // 判断用户名密码是否合法
        if(this.checkUserNameAndPassword(user)) {

            // 密码MD5加密
            user.setPassWord(this.MD5Code(user.getPassWord()));

            // 检查用户是否存在
            User curUser = this.checkUserIsExit(user);
            if(curUser!=null) {

                // 更新用户最后登录时间
                curUser.setLastLogin(new Date());
                userMapper.updateById(curUser);

                // 生成jwt
                Map<String, String> payload = new HashMap<>();
                payload.put("userId", curUser.getId().toString()); // 加入一些非敏感的用户信息
                payload.put("userName", curUser.getUserName());    // 加入一些非敏感的用户信息
                String jwt = JwtUtil.generateToken(payload);
                return new ServiceRes(1, "登录成功", jwt);

            } else return new ServiceRes(-1, "用户名或密码错误");

        } else return new ServiceRes(-1, "用户名或密码不合法");

    }

    /**
     * 改密业务
     * @return ServiceRes
     */
    @Override
    public ServiceRes changePassWord(User user) {
        if(this.updatePassWord(user)) return new ServiceRes(1, "改密成功");
        else return new ServiceRes(-1, "改密失败");
    }

    /**
     * 非对称加密
     * @param text 明文
     * @return 密文
     */
    private String MD5Code(String text) {
        return DigestUtils.md5DigestAsHex(text.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 修改密码方法
     * @param user 传入用户名和新密码
     * @return 改密成功返回 true 失败返回 false
     */
    private Boolean updatePassWord(User user) {
        // 密码非对称加密
        user.setPassWord(this.MD5Code(user.getPassWord()));
        // 更新密码
        return userMapper.updateById(user)>0;
    }

    /**
     * 检查用户是否存在【用户名密码相同】
     * @param user 用户类
     * @return 用户存在返回 用户对象 不存在返回 null
     */
    private User checkUserIsExit(User user) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUserName, user.getUserName());
        lqw.eq(User::getPassWord, user.getPassWord());
        return userMapper.selectOne(lqw);
    }

    /**
     * 判断用户名是否唯一
     * @param user 用户类
     * @return 唯一返回 true 不唯一返回 false
     */
    private Boolean checkUserNameIsUnique(User user) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUserName, user.getUserName());
        List<User> userList = userMapper.selectList(lqw);
        return userList.size() == 0;
    }

    /**
     * 判断用户名密码是否合法
     * @param user 用户类
     * @return 满足 【英文字母、数字、下划线】 返回 true，否则返回 false
     */
    private Boolean checkUserNameAndPassword(User user) {
        String regex = "^[_a-z0-9A-Z]+$";
        return user.getUserName().matches(regex) && user.getPassWord().matches(regex);
    }


}


