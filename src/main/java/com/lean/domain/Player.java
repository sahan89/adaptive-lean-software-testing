package com.lean.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "player")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long playerId;
    private Integer ranking;
    private Long seasonId;
    private Long tournamentId;
    private Long tournamentRegionId;
    private String tournamentRegionCode;
    private String regionCode;
    private String tournamentName;
    private String tournamentShortName;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private Boolean isOpta;
    private Long teamId;
    private String teamName;
    private String playedPositions;
    private Integer age;
    private Integer height;
    private Integer weight;
    private String positionText;
    private Integer apps;
    private Integer subOn;
    private Integer minsPlayed;
    private Double rating;
    private Integer goal;
    private Integer assistTotal;
    private Integer yellowCard;
    private Integer redCard;
    private Double shotsPerGame;
    private Double aerialWonPerGame;
    private Integer manOfTheMatch;
    private String name;
    private Boolean isManOfTheMatch;
    private String playedPositionsShort;
    private Double passSuccess;
}
