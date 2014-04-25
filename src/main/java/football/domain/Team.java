package football.domain;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

import java.util.Set;

@NodeEntity
public class Team {

	@GraphId private Long id;

    @Indexed(unique = true)
	private String name;

    @RelatedTo(direction = Direction.INCOMING)
    Set<Player> team;

    @Fetch
    @RelatedTo(type = "FROM")
    City city;

    public String getName() {
        return name;
    }

    public Set<Player> getTeam() {
        return team;
    }

    public City getCity() {
        return city;
    }

    public Team(String name, City city) {
        this.name = name;
        this.city = city;
    }

    public Team() { }
}
