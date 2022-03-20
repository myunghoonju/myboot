package admin.config;

import admin.domain.dsl.Team;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListDeSerializer extends JsonDeserializer<List<Team>> {

    @Override
    public List<Team> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        List<Team> list = new ArrayList<>();
        Team team1 = new Team("team");

        JsonNode node = p.getCodec().readTree(p);

        ArrayNode teamNode = (ArrayNode) node;

        for (JsonNode n :teamNode) {
            String id = n.get("id").asText();
            String name = n.get("name").asText();

            team1.setId(Long.valueOf(id));
            team1.setName(name);

            list.add(team1);
        }

        return list;
    }
}
