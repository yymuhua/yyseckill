package com.yyh.yyseckill.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yyh.common.utils.PageUtils;
import com.yyh.yyseckill.product.entity.RandomCodeEntity;

import java.util.Map;

/**
 * @author yyh
 * @email 469268632qq.com
 * @date 2020-07-11 23:44:21
 */
public interface RandomCodeService extends IService<RandomCodeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

