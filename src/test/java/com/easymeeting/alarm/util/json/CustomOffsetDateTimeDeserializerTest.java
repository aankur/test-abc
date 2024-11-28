package com.easymeeting.alarm.util.json;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

class CustomOffsetDateTimeDeserializerTest {

  @Test
  void parse() {
    OffsetDateTime offsetDateTime = CustomOffsetDateTimeDeserializer.parse("2024-09-14T22:30:00Z");
    assertEquals(OffsetDateTime.of(LocalDateTime.of(2024, 9, 14, 22, 30, 0), ZoneOffset.UTC), offsetDateTime);
    assertNull(CustomOffsetDateTimeDeserializer.parse(null));
  }
}