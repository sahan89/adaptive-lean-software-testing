package com.lean.controller;

import com.lean.domain.response.PlayerProfileResponse;
import com.lean.domain.response.PlayerResponse;
import com.lean.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlayerController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players")
    public Flux<PlayerResponse> getAllPlayers() {
        logger.info("-- ENTER -- [GET] -- getAllPlayers --");
        List<PlayerResponse> allPlayers = playerService.getAllPlayers();
        return Flux.fromIterable(allPlayers);
    }

    @GetMapping("/player/{playerId}")
    public Mono<PlayerProfileResponse> getPlayerById(@PathVariable Long playerId) {
        logger.info("-- ENTER -- [GET] -- getPlayerById --{}", playerId);
        return Mono.just(this.playerService.getPlayerById(playerId));
    }
}
