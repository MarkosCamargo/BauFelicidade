/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.eso.pin2.backtracking;

/**
 *
 * @author ignoi
 */
public class NewClass {

    public static void knapsack(int i, int profit, int weight) {
        if (weight <= W && profit > maxprofit) {
            maxprofit = profit;
            numbest = i;
            bestset = include;
        }
        if (promissing(i)) {
            include[i + 1] = "yes";
            knapsack(i + 1, profit + p[i + i], weight + w);
            include[i + 1] = no;
            knapsack(i + 1, profit, weight)
        }
    }

}
