package com.easymeeting.alarm.util.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.DayOfWeek;

import static com.easymeeting.alarm.util.json.DeserializerNodeUtil.getStringNodeDate;

public class DayOfWeekDeserializer extends StdDeserializer<DayOfWeek> {

  @SuppressWarnings("unused")
  public DayOfWeekDeserializer() {
    this(DayOfWeek.class);
  }

  protected DayOfWeekDeserializer(Class<?> c) {
    super(c);
  }

  static public DayOfWeek parse(String dayOfWeek) {
    if(dayOfWeek == null) {
      return null;
    }
    return DayOfWeek.valueOf(dayOfWeek.toUpperCase());
  }
  @Override
  public DayOfWeek deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    return parse(getStringNodeDate(jsonParser));
  }
}
