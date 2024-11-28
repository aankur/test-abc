package com.easymeeting.alarm.util.json;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.*;

class DayOfWeekDeserializerTest {

  @Test
  void shouldParse() {
    DayOfWeek dayOfWeek = DayOfWeekDeserializer.parse("sunday");
    assertEquals(DayOfWeek.SUNDAY, dayOfWeek);
    assertNull(DayOfWeekDeserializer.parse(null));
  }

  @Test
  void shouldFail() {
    assertThrows(IllegalArgumentException.class, () -> DayOfWeekDeserializer.parse("noday"));
  }
}