package com.example.lottery.comsumer;

import com.alibaba.fastjson.JSON;
import com.example.lottery.mapper.LotteryMapper;
import com.example.lottery.model.Award;
import com.example.lottery.model.LotteryWinner;
import com.example.lottery.service.impl.LotteryServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: fs
 * @date: 2023/9/10 15:29
 * @Description: everything is ok
 */
@Component
public class RabbitConsumer {

    @Autowired
    private LotteryServiceImpl lotteryService;

    @Autowired
    private LotteryMapper lotteryMapper;

    @RabbitListener(queues = {"direct1"})
    public void consumer(String lotteryWinner) {

        System.out.println(lotteryWinner);
        LotteryWinner lotteryWinner1 = JSON.parseObject(lotteryWinner, LotteryWinner.class);
        if(haveAward()){
            Long awardId = lotteryWinner1.getAwardId();
            if(awardId != 4){
                lotteryService.insertWinner(lotteryWinner1);

                List<LotteryWinner> winnerList = lotteryMapper.selectWinnerByAwardId1(lotteryWinner1.getAwardId());
                Award award = lotteryMapper.selectAwardCount(lotteryWinner1.getAwardId());
                Integer count = award.getCount();


                System.out.println("恭喜中"+awardId+"等奖"+"---"+awardId+"等奖还剩" + (count - winnerList.size()) + "个");
            }else {
                System.out.println("没中奖");
            }

        }else{
            System.out.println("奖已抽完");
        }
    }

    public Boolean haveAward(){
        List<LotteryWinner> winnerList = lotteryMapper.selectWinner2();
        List<LotteryWinner> l1 = winnerList.stream().filter(item ->{return item.getId() == 1;}).collect(Collectors.toList());
        List<LotteryWinner> l2 = winnerList.stream().filter(item ->{return item.getId() == 2;}).collect(Collectors.toList());
        List<LotteryWinner> l3 = winnerList.stream().filter(item ->{return item.getId() == 3;}).collect(Collectors.toList());
        List<LotteryWinner> l4 = winnerList.stream().filter(item ->{return item.getId() == 4;}).collect(Collectors.toList());

        if(l1.size() <= 10 || l2.size() <= 20 || l3.size() <= 30){
            return true;
        }else {
            return false;
        }
    }

}
