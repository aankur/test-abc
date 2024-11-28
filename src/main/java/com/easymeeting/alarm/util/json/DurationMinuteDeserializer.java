package com.easymeeting.alarm.util.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.Duration;

import static com.easymeeting.alarm.util.json.DeserializerNodeUtil.getStringNodeDate;


public class DurationMinuteDeserializer extends StdDeserializer<Duration> {
  public DurationMinuteDeserializer() {
    this(Duration.class);
  }

  protected DurationMinuteDeserializer(Class<?> c) {
    super(c);
  }
  public static Duration parse(String duration) {
    if (duration == null) {
      return null;
    }
    return Duration.ofMinutes(Long.parseLong(duration));
  }

  @Override
  public Duration deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    return parse(getStringNodeDate(jsonParser));
  }
}
