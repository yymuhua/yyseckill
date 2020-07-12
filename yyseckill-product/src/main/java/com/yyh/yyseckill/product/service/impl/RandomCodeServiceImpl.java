package com.yyh.yyseckill.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyh.common.utils.PageUtils;
import com.yyh.common.utils.Query;

import com.yyh.yyseckill.product.dao.RandomCodeDao;
import com.yyh.yyseckill.product.entity.RandomCodeEntity;
import com.yyh.yyseckill.product.service.RandomCodeService;


@Service("randomCodeService")
public class RandomCodeServiceImpl extends ServiceImpl<RandomCodeDao, RandomCodeEntity> implements RandomCodeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RandomCodeEntity> page = this.page(
                new Query<RandomCodeEntity>().getPage(params),
                new QueryWrapper<RandomCodeEntity>()
        );

        return new PageUtils(page);
    }

}