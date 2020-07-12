package com.yyh.yyseckill.seckill.feign;

import com.yyh.common.utils.R;
import com.yyh.yyseckill.seckill.vo.SeckillSessionVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author yeyuhua
 * @version 1.0
 * @created 2020/7/12 12:14 下午
 */
@FeignClient("yyseckill-product")
public interface SeckillSessionFeignService {
    /**
     * 远程调用商品服务的获取最近三天秒杀活动信息接口
     * @return
     */
    @GetMapping("/seckillsession/latest3DaysSessions")
    R getLatest3DaysSessions();

}
