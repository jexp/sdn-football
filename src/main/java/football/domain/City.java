package football.domain;

import org.neo4j.graphdb.Direction;
import org.springframework.data.geo.Point;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.support.index.IndexType;

import java.util.Set;

@NodeEntity
public class City {

	@GraphId private Long id;

    @Indexed(unique = true)
	private String name;

//    @Indexed(indexType = IndexType.POINT, indexName = "geo")
    private Point wkt;

    public String getName() {
        return name;
    }

    public City(String name, Point location) {
        this.name = name;
        this.wkt=location;
    }

    public City() { }


}
