package com.yyh.yyseckill.seckill.controller;

import com.yyh.common.utils.R;
import com.yyh.yyseckill.seckill.service.PutOnShellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 上架Controller
 *
 * @author yeyuhua
 * @version 1.0
 * @created 2020/7/12 1:59 下午
 */
@RestController
@RequestMapping("/putonshell")
public class PutOnShellController {

    @Autowired
    private PutOnShellService putOnShellService;

    /**
     * 手动执行上架
     *
     * @return
     */
    @GetMapping("/execute")
    public R putOnShell() {
        putOnShellService.putOnShellLatest3Days();
        return R.ok();
    }
}
