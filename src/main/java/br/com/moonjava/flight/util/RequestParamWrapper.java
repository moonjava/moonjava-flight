/*
 * Copyright 2012 MoonJava LTDA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.moonjava.flight.util;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

/**
 * @version 1.0 Apr 10, 2012
 * @contact tiago.aguiar@moonjava.com.br
 * 
 */
public class RequestParamWrapper implements RequestParam {

  private Map<String, Object> map;

  public RequestParamWrapper() {
    this.map = new HashMap<String, Object>();
  }

  @Override
  public void set(String string, Object object) {
    map.put(string, object);
  }

  @Override
  public Integer intParam(String param) {
    return (Integer) map.get(param);
  }

  @Override
  public DateTime dateTimeParam(String param) {
    return (DateTime) map.get(param);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <E extends Enum<E>> E enumParam(Class<E> enumClass, String param) {
    return (E) map.get(param);
  }

  @Override
  public String stringParam(String param) {
    return (String) map.get(param);
  }
  
  public Boolean booleanParam(String param){
	  return (Boolean) map.get(param);
  }

}