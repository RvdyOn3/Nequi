package com.nequi.jpa.v1.mapper;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class ObjectIdMapper {
    public String map(ObjectId objectId) {
        return objectId != null ? objectId.toHexString() : null;
    }

    public ObjectId map(String id) {
        return (id != null && ObjectId.isValid(id)) ? new ObjectId(id) : null;
    }
}
