package com.company;

import java.util.ArrayList;

public class Population
{
    private float mutationRate;           // Mutation rate
    private DNA[] population;             // Array to hold the current population
    private ArrayList<DNA> matingPool;    // ArrayList which we will use for our "mating pool"
    private String target;                // Target phrase
    private int generations;              // Number of generations
    private boolean finished;             // Are we finished evolving?
    private int perfectScore;


    public Population(String p, float m, int num)
    {
        target = p;
        mutationRate = m;
        population = new DNA[num];

        for (int i = 0; i < population.length; i++)
        {
            population[i] = new DNA(target.length());
        }

        calculateFitness();

        matingPool = new ArrayList<>();
        finished = false;
        generations = 0;
        perfectScore = 1;
    }

    // Fill our fitness array with a value for every member of the population
    void calculateFitness()
    {
        for (int i = 0; i < population.length; ++i)
        {
            population[i].fitness(target);
        }
    }

    // Generate a mating pool
    void naturalSelection()
    {
        // Clear the ArrayList
        matingPool.clear();

        float maxFitness = 0;

        for (int i = 0; i < population.length; i++)
        {
            if (population[i].getFitness() > maxFitness)
            {
                maxFitness = population[i].getFitness();
            }
        }

        // Based on fitness, each member will get added to the mating pool a certain number of times
        // a higher fitness = more entries to mating pool = more likely to be picked as a parent
        // a lower fitness = fewer entries to mating pool = less likely to be picked as a parent
        for (int i = 0; i < population.length; i++)
        {
            float fitness = Map.map(population[i].getFitness(), 0, maxFitness, 0, 1);

            int n = (int) fitness * 100;  // Arbitrary multiplier, we can also use monte carlo method and pick two random numbers

            for (int j = 0; j < n; ++j )
            {
                matingPool.add(population[i]);
            }
        }
    }

    // Create a new generation
    void generate()
    {
        // Refill the population with children from the mating pool
        for (int i = 0; i < population.length; i++)
        {
            int a = (int) Random.randomInteger(matingPool.size() - 1);
            int b = (int) Random.randomInteger(matingPool.size() - 1);

            DNA partnerA = matingPool.get(a);
            DNA partnerB = matingPool.get(b);

            DNA child = partnerA.crossover(partnerB);

            child.mutate(mutationRate);

            population[i] = child;
        }

        generations++;
    }

    public int getGenerations()
    {
        return generations;
    }

    // Compute the current "most fit" member of the population
    String getBest()
    {
        float worldRecord = (float) 0.0;
        int index = 0;

        for (int i = 0; i < population.length; i++)
        {
            if (population[i].getFitness() > worldRecord)
            {
                index = i;
                worldRecord = population[i].getFitness();
            }
        }

        if (worldRecord == perfectScore)
        {
            finished = true;
        }

        return population[index].getPhrase();
    }

    boolean isFinished()
    {
        return finished;
    }

    // Compute average fitness for the population
    float getAverageFitness()
    {
        float total = 0;

        for (DNA dna : population)
        {
            total += dna.getFitness();
        }

        return total / population.length;
    }

    private String allPhrases()
    {
        String everything = "";

        int displayLimit = Math.min(population.length, 50);


        for (int i = 0; i < displayLimit; ++i)
        {
            everything += (population[i].getPhrase() + "\n");
        }

        return everything;
    }
}
