package com.easymeeting.alarm.util.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class DeserializerNodeUtil {
  public static String getStringNodeDate(JsonParser jsonParser) throws IOException {
    ObjectCodec oc = jsonParser.getCodec();
    JsonNode node = oc.readTree(jsonParser);
    return node.textValue();
  }
}
