package com.example.lottery.mapper;


import com.example.lottery.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: fs
 * @date: 2023/9/10 14:50
 * @Description: everything is ok
 */
public interface LotteryMapper {
    List<Award> getAllAward1();

    List<LotteryWinner> selectWinner1();


    List<LotteryWinner> selectWinnerByAwardId(@Param("awardId") Long awardId);
}
