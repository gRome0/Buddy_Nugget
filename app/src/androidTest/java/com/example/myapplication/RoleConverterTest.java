package com.example.myapplication;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RoleConverterTest {

    @Test
    public void checkTeachStr() {
        assertEquals("TEACHER", RoleConverter.fromRole(User.Role.TEACHER));
    }

    @Test
    public void adminEnumRole() {
        assertEquals(User.Role.ADMIN, RoleConverter.fromString("ADMIN"));
    }
}

