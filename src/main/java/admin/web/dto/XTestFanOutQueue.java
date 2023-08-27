package admin.web.dto;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIncludeProperties(value = "destination")
public class XTestFanOutQueue {

     private static ObjectMapper mapper = new ObjectMapper();

     private String source;

     private String vhost;
     
     private String destination;

     private String destination_type;

     private String routing_key;

     private String arguments;

     private String properties_key;

     public static List<XTestFanOutQueue> toList(String content) throws JsonProcessingException {
          Collection<XTestFanOutQueue> xTestFanOutQueues = mapper.readValue(content, new TypeReference<>() {});
          return new ArrayList<>(xTestFanOutQueues);
     }
}
