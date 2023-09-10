package com.example.lottery.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.lottery.mapper.LotteryMapper;
import com.example.lottery.model.Award;
import com.example.lottery.model.LotteryWinner;
import com.example.lottery.service.LotteryService;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class LotteryServiceImpl implements LotteryService {

    @Autowired
    private LotteryMapper lotteryMapper;

    @Autowired
    private RabbitOperations rabbitOperations;

    /**
     * 抽奖逻辑
     * @param userId
     */
    Object lock = new Object();
    @Override
    public String getLottery(Long userId) {

        synchronized (lock) {
            //获取所有奖项
            List<Award> awardList = lotteryMapper.getAllAward1();
            //获取随机奖项的id：一等奖，二等奖，三等奖，没中奖
            Long awardtId = getAward(awardList);
            //是否还有奖
            if(awardtId != -1){
                LotteryWinner lotteryWinner = new LotteryWinner();
                lotteryWinner.setParticipantId(userId);
                lotteryWinner.setAwardId(awardtId);
                //发送消息
                rabbitOperations.convertAndSend("fs.direct","fs_direct", JSON.toJSONString(lotteryWinner));
                return "成功参与抽奖";
            }else {
                return "奖品已抽完";
            }
        }
    }

    public Long getAward(List<Award> awardList) {

        ArrayList<Award> awards1 = new ArrayList<>(awardList);

        for (Award award : awardList) {
            if(award.getId() != 4){
                List<LotteryWinner> winnerList = lotteryMapper.selectWinnerByAwardId(award.getId());
                if(winnerList.size() == award.getCount()){
                    awards1.remove(award);
                }
            }
        }
        if(awards1.size() == 1){
            return -1L;
        }
        int randomIndex  = new Random().nextInt(awards1.size());
        return awards1.get(randomIndex).getId();
    }



}
