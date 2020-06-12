package com.online.study.service.impl;

import com.online.study.common.ResponseCode;
import com.online.study.common.ServerResponse;
import com.online.study.entity.User;
import com.online.study.dao.UserDao;
import com.online.study.service.UserService;
import com.online.study.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2020-04-13 17:12:36
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public ServerResponse login(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {


        User tUser = userDao.loginBytel(user.getTel());

        if (tUser != null){

            if (Md5.checkPassword(user.getPassword(),tUser.getPassword())){
                tUser.setPassword("");
                return ServerResponse.createBySuccessCodeMessage(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc(), tUser);

            }else {
                return ServerResponse.createByErrorMessage("密码错误");
            }
        }

        return ServerResponse.createByErrorMessage("用户不存在！");
    }

    @Override
    public ServerResponse register(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        int checked = userDao.checked(user.getTel());
        if (checked > 0){
            return ServerResponse.createByErrorMessage("该手机号已注册!");
        }
        user.setPassword(Md5.EncoderByMd5(user.getPassword()));
        int rowCount = userDao.register(user);
        if (rowCount > 0){
            return ServerResponse.createBySuccessMessage("SUCCESS");
        }

        return ServerResponse.createByErrorMessage("注册失败");
    }
}