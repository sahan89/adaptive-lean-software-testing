package com.lean.testservice.impl;

import com.lean.domain.response.PlayerProfileResponse;
import com.lean.domain.response.PlayerResponse;
import com.lean.service.impl.PlayerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
public class PlayerServiceImplTest {

    @Mock
    private PlayerServiceImpl playerServiceImpl;

    @Test
    void testGetPlayerById() {
        Long playerId = 11119L;

        PlayerProfileResponse expectedResponse = new PlayerProfileResponse();
        expectedResponse.setPlayerId(11119L);
        expectedResponse.setRanking(1);
        expectedResponse.setIsActive(true);
        expectedResponse.setTeamId(65L);
        expectedResponse.setTeamName("Barcelona");
        expectedResponse.setPlayedPositions("-AMC-AMR-FW-");
        expectedResponse.setAge(27);
        expectedResponse.setPositionText("Forward");
        expectedResponse.setGoal(26);
        expectedResponse.setName("Lionel Messi");
        expectedResponse.setFirstName("Lionel");
        expectedResponse.setLastName("Messi");
        expectedResponse.setManOfTheMatch(16);
        expectedResponse.setPlayedPositionsShort("AM(CR),FW");

        when(playerServiceImpl.getPlayerById(playerId)).thenReturn(expectedResponse);

        PlayerProfileResponse actualResponse = playerServiceImpl.getPlayerById(playerId);

        assertThat("Actual player should not be null", actualResponse, notNullValue());
        assertThat("Player ID should match", actualResponse.getPlayerId(), equalTo(playerId));
        assertThat("Played Positions Short should match", actualResponse.getPlayedPositionsShort(), equalTo(expectedResponse.getPlayedPositionsShort()));
        assertThat("Played Positions Text", actualResponse.getPositionText(), equalTo(expectedResponse.getPositionText()));
        assertThat("Man of the match more than 15", actualResponse.getManOfTheMatch(), greaterThan(15));
        assertThat("Players goals more than 25", actualResponse.getGoal(), greaterThan(25));
        assertThat("Player First Name", actualResponse.getFirstName(), is(expectedResponse.getFirstName()));
        assertThat("Player Last Name", actualResponse.getLastName(), is(expectedResponse.getLastName()));
    }

    @Test
    void getAllPlayers() {
        List<PlayerResponse> expectedPlayers = Arrays.asList(
                new PlayerResponse(11119L, 1, true, 65L, "Barcelona", "-AMC-AMR-FW-", 27, 169, 67, "Forward", 8.759166667, 26, "Lionel Messi"),
                new PlayerResponse(4173L, 2, true, 37L, "Bayern Munich", "-FW-ML-MR-", 31, 180, 80, "Midfielder", 8.56, 16, "Arjen Robben"),
                new PlayerResponse(5583L, 3, true, 52L, "Real Madrid", "-AML-AMR-FW-", 30, 185, 80, "Forward", 8.489047619, 29, "Cristiano Ronaldo")
        );

        when(playerServiceImpl.getAllPlayers()).thenReturn(expectedPlayers);

        List<PlayerResponse> actualPlayers = playerServiceImpl.getAllPlayers();

        assertNotNull("Actual players list should not be null", actualPlayers);
        assertEquals("Number of players should match", expectedPlayers.size(), actualPlayers.size());
        assertThat(actualPlayers, hasItem(hasProperty("name", is("Cristiano Ronaldo"))));
        assertThat(actualPlayers, hasItem(hasProperty("isActive", is(true))));
        assertThat(actualPlayers, hasItem(hasProperty("age", greaterThan(25))));
        assertThat(actualPlayers, hasItem(hasProperty("ranking", greaterThan(0))));
    }
}
