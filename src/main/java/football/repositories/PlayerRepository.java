package football.repositories;

import football.domain.Player;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "players", path = "players")
public interface PlayerRepository extends GraphRepository<Player> {

    Player findByName(@Param("0") String name);
}
