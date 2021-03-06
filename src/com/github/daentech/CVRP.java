package com.github.daentech;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import com.github.daentech.algorithms.SimpleGA;
import com.github.daentech.graphics.RouteVisualiser;
import com.github.daentech.graphics.TimeGraph;

public class CVRP {

	/**
	 * @param args
	 */
	
	public static void test(){
		LimitedPriorityQueue lpq = new LimitedPriorityQueue(5, new Double(1));
		lpq.printArray(0);
		
		lpq.push(2, new Double(45));
		lpq.push(4, new Double(50));
		lpq.push(5, new Double(34));
		lpq.push(6, new Double(20));
		lpq.push(7, new Double(59));
		lpq.push(8, new Double(12));
		lpq.push(9, new Double(30));
		
		lpq.printArray(1);
	}
	
	public static void main(String[] args) {
		
		test();
		// Run each algorithm and output the result distance
		
		// Run the best by default, but allow switching on commandline input
		
		// Output a graph file
		RouteVisualiser rv = new RouteVisualiser(true);
		rv.getNodeMap();
		/*int[][] paths = {
				{1,3,4,6,7,34,25,11,56,32,1},
				{1,5,2,22,43,70, 60,50,36,1},
				{1,8,9,10,12,13,14,15,17,1}, 
				{1, 33, 52, 19, 24, 71,1},
				{1, 47, 63, 20, 25, 51, 40, 49, 1}
		};
		rv.drawPaths(paths);
		rv.drawKey(paths);
		rv.saveImage("SimpleGA");*/
		
		SimpleGA sga = new SimpleGA();
		sga.randomise();
		sga.run(300000);
		//int[][] paths = sga.getChromosomes().get(0);
		sga.printBestPath();
		rv.drawPaths(sga.getBestPath());
		rv.drawKey(sga.getBestPath());
		rv.saveImage(sga.getName());
		
		TimeGraph tg = new TimeGraph(true);
		double[] weights = sga.getWeightsOverTime();
		
		
		// Add results to the graph
		tg.addResults(weights, "SimpleGA");
		
		tg.render();
		
		tg.save(sga.getName());
		

	}

}
