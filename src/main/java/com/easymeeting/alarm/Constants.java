package com.easymeeting.alarm;

import com.easymeeting.alarm.util.json.*;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.time.OffsetDateTime;

public class Constants {

  public static final String ISO_8601_TIMESTAMP_PATTERN = "yyyy-MM-dd'T'HH:mm:ssXXX";
  public static final String ISO_8601_TIMESTAMP_MS_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

  static private ObjectMapper OBJECT_MAPPER;

  public static ObjectMapper ObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    SimpleModule nattuglaAlarmModule = new SimpleModule("NattuglaAlarmModule", new Version(1, 0, 0, null, null, null));
    nattuglaAlarmModule.addSerializer(OffsetDateTime.class, new CustomOffsetDateTimeSerializer());
    nattuglaAlarmModule.addDeserializer(OffsetDateTime.class, new CustomOffsetDateTimeDeserializer());
    nattuglaAlarmModule.addDeserializer(DayOfWeek.class, new DayOfWeekDeserializer());
    nattuglaAlarmModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer());
    nattuglaAlarmModule.addDeserializer(Duration.class, new DurationMinuteDeserializer());
    objectMapper.registerModule(nattuglaAlarmModule);

    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    return objectMapper;
  }

  public static synchronized ObjectMapper getObjectMapper() {
    if(OBJECT_MAPPER == null) {
      OBJECT_MAPPER = ObjectMapper();
    }
    return OBJECT_MAPPER;
  }
}
