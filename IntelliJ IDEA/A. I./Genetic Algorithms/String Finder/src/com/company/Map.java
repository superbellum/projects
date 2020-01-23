package com.company;

public class Map
{
    public static float map(float valueToMap, float currentMin, float currentMax, float targetMin, float targetMax)
    {
        float currentRange = currentMax - currentMin;
        float targetRange = targetMax - targetMin;

        float a = targetRange / currentRange;

        return valueToMap * a;
    }
}
