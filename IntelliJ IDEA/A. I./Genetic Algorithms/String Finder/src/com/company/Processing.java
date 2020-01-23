package com.company;

public class Processing
{
    private String target;
    private int popmax;
    private float mutationRate;
    private Population population;

    void setup()
    {
        target = "To be or not to be.";
        popmax = 150;
        mutationRate = (float) 0.1;

        // Create a populationation with a target phrase, mutation rate, and populationation max
        population = new Population(target, mutationRate, popmax);
    }

    void draw()
    {
        // Generate mating pool
        population.naturalSelection();
        //Create next generation
        population.generate();
        // Calculate fitness
        population.calculateFitness();

        displayInfo();

        // If we found the target phrase, stop
        if (population.isFinished())
        {
            return;
        }

        try
        {
            //Thread.sleep(10);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }

        System.out.print("\033[H\033[2J");

        draw();
    }

    private void displayInfo()
    {
        String answer = population.getBest();

        System.out.println("Best phrase: " + answer);

        System.out.println("Total generations: " + population.getGenerations());
        System.out.println("Average fitness: " + population.getAverageFitness());
        System.out.println("Total population: " + popmax);
        System.out.println("Mutation rate: " + (int) (mutationRate * 100) + "%");
    }

}
