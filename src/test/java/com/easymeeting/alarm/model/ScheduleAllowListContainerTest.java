package com.easymeeting.alarm.model;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleAllowListContainerTest {

  ZoneId zoneId = ZoneId.of("Asia/Kolkata");

  @Test
  void shouldAlwaysReturnTrueOnNoRulesConfigured() {
    ScheduleAllowListContainer scheduleAllowListContainer = new ScheduleAllowListContainer(new ArrayList<>());
    assertTrue(scheduleAllowListContainer.isAllowed(zoneId, ZonedDateTime.now().toInstant()));
  }

  @Test
  void shouldAlwaysReturnFalseWhenOneRuleIsFalse() {
    ScheduleAllowListContainer scheduleAllowListContainer = new ScheduleAllowListContainer(new ArrayList<>(Collections.singleton((zoneId, instant) -> false)));
    assertFalse(scheduleAllowListContainer.isAllowed(zoneId, ZonedDateTime.now().toInstant()));
  }

  @Test
  void shouldAlwaysReturnTrueWhenOneRuleIsTrue() {
    ScheduleAllowListContainer scheduleAllowListContainer = new ScheduleAllowListContainer(new ArrayList<>(Collections.singleton((zoneId, instant) -> true)));
    assertTrue(scheduleAllowListContainer.isAllowed(zoneId, ZonedDateTime.now().toInstant()));
  }
}