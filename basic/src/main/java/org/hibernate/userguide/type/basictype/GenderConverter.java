package org.hibernate.userguide.type.basictype;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by manlier on 2016/11/23.
 */
@Converter
public class GenderConverter
        implements AttributeConverter<Gender, Character> {

    @Override
    public Character convertToDatabaseColumn(Gender attribute) {
        return attribute == null ? null : attribute.getCode();
    }

    @Override
    public Gender convertToEntityAttribute(Character dbData) {
        return dbData == null ? null : Gender.fromCode(dbData);
    }
}
