package com.example.lottery.mapper;

import com.example.lottery.model.Award;
import com.example.lottery.model.LotteryWinner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: fs
 * @date: 2023/9/10 14:50
 * @Description: everything is ok
 */
public interface LotteryMapper {
    List<Award> getAllAward2();

    List<LotteryWinner> selectWinner2();

    Integer insert2(LotteryWinner lotteryWinner);


    List<LotteryWinner> selectWinnerByAwardId1(@Param("awardId") Long awardId);

    Award selectAwardCount(@Param("awardId") Long awardId);
}
