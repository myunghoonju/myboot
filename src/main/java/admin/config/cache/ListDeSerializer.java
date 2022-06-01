package admin.config.cache;

import admin.domain.dsl.Team;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ListDeSerializer extends JsonDeserializer<List<Team>> implements ContextualDeserializer {
    private Class<?> targetClass;
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
