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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

/**
 * @version 1.0 07/10/2012
 * @contact miqueias@moonjava.com.br
 * 
 */
public class EncryptPassword {

  public String toEncryptMD5(String password) {
    try {
      MessageDigest digest = MessageDigest.getInstance("MD5");
      digest.update(password.getBytes());
      Base64 base = new Base64();
      return base.encodeAsString(digest.digest());
    } catch (NoSuchAlgorithmException ns) {
      ns.printStackTrace();
      return password;
    }
  }
}
