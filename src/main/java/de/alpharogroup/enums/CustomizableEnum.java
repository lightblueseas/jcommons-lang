/*
 * Copyright 2016 Alpha Ro Group UG (haftungsbeschrängt).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.enums;

import lombok.Getter;

/**
 * This class is for use if you have an enum but you want that the user can set 
 * a custom value.
 * @author astrapi69
 */
public class CustomizableEnum<E extends Enum<E>, T> {
    @Getter
    E enumtype;
    @Getter
    T value;
    @Getter    
    boolean custom;
    
    public CustomizableEnum(E enumtype, final T value) {
        this.enumtype = enumtype;
        if(value!= null) {
            this.value = value;
            custom = true;
        }
    }
}
