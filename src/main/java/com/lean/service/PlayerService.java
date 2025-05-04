package com.lean.service;

import com.lean.domain.Player;
import com.lean.domain.response.PlayerProfileResponse;
import com.lean.domain.response.PlayerResponse;

import java.util.List;

public interface PlayerService {
    void saveAllPlayers(List<Player> players);
    List<PlayerResponse> getAllPlayers();
    PlayerProfileResponse getPlayerById(Long playerId);
}
