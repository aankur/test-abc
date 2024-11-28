package com.easymeeting.alarm.model.rules;

import com.easymeeting.alarm.dto.rules.ScheduleRuleWeekV1DTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.*;

@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRuleWeekV1 implements IScheduleRule {
  DayOfWeek dayOfWeek;
  LocalTime startAt;
  Duration alarmDuration;

  public static ScheduleRuleWeekV1 from(ScheduleRuleWeekV1DTO scheduleRuleWeekV1DTO) {
    return new ScheduleRuleWeekV1(scheduleRuleWeekV1DTO.getDayOfWeek(), scheduleRuleWeekV1DTO.getStartAt(), scheduleRuleWeekV1DTO.getAlarmDuration());
  }

  @Override
  public boolean isAllowed(@NonNull ZoneId zoneId, @NonNull Instant instant) {
    return
      getCurrentDayAllowed(zoneId, instant) ||
        getPreviousDayAllowed(zoneId, instant);
  }

  private boolean getCurrentDayAllowed(@NonNull ZoneId zoneId, @NonNull Instant instant) {
    LocalDateTime timeAtZone = LocalDateTime.ofInstant(instant, zoneId);
    LocalDateTime alarmStartAt = startAt.atDate(timeAtZone.toLocalDate());
    LocalDateTime alarmEndAt = alarmStartAt.plus(alarmDuration);
    return alarmStartAt.getDayOfWeek().equals(dayOfWeek) && withinRange(timeAtZone, alarmStartAt, alarmEndAt);
  }

  private boolean getPreviousDayAllowed(@NonNull ZoneId zoneId, @NonNull Instant instant) {
    LocalDateTime timeAtZone = LocalDateTime.ofInstant(instant, zoneId);
    LocalDateTime alarmStartAt = startAt.atDate(timeAtZone.toLocalDate().minusDays(1));
    LocalDateTime alarmEndAt = alarmStartAt.plus(alarmDuration);
    return alarmEndAt.getDayOfWeek().equals(dayOfWeek.plus(1)) && withinRange(timeAtZone, alarmStartAt, alarmEndAt);
  }

  private boolean withinRange(@NonNull LocalDateTime needle, @NonNull LocalDateTime startAt, @NonNull LocalDateTime endAt) {
    return (
      needle.isEqual(startAt) || needle.isAfter(startAt)
    ) && (
      needle.isEqual(endAt) || needle.isBefore(endAt)
    );
  }
}
