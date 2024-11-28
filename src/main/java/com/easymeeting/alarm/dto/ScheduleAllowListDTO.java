package com.easymeeting.alarm.dto;

import com.easymeeting.alarm.dto.exception.BadRuleDataException;
import com.easymeeting.alarm.util.jackson.JsonPostConstruct;
import com.easymeeting.alarm.util.jackson.JsonPostConstructConverter;
import com.easymeeting.alarm.util.jackson.RawObject;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@JsonDeserialize(converter = ScheduleAllowListDTO.PostConstructor.class)
@Data
public class ScheduleAllowListDTO {
  String type;
  RawObject data;

  @JsonPostConstruct
  public void validate() {
    if(type == null || type.trim().isEmpty()) {
      throw new BadRuleDataException("'type' is required");
    }
    if(!type.equals("V1-WEEK")) {
      throw new BadRuleDataException("'type' is not 'V1-WEEK'");
    }
    if(data == null) {
      throw new BadRuleDataException("'data' is required");
    }

  }

  static class PostConstructor extends JsonPostConstructConverter<ScheduleAllowListDTO>
  {

  }
}
