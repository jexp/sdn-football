package football.domain;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.Collection;
import java.util.HashSet;

import static java.util.Arrays.asList;

@NodeEntity
public class Match {

	@GraphId private Long id;

    @Fetch
    @RelatedTo(type = "AWAY_TEAM")
    private Team awayTeam;
    @RelatedTo(type = "PLAYED_FOR_AWAY", direction = Direction.INCOMING)
    private Collection<Player> awayPlayers;

    @Fetch
    @RelatedTo(type = "HOME_TEAM")
    private Team homeTeam;
    @RelatedTo(type = "PLAYED_FOR_HOME", direction = Direction.INCOMING)
    private Collection<Player> homePlayers;

    @Fetch
    @RelatedTo(type = "IN")
    City city;

    int homeGoals, awayGoals;

    public Match(City city) {
        this.city = city;
    }

    public Match() { }

    public Match awayTeam(Team team, Player...players) {
        this.awayTeam = team;
        this.awayPlayers = new HashSet<Player>(asList(players));
        return this;
    }

    public Match homeTeam(Team team, Player...players) {
        this.homeTeam = team;
        this.homePlayers = new HashSet<Player>(asList(players));
        return this;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Collection<Player> getAwayPlayers() {
        return awayPlayers;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Collection<Player> getHomePlayers() {
        return homePlayers;
    }

    public City getCity() {
        return city;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public void homeScored() {
        homeGoals++;
    }

    public void awayScored() {
        awayGoals++;
    }
}
