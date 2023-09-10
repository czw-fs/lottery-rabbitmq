package com.example.lottery.service.impl;

import com.example.lottery.mapper.LotteryMapper;
import com.example.lottery.model.Award;
import com.example.lottery.model.LotteryWinner;
import com.example.lottery.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @author: fs
 * @date: 2023/9/10 14:01
 * @Description: everything is ok
 */
@Service
public class LotteryServiceImpl implements LotteryService {

    @Autowired
    private LotteryMapper lotteryMapper;

    @Override
    public Integer insertWinner(LotteryWinner lotteryWinner) {
        return lotteryMapper.insert2(lotteryWinner);
    }

    public Long getAward(List<Award> awardList) {
        int randomIndex  = new Random().nextInt(awardList.size());
        return awardList.get(randomIndex).getId();
    }



}
