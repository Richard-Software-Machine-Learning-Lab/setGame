package server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.impl.LeagueTable;


/**
 * Tests for LeagueTable.
 */
public class LeagueTableImplementationTests {
    ILeagueTable leagueTable;

    @BeforeEach
    public void setup() {
        this.leagueTable = new LeagueTable();
    }

    @Test
    public void addScoreToLeagueTable() {
        String name = "Richard";
        Long time = 1L;
        this.leagueTable.addToLeagueTable(name, time);
        Assertions.assertNotNull(this.leagueTable.getLeagueTable());
        Assertions.assertNotNull(this.leagueTable.getLeagueTable().get("Emma"));
    }


    @Test
    public void getLeagueTableReturnsPlayerNameAndTheirTime() {
        String name = "Richard";
        Long time = 1L;
        this.leagueTable.addToLeagueTable(name, time);
        Assertions.assertEquals(leagueTable.getLeagueTable().get("Emma"), 1);
        Assertions.assertNull(leagueTable.getLeagueTable().get("Richard"));
    }
}
