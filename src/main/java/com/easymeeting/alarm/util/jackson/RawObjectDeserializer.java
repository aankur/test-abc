package com.easymeeting.alarm.util.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;

import java.io.IOException;

public class RawObjectDeserializer extends StdDeserializer<RawObject> {

  public RawObjectDeserializer() {
    super(RawObject.class);
  }

  @Override
  public RawObject deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    return new RawObject(parser.getCodec().readTree(parser).toString());
  }

  @Override
  public Object deserializeWithType(JsonParser parser, DeserializationContext context, TypeDeserializer typeDeserializer) throws IOException {
    return deserialize(parser, context);
  }
}
