package com.srivn.works.smusers.db.entity.personal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.srivn.works.smusers.db.entity.util.ClsnEn;
import com.srivn.works.smusers.db.entity.util.ClsnValEn;
import org.junit.jupiter.api.Test;

class HealthInfoEnTest {

    @Test
    void test_constructor_N() {
        // Arrange and Act
        HealthInfoEn actualHealthInfoEn = new HealthInfoEn();
        // Assert
        assertNull(actualHealthInfoEn.getBloodGroup());
        assertNull(actualHealthInfoEn.getNotes());
    }

    @Test
    void test_constructor_P() {
        // Arrange
        ClsnValEn bloodGroup = new ClsnValEn(100, "B+", new ClsnEn(1, "BLOODGROUP"));
        // Act
        HealthInfoEn actualHealthInfoEn = new HealthInfoEn(bloodGroup, "Notes");

        // Assert
        assertNull(actualHealthInfoEn.getUserInfoEn());
        assertEquals("Notes", actualHealthInfoEn.getNotes());
        assertEquals("B+", bloodGroup.getValue());
    }
}

