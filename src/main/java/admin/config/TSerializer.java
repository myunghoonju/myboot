/*
package admin.config;

import admin.domain.dsl.Tes;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class TSerializer extends JsonDeserializer<Tes> {

    @Override
    public Tes deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode n = p.getCodec().readTree(p);
        String name = n.get("name").asText();
        Tes tes = new Tes();
        tes.setName(name);
        return tes;
    }

}
*/
