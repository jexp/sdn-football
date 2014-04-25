package football;

import football.domain.City;
import football.domain.Match;
import football.domain.Player;
import football.domain.Team;
import football.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.template.GraphCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DatabasePopulator {
    @Autowired private Neo4jTemplate template;

    @Transactional
    public void initialize() {

        template.exec(new GraphCallback.WithoutResult() {
            @Override
            public void doWithGraphWithoutResult(GraphDatabase graph) throws Exception {
                City madrid = new City("Madrid", new Point(40.4072734, 3.7007823));
                City munich = new City("München", new Point(48.1549107,11.5418357));
                Team real = new Team("Real Madrid",madrid);
                Team bayern = new Team("Bayern München",munich);

                Player plReal = new Player("Cristiano Ronaldo").playsFor(real);
                Player plBayern = new Player("Thomas Müller").playsFor(bayern);

                Match realVsBayern =
                        new Match(madrid)
                                .awayTeam(bayern,plBayern)
                                .homeTeam(real, plReal);

                template.save(madrid);
                template.save(munich);
                template.save(real);
                template.save(plReal);
                template.save(bayern);
                template.save(plBayern);
                template.save(realVsBayern);
            }
        });
    }
}
