package football.repositories;

import football.domain.Team;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "myTeams", path = "teams")
public interface TeamRepository extends GraphRepository<Team> {
    Team findByName(@Param("0") String name);

    // MATCH (t:Team)-[:FROM]->(c:City) where c.name = {0} return t
    List<Team> findByCityName(@Param("0") String name);
}
