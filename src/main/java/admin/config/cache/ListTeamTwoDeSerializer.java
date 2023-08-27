package admin.config.cache;

import admin.domain.dsl.TeamTwo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ListTeamTwoDeSerializer extends JsonDeserializer<List<TeamTwo>> implements ContextualDeserializer {
    private Class<?> targetClass;
    @Override
    public List<TeamTwo> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        List<TeamTwo> list = new ArrayList<>();
        TeamTwo team1 = new TeamTwo("team");

        JsonNode node = p.getCodec().readTree(p);

        ArrayNode teamNode = (ArrayNode) node;

        for (JsonNode n :teamNode) {
            String id = n.get("id").asText();
            String name = n.get("name").asText();
            String age = n.get("age").asText();

            team1.setId(Long.valueOf(id));
            team1.setName(name);
            team1.setAge(Integer.parseInt(age));

            list.add(team1);
        }

        return list;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        String targetClassName=ctxt.getContextualType().toCanonical();
        try {
            targetClass = Class.forName(targetClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return this;
    }
}
