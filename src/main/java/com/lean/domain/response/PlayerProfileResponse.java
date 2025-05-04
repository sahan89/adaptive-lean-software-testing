package com.lean.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerProfileResponse extends PlayerResponse {
    private Long seasonId;
    private long tournamentId;
    private long tournamentRegionId;
    private String tournamentRegionCode;
    private String regionCode;
    private String tournamentName;
    private String tournamentShortName;
    private String firstName;
    private String lastName;
    private boolean isOpta;
    private int apps;
    private int subOn;
    private int minsPlayed;
    private int assistTotal;
    private int yellowCard;
    private int redCard;
    private double shotsPerGame;
    private double aerialWonPerGame;
    private int manOfTheMatch;
    private boolean isManOfTheMatch;
    private String playedPositionsShort;
    private double passSuccess;
}
