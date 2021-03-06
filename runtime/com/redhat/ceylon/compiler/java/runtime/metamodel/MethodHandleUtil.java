package com.redhat.ceylon.compiler.java.runtime.metamodel;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.List;

import com.redhat.ceylon.compiler.java.runtime.model.TypeDescriptor;
import com.redhat.ceylon.compiler.typechecker.model.ProducedType;

public class MethodHandleUtil {

    public static MethodHandle insertReifiedTypeArguments(MethodHandle constructor, int insertAt, List<ProducedType> typeArguments) {
        Object[] typeDescriptors = new TypeDescriptor[typeArguments.size()];
        for(int i=0;i<typeDescriptors.length;i++){
            typeDescriptors[i] = Metamodel.getTypeDescriptorForProducedType(typeArguments.get(i));
        }
        return MethodHandles.insertArguments(constructor, insertAt, typeDescriptors);
    }

    public static MethodHandle unboxArguments(MethodHandle method, int typeParameterCount, int filterIndex, java.lang.Class<?>[] parameterTypes) {
        MethodHandle[] filters = new MethodHandle[parameterTypes.length - typeParameterCount];
        try {
            for(int i=0;i<filters.length;i++){
                java.lang.Class<?> paramType = parameterTypes[i + typeParameterCount];
                // FIXME: more boxing for interop
                if(paramType == java.lang.String.class){
                    // ((ceylon.language.String)obj).toString()
                    MethodHandle unbox = MethodHandles.lookup().findVirtual(ceylon.language.String.class, "toString", 
                                                                               MethodType.methodType(java.lang.String.class));
                    filters[i] = unbox.asType(MethodType.methodType(java.lang.String.class, java.lang.Object.class));
                }else if(paramType == long.class){
                    // ((ceylon.language.Integer)obj).longValue()
                    MethodHandle unbox = MethodHandles.lookup().findVirtual(ceylon.language.Integer.class, "longValue", 
                                                                             MethodType.methodType(long.class));
                    filters[i] = unbox.asType(MethodType.methodType(long.class, java.lang.Object.class));
                }else if(paramType == double.class){
                    // ((ceylon.language.Float)obj).doubleValue()
                    MethodHandle unbox = MethodHandles.lookup().findVirtual(ceylon.language.Float.class, "doubleValue", 
                                                                             MethodType.methodType(double.class));
                    filters[i] = unbox.asType(MethodType.methodType(double.class, java.lang.Object.class));
                }else if(paramType == int.class){
                    // ((ceylon.language.Character)obj).intValue()
                    MethodHandle unbox = MethodHandles.lookup().findVirtual(ceylon.language.Character.class, "intValue", 
                                                                             MethodType.methodType(int.class));
                    filters[i] = unbox.asType(MethodType.methodType(int.class, java.lang.Object.class));
                }else if(paramType == boolean.class){
                    // ((ceylon.language.Boolean)obj).booleanValue()
                    MethodHandle unbox = MethodHandles.lookup().findVirtual(ceylon.language.Boolean.class, "booleanValue", 
                                                                             MethodType.methodType(boolean.class));
                    filters[i] = unbox.asType(MethodType.methodType(boolean.class, java.lang.Object.class));
                }
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Failed to filter parameter", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to filter parameter", e);
        }
        return MethodHandles.filterArguments(method, filterIndex, filters);
    }

    public static MethodHandle boxReturnValue(MethodHandle method, java.lang.Class<?> type) {
        try {
            // FIXME: more boxing for interop
            if(type == java.lang.String.class){
                // ceylon.language.String.instance(obj)
                MethodHandle box = MethodHandles.lookup().findStatic(ceylon.language.String.class, "instance", 
                                                        MethodType.methodType(ceylon.language.String.class, java.lang.String.class));
                method = MethodHandles.filterReturnValue(method, box);
            }else if(type == long.class){
                // ceylon.language.Integer.instance(obj)
                MethodHandle box = MethodHandles.lookup().findStatic(ceylon.language.Integer.class, "instance", 
                                                                     MethodType.methodType(ceylon.language.Integer.class, long.class));
                return MethodHandles.filterReturnValue(method, box);
            }else if(type == double.class){
                // ceylon.language.Float.instance(obj)
                MethodHandle box = MethodHandles.lookup().findStatic(ceylon.language.Float.class, "instance", 
                                                                     MethodType.methodType(ceylon.language.Float.class, double.class));
                return MethodHandles.filterReturnValue(method, box);
            }else if(type == int.class){
                // ceylon.language.Character.instance(obj)
                MethodHandle box = MethodHandles.lookup().findStatic(ceylon.language.Character.class, "instance", 
                                                                     MethodType.methodType(ceylon.language.Character.class, int.class));
                return MethodHandles.filterReturnValue(method, box);
            }else if(type == boolean.class){
                // ceylon.language.Boolean.instance(obj)
                MethodHandle box = MethodHandles.lookup().findStatic(ceylon.language.Boolean.class, "instance", 
                                                                     MethodType.methodType(ceylon.language.Boolean.class, boolean.class));
                return MethodHandles.filterReturnValue(method, box);
            }
            return method;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Failed to filter return value", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to filter return value", e);
        }
    }

}
