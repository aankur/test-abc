package com.easymeeting.alarm.model.rules;

import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;
class ScheduleRuleWeekV1Test {

  ScheduleRuleWeekV1 scheduleRuleWeek = new ScheduleRuleWeekV1(DayOfWeek.MONDAY, LocalTime.of(20, 30), Duration.ofHours(10));
  ZoneId zoneId = ZoneId.of("Asia/Kolkata");

  ScheduleRuleWeekV1 scheduleRuleSameDayWeek = new ScheduleRuleWeekV1(DayOfWeek.MONDAY, LocalTime.of(18, 30), Duration.ofHours(4));

  @Test
  void shouldAllowTimeDuringScheduleRangeOnSameDay() {
    {
      ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2024, 11, 25, 20, 30), zoneId);
      assertTrue(scheduleRuleWeek.isAllowed(zoneId, zonedDateTime.toInstant()));
    }

    {
      ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2024, 11, 25, 21, 30), zoneId);
      assertTrue(scheduleRuleWeek.isAllowed(zoneId, zonedDateTime.toInstant()));
    }
  }

  @Test
  void shouldRejectTimeBeforeScheduleStartOnSameDay() {
    ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2024, 11, 25, 20, 0), zoneId);
    assertFalse(scheduleRuleWeek.isAllowed(zoneId, zonedDateTime.toInstant()));
  }

  @Test
  void shouldRejectTimeAfterScheduleEndOnNextDay() {
    {
      ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2024, 11, 26, 7, 0), zoneId);
      assertFalse(scheduleRuleWeek.isAllowed(zoneId, zonedDateTime.toInstant()));
    }
    {
      ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2024, 11, 26, 20, 0), zoneId);
      assertFalse(scheduleRuleWeek.isAllowed(zoneId, zonedDateTime.toInstant()));
    }
  }

  @Test
  void shouldAllowTimeDuringScheduleRangeOnNextDay() {
      ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2024, 11, 26, 5, 30), zoneId);
      assertTrue(scheduleRuleWeek.isAllowed(zoneId, zonedDateTime.toInstant()));
  }

  @Test
  void shouldAllowTimeWithinSameDayScheduleRange() {
    ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2024, 11, 25, 20, 0), zoneId);
    assertTrue(scheduleRuleSameDayWeek.isAllowed(zoneId, zonedDateTime.toInstant()));
  }

  @Test
  void shouldRejectTimeBeforeSameDayScheduleRange() {
    ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2024, 11, 25, 18, 0), zoneId);
    assertFalse(scheduleRuleSameDayWeek.isAllowed(zoneId, zonedDateTime.toInstant()));
  }

  @Test
  void shouldRejectTimeAfterSameDayScheduleRange() {
    ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2024, 11, 25, 23, 0), zoneId);
    assertFalse(scheduleRuleSameDayWeek.isAllowed(zoneId, zonedDateTime.toInstant()));
  }

  @Test
  void shouldRejectTimeOnPreviousDay() {
    ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2024, 11, 24, 20, 0), zoneId);
    assertFalse(scheduleRuleSameDayWeek.isAllowed(zoneId, zonedDateTime.toInstant()));
  }

  @Test
  void shouldRejectTimeBeforeRangeOnPreviousDay() {
    ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2024, 11, 24, 6, 0), zoneId);
    assertFalse(scheduleRuleSameDayWeek.isAllowed(zoneId, zonedDateTime.toInstant()));
  }

  @Test
  void shouldRejectTimeAfterRangeOnPreviousDay() {
    ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2024, 11, 24, 11, 0), zoneId);
    assertFalse(scheduleRuleSameDayWeek.isAllowed(zoneId, zonedDateTime.toInstant()));
  }

  @Test
  void shouldRejectTimeOnNextDay() {
    {
      ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2024, 11, 26, 20, 0), zoneId);
      assertFalse(scheduleRuleSameDayWeek.isAllowed(zoneId, zonedDateTime.toInstant()));
    }
  }

  @Test
  void shouldRejectTimeBeforeRangeOnNextDay() {
    {
      ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2024, 11, 26, 6, 0), zoneId);
      assertFalse(scheduleRuleSameDayWeek.isAllowed(zoneId, zonedDateTime.toInstant()));
    }
  }

  @Test
  void shouldRejectTimeAfterRangeOnNextDay() {
    {
      ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2024, 11, 26, 11, 0), zoneId);
      assertFalse(scheduleRuleSameDayWeek.isAllowed(zoneId, zonedDateTime.toInstant()));
    }
  }
}