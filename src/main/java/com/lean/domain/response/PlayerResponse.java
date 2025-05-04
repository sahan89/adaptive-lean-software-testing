package com.lean.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResponse {
    private Long playerId;
    private int ranking;
    private Boolean isActive;
    private long teamId;
    private String teamName;
    private String playedPositions;
    private int age;
    private int height;
    private int weight;
    private String positionText;
    private double rating;
    private int goal;
    private String name;
}
