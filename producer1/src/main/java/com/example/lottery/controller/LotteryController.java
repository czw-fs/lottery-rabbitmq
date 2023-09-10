package com.example.lottery.controller;

import com.example.lottery.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: fs
 * @date: 2023/9/10 14:00
 * @Description: everything is ok
 */
@RestController
public class LotteryController {

    @Autowired
    private LotteryService lotteryService;

    /**
     * 用户抽奖接口
     * @return
     */

    @GetMapping("/lottery")
    public String getLottery(Long userId){
        //用户点击抽奖的逻辑
        return lotteryService.getLottery(userId);
    }



}
