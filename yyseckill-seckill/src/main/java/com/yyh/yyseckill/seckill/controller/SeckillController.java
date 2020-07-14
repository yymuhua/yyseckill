package com.yyh.yyseckill.seckill.controller;

import com.yyh.common.utils.R;
import com.yyh.yyseckill.seckill.dto.KillDto;
import com.yyh.yyseckill.seckill.service.PutOnShellService;
import com.yyh.yyseckill.seckill.service.SeckillService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 秒杀Controller
 * @author yeyuhua
 * @version 1.0
 * @created 2020/7/12 12:13 上午
 */
@RestController
@RequestMapping("/kill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;


    /**
     * 商品秒杀核心业务逻辑-用于压力测试
     * @return
     */
    @PostMapping(value = "/execute")
    public R execute(@RequestBody KillDto killDto) {
        String orderNo = seckillService.kill(killDto);
        if (StringUtils.isEmpty(orderNo)) {
            return R.ok("秒杀失败");
        }
        return R.ok("秒杀成功").setData(orderNo);
    }
}
