/*
 * Copyright © 2023 Techgentsia  Software Technologies Private Limited - All rights reserved.
 *
 * This software is produced by Techgentsia. This is Proprietary and confidential
 * Unauthorized redistribution, reproduction, or usage of
 * this software in whole or in part without the express
 * written consent of Techgentsia is strictly prohibited.
 * Author - <Ankur Deep Jaiswal> e-mail - <ankur@techgentsia.com> ,  <22.Jul.2023>
 */

package com.easymeeting.alarm.util.jackson;

import com.easymeeting.alarm.dto.exception.BadRuleDataException;
import com.fasterxml.jackson.databind.util.StdConverter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JsonPostConstructConverter<T> extends StdConverter<T, T>
{
  @Override
  public T convert(T value)
  {
    Class<?> klass = value.getClass();

    Method method = null;

    for (Method declaredMethod : klass.getDeclaredMethods())
    {
      JsonPostConstruct postConstruct = declaredMethod.getAnnotation(JsonPostConstruct.class);
      if (postConstruct != null)
      {
        method = declaredMethod;
        break;
      }
    }


    if (method != null)
    {
      try
      {
        method.setAccessible(true);
        if (method.getParameterCount() == 0)
        {
          method.invoke(value);
        }
        else
        {
          method.invoke(null); // Replace null with an InvocationContext if needed
        }
      }
      catch (IllegalAccessException | IllegalArgumentException
             | InvocationTargetException e)
      {
        if(e instanceof InvocationTargetException
          && ((InvocationTargetException) e).getTargetException() != null
          && ((InvocationTargetException) e).getTargetException() instanceof BadRuleDataException

        ) {
          throw (BadRuleDataException) (((InvocationTargetException) e).getTargetException());
        }
        throw new IllegalArgumentException("Failed to invoke post-construct method", e);
      }
    }

    return value;
  }
}
