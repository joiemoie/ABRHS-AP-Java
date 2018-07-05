package com.apporiented.algorithm.clustering;

import java.util.Scanner;

import javax.swing.JFrame;

import apporiented.algorithm.clustering.visualization.DendrogramPanel;

import java.util.Arrays;

public class Tester {
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 800;
	
	public static void main(String[] args){
		String [] names = new String[20];
		double[][] distances = new double[20][20];
		Scanner scanner = new Scanner(System.in);
		for(int i = 0; i<20; i++){
			names[i] = scanner.next();
		}
		for(int i = 0; i<20; i++){
			for(int j = 0; j<20; j++){
				distances[i][j] = new Double(scanner.next());
			}
		}
		double[] values = new double[400];
		for(int i = 0; i<20; i++){
			for(int j = 0; j<20; j++){
				values[i*20+j] = distances[i][j];
			}
		}
		
		java.util.Arrays.sort(values);
		for(double value:values){
			System.out.println(value);
		}
		System.out.println();
		ClusteringAlgorithm alg = new DefaultClusteringAlgorithm();
		Cluster cluster = alg.performClustering(distances, names, new AverageLinkageStrategy());
		DendrogramPanel dp = new DendrogramPanel();
		dp.setModel(cluster);
		
		//creates the JFrame
		JFrame frame = new JFrame();
		frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		frame.setTitle("Hierarchial Clustering");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(dp);
		frame.setVisible(true);
	}
}
