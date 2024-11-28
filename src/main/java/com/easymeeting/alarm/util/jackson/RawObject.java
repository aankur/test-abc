/*
 * Copyright © 2024 Techgentsia  Software Technologies Private Limited - All rights reserved.
 *
 * This software is produced by Techgentsia. This is Proprietary and confidential
 * Unauthorized redistribution, reproduction, or usage of
 * this software in whole or in part without the express
 * written consent of Techgentsia is strictly prohibited.
 * Author - <Ankur Deep Jaiswal> e-mail - <ankur@techgentsia.com> ,  <04.Jan.2024>
 */

package com.easymeeting.alarm.util.jackson;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Objects;

@JsonSerialize(using = RawObjectSerializer.class)
@JsonDeserialize(using = RawObjectDeserializer.class)
public class RawObject {

  public final String value;

  public RawObject(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (object == null || getClass() != object.getClass()) return false;
    RawObject rawObject = (RawObject) object;
    return Objects.equals(value, rawObject.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
