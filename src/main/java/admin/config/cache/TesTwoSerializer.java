/*
package admin.config;

import admin.domain.dsl.TesTwo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class TesTwoSerializer extends JsonDeserializer<TesTwo> {

    @Override
    public TesTwo deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode n = p.getCodec().readTree(p);
        String name = n.get("name").asText();
        TesTwo tes = new TesTwo();
        tes.setName(name);
        return tes;
    }

}
*/
