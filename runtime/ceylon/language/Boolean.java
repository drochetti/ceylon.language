package ceylon.language;

import com.redhat.ceylon.compiler.java.metadata.CaseTypes;
import com.redhat.ceylon.compiler.java.metadata.Ceylon;
import com.redhat.ceylon.compiler.java.metadata.Class;
import com.redhat.ceylon.compiler.java.metadata.Ignore;
import com.redhat.ceylon.compiler.java.metadata.ValueType;
import com.redhat.ceylon.compiler.java.runtime.model.ReifiedType;
import com.redhat.ceylon.compiler.java.runtime.model.TypeDescriptor;

@Ceylon(major = 5)
@Class
@CaseTypes({"ceylon.language::true", "ceylon.language::false"})
@ValueType
public abstract class Boolean implements ReifiedType {

    @Ignore
    public final static TypeDescriptor $TypeDescriptor = TypeDescriptor.klass(Boolean.class);

    @Ignore
    public static Boolean instance(boolean b) {
        return b ? true_.getTrue$() : false_.getFalse$();
    }

    @Ignore
    abstract public boolean booleanValue();
    
    @Ignore
    public static java.lang.String toString(boolean value) {
        return (value) ? true_.getTrue$().toString() : false_.getFalse$().toString();
    }
    
    @Ignore
    public static boolean equals(boolean value, java.lang.Object that) {
        if (that instanceof Boolean) {
            Boolean s = (Boolean)that;
            return value = s.booleanValue();
        }
        else {
            return false;
        }
    }

    @Override
    @Ignore
    public TypeDescriptor $getType() {
        return $TypeDescriptor;
    }
}
