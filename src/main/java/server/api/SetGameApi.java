package server.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import server.IModelServer;

import server.impl.ModelServer;

import java.util.ArrayList;
import java.util.Hashtable;

@RestController
public class SetGameApi {
    IModelServer  model = new ModelServer();

    @GetMapping("/setGameApi")
    public String apiDescription() {
        return "Welcome to the REST API from the Set Game";
    }


    @GetMapping("/checkSet")
    public Boolean checkSet(@RequestParam ArrayList<Integer> cards, @RequestParam int challengeId) {
        if( cards.size() == 3 && challengeId < this.model.getTotalNumberOfChallenges()) {
            return this.model.checkSet(cards,challengeId);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect parameters in terms of card numbers or challenge id");
    }


    /**
     * Gets the cards for the current challenge.
     */

    @GetMapping("challengeDeck/{challengeId}")
    public ArrayList<Integer> challengeDeck(@PathVariable int challengeId) {
        if (challengeId >= 0 &&  challengeId < this.model.getTotalNumberOfChallenges()) {
            return this.model.challengeDeck(challengeId);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "challenge does not exist");
    }


    /**
     * Update the public league table with name and time.
     */
    @PostMapping("updateLeagueTable/{challengeId}/{name}/{time}")
    public void updateLeagueTable(@PathVariable int challengeId, @PathVariable String name,
                                  @PathVariable long time) {
        if (challengeId >= 0 &&  challengeId < this.model.getTotalNumberOfChallenges() && this.model != null) {
            this.model.updateLeagueTable(challengeId, name, time);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "league table not found");
    }


    /**
     * Get league table for the challenge.
     */
    @GetMapping("getLeagueTable/{challengeId}")
    public Hashtable<String, Long> getLeagueTable(@PathVariable int challengeId) {
        if (challengeId >= 0 &&  challengeId < this.model.getTotalNumberOfChallenges() && this.model != null) {
            return model.getLeagueTable(challengeId).getLeagueTable();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "league table not found");
    }

    /**
     * How many sets in challenge.
     */
    @GetMapping("getNumberOfSets/{challengeId}")
    public int getNumberOfSets(@PathVariable int challengeId) {
        if (challengeId >= 0 &&  challengeId < this.model.getTotalNumberOfChallenges() && this.model != null) {
            return this.model.getChallenge(challengeId).getNumberOfSetsIncluded();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "challenge not found");

    }
}
