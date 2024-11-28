package com.easymeeting.alarm.util.json;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
class DurationMinuteDeserializerTest {

  @Test
  void shouldParse() {
    Duration duration = DurationMinuteDeserializer.parse("10");
    assertEquals(Duration.ofMinutes(10), duration);
    assertNull(DurationMinuteDeserializer.parse(null));
  }

  @Test
  void shouldNotParse() {
    assertThrows(NumberFormatException.class, () -> DurationMinuteDeserializer.parse("ab"));
  }
}