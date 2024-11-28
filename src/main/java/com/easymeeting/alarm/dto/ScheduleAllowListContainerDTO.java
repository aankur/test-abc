package com.easymeeting.alarm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScheduleAllowListContainerDTO {
  List<ScheduleAllowListDTO> allowList;
}
