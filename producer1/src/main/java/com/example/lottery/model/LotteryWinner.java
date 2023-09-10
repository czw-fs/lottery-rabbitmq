package com.example.lottery.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class LotteryWinner implements Serializable {
    private static final long serialVersionUID = 2L;
    private Long id;
    private Long awardId;
    private Long participantId;

    private Participant participant;

    private Award award;

}
