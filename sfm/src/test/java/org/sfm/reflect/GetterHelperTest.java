package org.sfm.reflect;

import org.junit.Test;

import java.lang.reflect.Modifier;

import static org.junit.Assert.*;

public class GetterHelperTest {
    @Test
    public void testFieldModifiersMatchers() {
        assertTrue(GetterHelper.fieldModifiersMatches(Modifier.PUBLIC));
        assertFalse(GetterHelper.fieldModifiersMatches(Modifier.FINAL| Modifier.PUBLIC));
        assertFalse(GetterHelper.fieldModifiersMatches(Modifier.STATIC| Modifier.PUBLIC));
    }
    @Test
    public void testMethodModifiersMatchers() {
        assertTrue(GetterHelper.isPublicMember(Modifier.PUBLIC));
        assertTrue(GetterHelper.isPublicMember(Modifier.FINAL | Modifier.PUBLIC));
        assertFalse(GetterHelper.isPublicMember(Modifier.STATIC | Modifier.PUBLIC));
    }

    @Test
    public void testGetPropertyNameFromMethodName() {
        assertEquals("name", GetterHelper.getPropertyNameFromMethodName("getName"));
        assertEquals("name", GetterHelper.getPropertyNameFromMethodName("isName"));
    }

    @Test
    public void testIsGetterOnGetMethodNoArgs() throws NoSuchMethodException {
        assertTrue(GetterHelper.isGetter(Getters.class.getMethod("getValue")));
    }

    @Test
    public void testIsGetterOnMethodNoArgsReturnValue() throws NoSuchMethodException {
        assertTrue(GetterHelper.isGetter(Getters.class.getMethod("value")));
    }

    @Test
    public void testIsGetterOnGetMethodArgs() throws NoSuchMethodException {
        assertFalse(GetterHelper.isGetter(Getters.class.getMethod("getValueArgs", String.class)));
    }
    @Test
    public void testIsGetterOnVoidMethod() throws NoSuchMethodException {
        assertFalse(GetterHelper.isGetter(Getters.class.getMethod("valueVoid")));
    }



    public static class Getters {
        public String getValue() {
            return null;
        }

        public String getValueArgs(String str) {
            return null;
        }

        public String value() {
            return null;
        }

        public void valueVoid() {
        }
    }

}
