package admin.config.cache;

import admin.domain.dsl.Team;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class ListSerializer extends JsonSerializer<List<Team>> {

    @Override
    public void serialize(List<Team> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        Team team1 = new Team("team");

        for (Team t : value) {
            team1 = Team.builder()
                    .id(t.getId())
                    .name(t.getName())
                    .build();
        }
        gen.writeObject(team1);
    }


}
