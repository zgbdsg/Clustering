/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author zhouguobing
 */
public class Kmeans {
	double[] nanvalue;

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
        
        nanvalue = new double[features[0].length];
        
        for(int i=0;i<nanvalue.length;i ++){
        	double[] row = getRow(i, features);
        	double sum = 0;
        	for(int j=0;j<row.length;j ++){
        		if(!Double.isNaN(row[j])){
        			sum += row[j];
        		}
        	}
        	
        	nanvalue[i] = 1.0*sum / row.length;
        }
        
        for(int i=0;i<K;i ++){
        	int index = Math.abs(random.nextInt()%features.length);
        	System.out.println(index);
        	if(!hasNan(features[index])){
        		clusterCenters[i] = features[index];
        	}else{
        		i --;
        	}
        }
        
        double[][] distances = new double[K][features.length];
        boolean isConvergence = false;
        
        while(!isConvergence){       
	        for(int i=0;i < K;i ++){
	        	for(int j=0;j< features.length;j ++){
	        		distances[i][j] = getDistance(clusterCenters[i], features[j]);
	        	}
	        }
	        
	        int[] newclusterIndex = updateIndex(distances);
	        
	        double convergence = getConvergence(clusterIndex,newclusterIndex);
	        
	        System.out.println(convergence);
	        
	        if(convergence >= 1){
		        clusterCenters = updateCenter(newclusterIndex,features,K);
		        clusterIndex = newclusterIndex;
	        }else{
	        	isConvergence = true;
	        }
        }
    }
    
    boolean hasNan(double[] data){
    	boolean hasnan = false;
    	
    	for(int i=0;i<data.length;i ++){
    		if(Double.isNaN(data[i])){
    			hasnan = true;
    			break;
    		}
    	}
    	return hasnan;
    }
    
    
    double[][] updateCenter(int[] clusterIndex, double[][] features, int k) {
		// TODO Auto-generated method stub
    	double[][] clustercenter = new double[k][features[0].length];
    	
    	for(int i=0;i<k;i ++){
    		List<double[]> tmp = new ArrayList<double[]>();
    		
    		for(int j=0;j<clusterIndex.length;j ++){
    			if(clusterIndex[j] == i)
    				tmp.add(features[j]);
    		}
    		
    		double[] sum =new double[features[0].length];
    		for(int j=0;j<tmp.size();j ++){
    			for(int x=0;x<sum.length;x ++){
    				sum[x] += tmp.get(j)[x];
    			}
    		}
    		
    		for(int j=0;j<sum.length;j ++){
    			clustercenter[i][j] = 1.0*sum[j] / tmp.size();
    		}
    	}
		return clustercenter;
	}

	double getConvergence(int[] clusterIndex, int[] newclusterIndex) {
		// TODO Auto-generated method stub
    	double convergence=0;
    	
    	for(int i=0;i<clusterIndex.length;i ++){
    		convergence += Math.pow(newclusterIndex[i]-clusterIndex[i],2);
    	}
		return convergence;
	}

	double getDistance(double[] center,double[] point){
    	double sum =0;
    	int len = center.length;
    	
    	for(int i=0;i<len;i ++){
    		
    		double diff = 0;
    		if(Double.isNaN(point[i])){
    			diff = center[i] - nanvalue[i];
    		}else{
    			diff = center[i] - point[i];
    		}
    		sum +=Math.pow(diff, 2);
    	}
    	
    	return sum;
    }
    
    int[] updateIndex(double[][] distances){
    	int[] clusterindex = new int[distances[0].length];
    	
    	for(int i=0;i<distances[0].length;i ++){
    		double[] irow = getRow(i, distances);
    		clusterindex[i] = minIndex(irow);
    	}
    	return clusterindex;
    }
    
    double[] getRow(int rowindex,double[][] data){
    	double[] row = new double[data.length];
    	
    	for(int i=0;i<data.length;i ++){
    		row[i] = data[i][rowindex];
    	}
    	
    	return row;
    }
    
    int minIndex(double[] row){
    	int min = 0;
    	
    	for(int i=0;i<row.length;i ++){
    		if(row[i] < row[min]){
    			min = i;
    		}
    	}
    	
    	return min;
    }
}
