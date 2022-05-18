package admin.config.cache;

import admin.domain.dsl.Team;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class StoreSerializer extends JsonSerializer<Team> {

    @Override
    public void serialize(Team value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();

        gen.writeFieldName("id");
        gen.writeNumber(value.getId());

        gen.writeFieldName("name");
        gen.writeString(value.getName());

        gen.writeEndObject();
    }
}
