package apiTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import server.api.SetGameApi;

/**
 * Tests for the {@link SetGameApi} class
 */
public class SetGameApiTests {

    WebTestClient client;

    @BeforeEach
    public void setup() {
        client = WebTestClient.bindToController(new SetGameApi()).build();
    }

    @Test
    public void checkSetApi() {
        client.get().uri("/setGameApi")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void checkSetOfCardsChosen() {
        client.get().uri("/checkSet?cards=2232,2112,2322&challengeId=0")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void checkSetOfCardsChosenWithIncorrectChallengeNumber() {
        client.get().uri("/checkSet?cards=2232,2112,2322&challengeId=4")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void checkNumberOfCardIncorrectly() {
        client.get().uri("/checkSet?cards=2432,2112,1311")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void challengeDeck() {
        client.get().uri("/challengeDeck/0")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }


    @Test
    public void challengeDeckNotFound() {
        client.get().uri("/challengeDeck/10")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }


    @Test
    public void getNumberOfSetsInChallenge() {
        client.get().uri("/getNumberOfSets/0")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody().json("4");
    }

    @Test
    public void getNumberOfSetsInChallengeNotFound() {
        client.get().uri("/getNumberOfSets/4")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void getLeagueTableShouldReturnLeagueTable() {
        client.get().uri("/getLeagueTable/0")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void getLeagueTableShouldReturnLeagueTableNotFound() {
        client.get().uri("/getLeagueTable/4")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }

}
