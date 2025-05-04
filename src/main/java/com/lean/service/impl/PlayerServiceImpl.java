package com.lean.service.impl;

import com.lean.domain.Player;
import com.lean.domain.response.PlayerProfileResponse;
import com.lean.domain.response.PlayerResponse;
import com.lean.exceptiondomain.DataNotFoundException;
import com.lean.exceptiondomain.constant.ExceptionConstant;
import com.lean.repository.PlayerRepository;
import com.lean.service.PlayerService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, ModelMapper modelMapper) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveAllPlayers(List<Player> players) {
        logger.info("--ENTER-- saveAllPlayers --");
        this.playerRepository.saveAll(players);
    }

    @Override
    public List<PlayerResponse> getAllPlayers() {
        List<Player> playerList = this.playerRepository.findAll();

        return playerList.stream()
                .map(this::mapEntityToPlayerResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PlayerProfileResponse getPlayerById(Long playerId) {
        logger.info("--ENTER-- getPlayerById -- {}", playerId);

        Player player = this.playerRepository.findPlayerByPlayerId(playerId);
        if (player == null) {
            throw new DataNotFoundException(ExceptionConstant.PLAYER_NOT_FOUND);
        }
        return mapEntityToPlayerProfileResponse(player);
    }

    private PlayerProfileResponse mapEntityToPlayerProfileResponse(Player player) {
        return modelMapper.map(player, PlayerProfileResponse.class);
    }

    private PlayerResponse mapEntityToPlayerResponse(Player playerList) {
        return modelMapper.map(playerList, PlayerResponse.class);
    }
}
