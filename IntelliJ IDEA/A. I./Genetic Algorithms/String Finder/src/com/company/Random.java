package com.company;

public class Random
{
    public static int randomInteger(int min, int max)
    {
        java.util.Random random = new java.util.Random();

        return random.nextInt((max - min) + 1) + min;
    }

    public static int randomInteger(int max)
    {
        return randomInteger(0, max);
    }

    public static float randomFloat()
    {
        java.util.Random random = new java.util.Random();

        return random.nextFloat();
    }
}
