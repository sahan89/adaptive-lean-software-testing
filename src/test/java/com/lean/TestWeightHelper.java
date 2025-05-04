package com.lean;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestWeightHelper {
    private final WeightHelper weightHelper = new WeightHelper();

    @Test
    public void testCalculateBMINormalValues() {
        assertEquals(22.86, weightHelper.calculateBMI(70, 175), 0.01);
    }

    @Test
    public void testCalculateBMIBoundaryValues() {
        assertEquals(0.04, weightHelper.calculateBMI(0.1, 150), 0.0001);
    }

    @Test
    public void testCalculateBMIInvalidWeight() {
        assertThrows(IllegalArgumentException.class, () -> weightHelper.calculateBMI(-70, 175));
    }

    @Test
    public void testCalculateBMIInvalidHeight() {
        assertThrows(IllegalArgumentException.class, () -> weightHelper.calculateBMI(70, 0));
    }

    @Test
    public void testCalculateBMIInvalidWeightAndHeight() {
        assertThrows(IllegalArgumentException.class, () -> weightHelper.calculateBMI(-70, -175));
    }

    @Test
    public void testGetBMICategoryUnderweight() {
        assertEquals("Underweight", weightHelper.getBMICategory(50, 175));
    }

    @Test
    public void testGetBMICategoryNormalWeight() {
        assertEquals("Normal weight", weightHelper.getBMICategory(70, 175));
    }

    @Test
    public void testGetBMICategoryOverweight() {
        assertEquals("Overweight", weightHelper.getBMICategory(80, 175));
    }

    @Test
    public void testGetBMICategoryObese() {
        assertEquals("Obese", weightHelper.getBMICategory(100, 175));
    }

    @Test
    public void testGetBMICategoryBoundaryValues() {
        assertEquals("Underweight", weightHelper.getBMICategory(18.4, 100)); // BMI = 18.4
        assertEquals("Normal weight", weightHelper.getBMICategory(18.5, 100)); // BMI = 18.5
        assertEquals("Normal weight", weightHelper.getBMICategory(24.9, 100)); // BMI = 24.9
        assertEquals("Overweight", weightHelper.getBMICategory(25, 100)); // BMI = 25
        assertEquals("Overweight", weightHelper.getBMICategory(29.9, 100)); // BMI = 29.9
        assertEquals("Obese", weightHelper.getBMICategory(30, 100)); // BMI = 30
    }
}