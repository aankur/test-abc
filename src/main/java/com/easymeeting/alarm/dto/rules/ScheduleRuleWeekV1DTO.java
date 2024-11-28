package com.easymeeting.alarm.dto.rules;

import com.easymeeting.alarm.dto.exception.BadRuleDataException;
import com.easymeeting.alarm.util.jackson.JsonPostConstruct;
import com.easymeeting.alarm.util.jackson.JsonPostConstructConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

@JsonDeserialize(converter = ScheduleRuleWeekV1DTO.PostConstructor.class)
@Data
public class ScheduleRuleWeekV1DTO {
  DayOfWeek dayOfWeek;
  LocalTime startAt;
  Duration alarmDuration;

  @JsonPostConstruct
  public void validate() {
    if(dayOfWeek == null) {
      throw new BadRuleDataException("'dayOfWeek' is required");
    }
    if(startAt == null) {
      throw new BadRuleDataException("'startAt' is required");
    }
    if(alarmDuration == null) {
      throw new BadRuleDataException("'alarmDuration' is required");
    }
  }

  static class PostConstructor extends JsonPostConstructConverter<ScheduleRuleWeekV1DTO>
  {

  }
}
