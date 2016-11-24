package org.hibernate.userguide.type.basictype;

import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.java.CharacterTypeDescriptor;

/**
 * Created by manlier on 2016/11/23.
 */
public class GenderJavaTypeDescriptor extends AbstractTypeDescriptor<Gender> {

    public static final GenderJavaTypeDescriptor INSTANCE =
            new GenderJavaTypeDescriptor();

    protected GenderJavaTypeDescriptor() {
        super(Gender.class);
    }

    @Override
    public String toString(Gender value) {
        return value == null ? null : value.name();
    }

    @Override
    public Gender fromString(String string) {
        return string == null ? null : Gender.valueOf(string);
    }

    @Override
    public <X> X unwrap(Gender value, Class<X> type, WrapperOptions options) {
        return CharacterTypeDescriptor.INSTANCE.unwrap(
                value == null ? null : value.getCode(),
                type,
                options
        );
    }

    @Override
    public <X> Gender wrap(X value, WrapperOptions options) {
        return Gender.fromCode(
                CharacterTypeDescriptor.INSTANCE.wrap(value, options)
        );
    }
}
