package com.easymeeting.alarm.model.rules;

import lombok.NonNull;

import java.time.Instant;
import java.time.ZoneId;

public interface IScheduleRule {
  boolean isAllowed(@NonNull ZoneId zoneId, @NonNull Instant instant);
}
