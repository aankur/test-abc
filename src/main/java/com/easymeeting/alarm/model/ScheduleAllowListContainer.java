package com.easymeeting.alarm.model;

import com.easymeeting.alarm.model.rules.IScheduleRule;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

@AllArgsConstructor
public class ScheduleAllowListContainer implements IScheduleRule {
  List<IScheduleRule> rules;
  @Override
  public boolean isAllowed(@NonNull ZoneId zoneId, @NonNull Instant instant) {
    if(rules.isEmpty()) {
      return true;
    }

    for (IScheduleRule rule : rules) {
      if(rule.isAllowed(zoneId, instant)) {
        return true;
      }
    }

    return false;
   }
}
