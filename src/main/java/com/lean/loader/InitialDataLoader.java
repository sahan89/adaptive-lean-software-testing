package com.lean.loader;

import com.lean.domain.Player;
import com.lean.domain.response.PlayerResponse;
import com.lean.service.PlayerService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitialDataLoader {
    private static final Logger logger = LoggerFactory.getLogger(InitialDataLoader.class);

    private final PlayerService playerService;

    @Autowired
    public InitialDataLoader(PlayerService playerService) {
        this.playerService = playerService;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("--ENTER-- onApplicationEvent(ContextRefreshedEvent)");
        try {
            this.loadPlayerDataToDatabase();
        } catch (Exception ex) {
            logger.error("--ERROR-- onApplicationEvent -- Data not save data to the Database--");
        }
        logger.info("--EXIT-- onApplicationEvent--");
    }

    private void loadPlayerDataToDatabase() {
        logger.info("--ENTER-- loadPlayerDataToDatabase--");

        List<PlayerResponse> allPlayersList = this.playerService.getAllPlayers();

        if (allPlayersList == null || allPlayersList.isEmpty()) {
            Resource resource = new ClassPathResource("/static/players_server_app.csv");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
                String headerLine = reader.readLine();
                List<Player> players = this.parseCsvDataToPlayers(reader, headerLine);
                this.playerService.saveAllPlayers(players);
            } catch (IOException e) {
                throw new RuntimeException("Error reading CSV file: " + e.getMessage(), e);
            }
        }
    }

    private List<Player> parseCsvDataToPlayers(BufferedReader reader, String headerLine) throws IOException {
        logger.info("--ENTER-- parseCsvDataToPlayers--");
        CSVParser csvParser = CSVFormat.DEFAULT.withHeader(headerLine.split(",")).parse(reader);

        List<Player> players = new ArrayList<>();
        for (CSVRecord record : csvParser) {
            Player player = new Player();
            player.setRanking(Integer.parseInt(record.get(0)));
            player.setSeasonId(Long.parseLong(record.get(1)));
            player.setTournamentId(Long.parseLong(record.get(2)));
            player.setTournamentRegionId(Long.parseLong(record.get(3)));
            player.setTournamentRegionCode(record.get(4));
            player.setRegionCode(record.get(5));
            player.setTournamentName(record.get(6));
            player.setTournamentShortName(record.get(7));
            player.setFirstName(record.get(8));
            player.setLastName(record.get(9));
            player.setPlayerId(Long.parseLong(record.get(10)));
            player.setIsActive(Boolean.valueOf(record.get(11)));
            player.setIsOpta(Boolean.valueOf(record.get(12)));
            player.setTeamId(Long.parseLong(record.get(13)));
            player.setTeamName(record.get(14));
            player.setPlayedPositions(record.get(15));
            player.setAge(Integer.parseInt(record.get(16)));
            player.setHeight(Integer.parseInt(record.get(17)));
            player.setWeight(Integer.parseInt(record.get(18)));
            player.setPositionText(record.get(19));
            player.setApps(Integer.parseInt(record.get(20)));
            player.setSubOn(Integer.parseInt(record.get(21)));
            player.setMinsPlayed(Integer.parseInt(record.get(22)));
            player.setRating(Double.parseDouble(record.get(23)));
            player.setGoal(Integer.parseInt(record.get(24)));
            player.setAssistTotal(Integer.parseInt(record.get(25)));
            player.setYellowCard(Integer.parseInt(record.get(26)));
            player.setRedCard(Integer.parseInt(record.get(27)));
            player.setShotsPerGame(Double.parseDouble(record.get(28)));
            player.setAerialWonPerGame(Double.parseDouble(record.get(29)));
            player.setManOfTheMatch(Integer.parseInt(record.get(30)));
            player.setName(record.get(31));
            player.setIsManOfTheMatch(Boolean.valueOf(record.get(32)));
            player.setPlayedPositionsShort(record.get(33));
            player.setPassSuccess(Double.parseDouble(record.get(34)));

            players.add(player);
        }
        logger.info("--EXIT-- parseCsvDataToPlayers--Saved {} Records", players.size());
        return players;
    }
}
