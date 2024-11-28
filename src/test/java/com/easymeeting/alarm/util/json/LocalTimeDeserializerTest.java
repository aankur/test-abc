package com.easymeeting.alarm.util.json;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class LocalTimeDeserializerTest {

  @Test
  void shouldParseLocalTime() {
    LocalTime localTime = LocalTimeDeserializer.parse("13:30");
    assertEquals(LocalTime.of(13, 30, 0), localTime);
    assertNull(LocalTimeDeserializer.parse(null));
  }

  @Test
  void shouldFail() {
    assertThrows(DateTimeParseException.class, () -> LocalTimeDeserializer.parse("26:30"));
  }
}