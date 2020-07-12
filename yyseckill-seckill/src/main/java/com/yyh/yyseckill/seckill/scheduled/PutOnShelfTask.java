package com.yyh.yyseckill.seckill.scheduled;

import com.yyh.yyseckill.seckill.service.PutOnShellService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author yeyuhua
 * @version 1.0
 * @created 2020/7/12 10:53 上午
 */
@Slf4j
@Component
@EnableAsync
@EnableScheduling
public class PutOnShelfTask {

    @Autowired
    private PutOnShellService putOnShellService;

    /**
     * 每天晚上3点上架最近三天需要秒杀的商品
     */
    @Async
    @Scheduled(cron = "0 0 3 * * ?")
    public void putOnShellLatest3Days() {
        putOnShellService.putOnShellLatest3Days();
    }

}
