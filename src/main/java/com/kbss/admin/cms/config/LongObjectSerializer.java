package com.kbss.admin.cms.config;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

import java.io.IOException;
import java.lang.reflect.Type;

public class LongObjectSerializer implements ObjectDeserializer, ObjectSerializer {
    @Override
    public void write(JSONSerializer serializer, Object object,
                      Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;

        if (object == null) {
            out.append("");
            return;
        }

        String strVal = object.toString();
        out.writeString(strVal);
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type,
                            Object fieldName) {
        return null;
    }

    @Override
    public int getFastMatchToken() {
        // TODO Auto-generated method stub
        return 0;
    }
}
