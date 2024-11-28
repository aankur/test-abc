package com.easymeeting.alarm.util.json;

import com.easymeeting.alarm.Constants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import static com.easymeeting.alarm.util.json.DeserializerNodeUtil.getStringNodeDate;

public class CustomOffsetDateTimeDeserializer extends StdDeserializer<OffsetDateTime> {

  static private final DateTimeFormatter format = new DateTimeFormatterBuilder()
    .appendOptional(DateTimeFormatter.ofPattern(Constants.ISO_8601_TIMESTAMP_PATTERN))
    .appendOptional(DateTimeFormatter.ofPattern(Constants.ISO_8601_TIMESTAMP_MS_PATTERN))
    .toFormatter();

  public static OffsetDateTime parse(String dateTime) {
    if (dateTime == null) {
      return null;
    }
    return OffsetDateTime.parse(dateTime, format);
  }

  @SuppressWarnings("unused")
  public CustomOffsetDateTimeDeserializer() {
    this(OffsetDateTime.class);
  }

  protected CustomOffsetDateTimeDeserializer(Class<?> c) {
    super(c);
  }

  @Override
  public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext ctx) throws IOException {
    return parse(getStringNodeDate(jsonParser));
  }
}