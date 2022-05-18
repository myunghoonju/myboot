package admin.config.cache;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.util.ArrayList;
import java.util.List;

public class CollectionTypeJsonDeSerializer extends SimpleDeserializers {

    @Override
    public JsonDeserializer<?> findCollectionDeserializer(CollectionType type, DeserializationConfig config, BeanDescription beanDesc, TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer) throws JsonMappingException {

        if (isListSerializer(type)) {
            return new ListDeSerializer();
        }

        return findCollectionDeserializer(type, config, beanDesc, elementTypeDeserializer, elementDeserializer);
    }

    private boolean isListSerializer(CollectionType type) {
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, Object.class);
        CollectionType collectionType1 = TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, Object.class);

        return (type.equals(collectionType) || type.equals(collectionType1));
    }
}
