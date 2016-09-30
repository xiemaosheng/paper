package com.nd.uc.config;

import com.google.common.collect.Lists;
import com.nd.user.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类名称：
 * 类描述：
 * 创建人：newtonk
 * 创建日期：2016/9/10 0010
 */
@Service
public class UserInfoService {

    @Autowired
    private UserRepository userRepository;

    public List<String> getUserRoleList(String userId) {
        return Lists.newArrayList(userRepository.findUserRoles(userId));
    }
}
