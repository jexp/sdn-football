package football.domain;

import org.springframework.data.neo4j.annotation.*;

import java.util.List;

@NodeEntity
public class Player {

	@GraphId private Long id;

    @Indexed(unique = true)
	private String name;

    private int age;

    private Team team;

    @RelatedTo(type="PLAYED_IN")
    List<Match> matches;

    @Fetch
    @RelatedTo(type = "FROM")
    City city;

    public Player(String name) {
        this.name = name;
    }

    public Player() { }

    public String getName() {
		return name;
	}

    public Player playsFor(Team team) {
        this.team = team;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Team getTeam() {
        return team;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public City getCity() {
        return city;
    }
}
