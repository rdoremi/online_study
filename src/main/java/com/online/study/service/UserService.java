package com.online.study.service;

import com.online.study.common.ServerResponse;
import com.online.study.entity.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2020-04-13 17:12:36
 */
public interface UserService {

   ServerResponse login(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException;

   ServerResponse register(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException;

}