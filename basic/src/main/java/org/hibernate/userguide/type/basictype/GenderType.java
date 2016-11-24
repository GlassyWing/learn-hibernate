package org.hibernate.userguide.type.basictype;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.sql.CharTypeDescriptor;

/**
 * Created by manlier on 2016/11/23.
 */
public class GenderType
        extends AbstractSingleColumnStandardBasicType<Gender> {

    public static final GenderType INSTANCE = new GenderType();

    public GenderType() {
        super(CharTypeDescriptor.INSTANCE, GenderJavaTypeDescriptor.INSTANCE);
    }

    @Override
    public String getName() {
        return "gender";
    }

    @Override
    protected boolean registerUnderJavaType() {
        return true;
    }
}
