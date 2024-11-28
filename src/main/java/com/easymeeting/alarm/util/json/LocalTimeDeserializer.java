package com.easymeeting.alarm.util.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static com.easymeeting.alarm.util.json.DeserializerNodeUtil.getStringNodeDate;

public class LocalTimeDeserializer extends StdDeserializer<LocalTime> {
  @SuppressWarnings("unused")
  public LocalTimeDeserializer() {
    this(LocalTime.class);
  }

  protected LocalTimeDeserializer(Class<?> c) {
    super(c);
  }

  static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

  public static LocalTime parse(String localTime) {
    if(localTime == null) {
      return null;
    }
    return LocalTime.parse(localTime, dateTimeFormatter);
  }

  @Override
  public LocalTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    return parse(getStringNodeDate(jsonParser));
  }
}
