package com.yyh.yyseckill.product.dao;

import com.yyh.yyseckill.product.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息表
 * 
 * @author yyh
 * @email 469268632qq.com
 * @date 2020-07-11 23:44:20
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
