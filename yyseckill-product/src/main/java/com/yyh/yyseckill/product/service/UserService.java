package com.yyh.yyseckill.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yyh.common.utils.PageUtils;
import com.yyh.yyseckill.product.entity.UserEntity;

import java.util.Map;

/**
 * 用户信息表
 *
 * @author yyh
 * @email 469268632qq.com
 * @date 2020-07-11 23:44:20
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

