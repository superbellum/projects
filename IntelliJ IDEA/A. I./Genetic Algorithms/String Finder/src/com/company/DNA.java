package com.company;

public class DNA
{
    private char[] genes;

    private float fitness;

    // Constructor (makes a random DNA)
    public DNA(int length)
    {
        genes = new char[length];

        for (int i = 0; i < length; ++i)
        {
            genes[i] = (char) Random.randomInteger(32, 126);
        }
    }

    // Converts character array to a String
    public String getPhrase()
    {
        return new String(genes);
    }

    // Fitness function (returns floating point % of "correct" characters)
    public void fitness(final String target)
    {
        int score = 0;

        for (int i = 0; i < genes.length; ++i)
        {
            if (genes[i] == target.charAt(i))
            {
                score++;
            }
        }

        fitness = (float) score / (float) target.length();
    }

    // DNA crossover
    public DNA crossover(final DNA partner)
    {
        // A new child
        DNA child = new DNA(genes.length);

        // Pick a midpoint
        int midpoint = Random.randomInteger(genes.length - 1);

        // Half from one, half from the other
        for (int i = 0; i < genes.length; ++i)
        {
            if (i > midpoint)
            {
                child.genes[i] = genes[i];
            }
            else
            {
                child.genes[i] = partner.genes[i];
            }
        }

        return child;
    }

    // Based on a mutation probability, picks a new random character
    public void mutate(final float mutationRate)
    {
        for (int i = 0; i < genes.length; ++i)
        {
            if (Random.randomFloat() < mutationRate)
            {
                genes[i] = (char) Random.randomInteger(32, 126);
            }
        }
    }

    public char[] getGenes()
    {
        return genes;
    }

    public float getFitness()
    {
        return fitness;
    }
}