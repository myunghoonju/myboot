package admin.config;

import admin.domain.dsl.Team;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.util.ArrayList;
import java.util.List;

public class CollectionTypeJsonSerializer extends SimpleSerializers {
    @Override
    public JsonSerializer<?> findCollectionSerializer(SerializationConfig config,
                                                      CollectionType type,
                                                      BeanDescription beanDesc,
                                                      TypeSerializer elementTypeSerializer,
                                                      JsonSerializer<Object> elementValueSerializer) {
        if (isListSerializer(type)) {
            return new ListSerializer();
        }

        return findSerializer(config, type, beanDesc);
    }

    private boolean isListSerializer(CollectionType type) {
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, Team.class);
        CollectionType collectionType1 = TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, Team.class);

        return (type.equals(collectionType) || type.equals(collectionType1));
    }


}
