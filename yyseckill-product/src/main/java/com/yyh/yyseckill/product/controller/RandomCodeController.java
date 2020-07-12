package com.yyh.yyseckill.product.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yyh.yyseckill.product.entity.RandomCodeEntity;
import com.yyh.yyseckill.product.service.RandomCodeService;
import com.yyh.common.utils.PageUtils;
import com.yyh.common.utils.R;



/**
 * 
 *
 * @author yyh
 * @email 469268632qq.com
 * @date 2020-07-11 23:44:21
 */
@RestController
@RequestMapping("/randomcode")
public class RandomCodeController {
    @Autowired
    private RandomCodeService randomCodeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = randomCodeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		RandomCodeEntity randomCode = randomCodeService.getById(id);

        return R.ok().put("randomCode", randomCode);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody RandomCodeEntity randomCode){
		randomCodeService.save(randomCode);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody RandomCodeEntity randomCode){
		randomCodeService.updateById(randomCode);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		randomCodeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
