package com.easymeeting.alarm;

import com.easymeeting.alarm.dto.ScheduleAllowListContainerDTO;
import com.easymeeting.alarm.dto.ScheduleAllowListDTO;
import com.easymeeting.alarm.dto.rules.ScheduleRuleWeekV1DTO;
import com.easymeeting.alarm.model.ScheduleAllowListContainer;
import com.easymeeting.alarm.model.rules.IScheduleRule;
import com.easymeeting.alarm.model.rules.ScheduleRuleWeekV1;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class NattuglaAlarmScheduleFactory {

  static public ScheduleAllowListContainer parseAndCreate(String jsonString) throws JsonProcessingException {
    if(jsonString == null) {
      return new ScheduleAllowListContainer(new ArrayList<>());
    }
    ObjectMapper objectMapper = Constants.getObjectMapper();
    ScheduleAllowListContainerDTO scheduleAllowListContainerDTO = objectMapper.readValue(jsonString, ScheduleAllowListContainerDTO.class);

    if(scheduleAllowListContainerDTO.getAllowList() == null || scheduleAllowListContainerDTO.getAllowList().isEmpty()) {
      return new ScheduleAllowListContainer(new ArrayList<>());
    }
    List<IScheduleRule> allowList = new ArrayList<>();
    for(ScheduleAllowListDTO scheduleAllowListDTO : scheduleAllowListContainerDTO.getAllowList()) {
      ScheduleRuleWeekV1DTO scheduleRuleWeekV1DTO = objectMapper.readValue(scheduleAllowListDTO.getData().getValue(), ScheduleRuleWeekV1DTO.class);
      allowList.add(ScheduleRuleWeekV1.from(scheduleRuleWeekV1DTO));
    }
    return new ScheduleAllowListContainer(allowList);
  }
}
