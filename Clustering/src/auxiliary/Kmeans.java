/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliary;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author zhouguobing
 */
public class Kmeans {

    public Kmeans() {
    }

    /*
     * Input double[numIns][numAtt] features, int K
     * Output double[K][numAtt] clusterCenters, int[numIns] clusterIndex
     * 
     * clusterCenters[k] should store the kth cluster center
     * clusterIndex[i] should store the cluster index which the ith sample belongs to
     */
    public void train(double[][] features, int K, double[][] clusterCenters, int[] clusterIndex) {
        Random random = new Random(features.length);
        
        for(int i=0;i<K;i ++){
        	int index = Math.abs(random.nextInt()%features.length);
        	System.out.println(index);
        	clusterCenters[i] = features[index];
        }
        
        double[][] distances = new double[K][features.length];
        
        for(int i=0;i < K;i ++){
        	for(int j=0;j< features.length;j ++){
        		distances[i][j] = getDistance(clusterCenters[i], features[j]);
        	}
        }
        
        clusterIndex = updateIndex(distances);
    }
    
    double getDistance(double[] center,double[] point){
    	double sum =0;
    	int len = center.length;
    	
    	for(int i=0;i<len;i ++){
    		double diff = center[i] - point[i];
    		sum +=Math.pow(diff, 2);
    	}
    	
    	return sum;
    }
    
    int[] updateIndex(double[][] distances){
    	int[] clusterindex = new int[distances.length];
    	
    	return clusterindex;
    }
    
    double[] getRow(int rowindex,double[][] data){
    	double[] row = new double[data[0].length];
    	
    	for(int i=0;i<data.length;i ++){
    		row[i] = data[i][rowindex];
    	}
    	
    	return row;
    }
    
    int minIndex(double[] row){
    	int min = 0;
    	
    	for(int i=0;i)
    }
}
