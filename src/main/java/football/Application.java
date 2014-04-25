
package football;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

@Configuration
@EnableNeo4jRepositories("football.repositories")
@Import(RepositoryRestMvcConfiguration.class)
@EnableAutoConfiguration
@ComponentScan
public class Application extends Neo4jConfiguration implements CommandLineRunner {

    public static final String PATH = "target/hello.db";

    public Application() {
        setBasePackage("football.domain");
    }

    @Bean(destroyMethod = "shutdown")
    public GraphDatabaseService graphDatabaseService() {
        return new SpringRestGraphDatabase("http://localhost:7474/db/data");
    }

    @Bean
    public DatabasePopulator databasePopulator() {
        return new DatabasePopulator();
    }

    public void run(String... args) throws Exception {
        databasePopulator().initialize();
    }

    public static void main(String[] args) throws IOException {
        FileUtils.deleteRecursively(new File(PATH));

        SpringApplication.run(Application.class, args);
    }
}
