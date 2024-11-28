package com.easymeeting.alarm;

import com.easymeeting.alarm.model.ScheduleAllowListContainer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class NattuglaAlarmScheduleFactoryTest {

  ZoneId zoneId = ZoneId.of("Asia/Kolkata");

  @Test
  void shouldParseAndCreate() throws JsonProcessingException {
    ScheduleAllowListContainer scheduleAllowListContainer = NattuglaAlarmScheduleFactory.parseAndCreate(
      "{\"allowList\": [{\"type\": \"V1-WEEK\", \"data\": {\"dayOfWeek\": \"monday\", \"startAt\": \"05:30\", \"alarmDuration\": \"60\"}}]}"
    );
    ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2024, 11, 25, 6, 0), zoneId);
    assertTrue(scheduleAllowListContainer.isAllowed(zoneId, zonedDateTime.toInstant()));
  }

  @Test
  void shouldFailParseAndCreate() throws JsonProcessingException {
    ScheduleAllowListContainer scheduleAllowListContainer = NattuglaAlarmScheduleFactory.parseAndCreate(
      "{\"allowList\": [{\"type\": \"V1-WEEK\", \"data\": null}]}"
    );
  }
}