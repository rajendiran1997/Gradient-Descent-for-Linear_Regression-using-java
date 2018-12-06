package ML;

import static java.lang.System.out;
import java.io.*;
import java.lang.Math;
import ML.ComputeJ;

public class GradientDescent
{

	static double[][] multi(double mean, double data[][])
	{
		for(int i = 0; i < data.length; i++)
		{
			data[i][0] = mean * data[i][0];
		}
		return data;
	}

	public static void main(String args[]) throws Exception
	{

		  FileInputStream in = null;
		  FileOutputStream fout = null;
		  String s = "";
	      try {
	         in = new FileInputStream("ex1data2.txt");
	         fout = new FileOutputStream("computedJval.txt");
	         
	         int c;
	         while ((c = in.read()) != -1) {
	            s += (char)c;
	         }
	     }
	     catch(Exception e)
	     {

	     }

	     int j = 0;
	     String arr[] = s.split("\n");
	     String ss[] = arr[0].split(",");
		 double y[][] = new double[arr.length][1];
		 double x[][];

	     
	    	x = new double[arr.length][ss.length];
	    	for(int i = 0; i < arr.length; i++)
	    	 {
		     	String arr2[] = arr[i].split(",");

		     	x[i][0] = 1;

		     	for(j = 0;j<arr2.length-1;j++){
		     		x[i][(j+1)] = Double.parseDouble(arr2[j]);
		     	}
		     	y[i][0] = Double.parseDouble(arr2[j]);
	     	
		 	}

	     

		ComputeJ obj = new ComputeJ();
		x = obj.x_norm(x);

		int m = y.length;

		double theta[][] = new double[x[0].length][1];

		out.println(obj.computeJ(theta, x, y));
		
		double alpha = 0.01;
		int iteration = 4000;

		double data[] = new double[iteration+1];
		//-----------------------------------

		double multiples[][] = obj.multiply(theta, x);
		double substracts[][] = obj.subtract(multiples, y);
		double trans[][] = obj.transpose(x);
		double finals[][] = obj.multiply(substracts, trans);
		finals = multi((alpha/m), finals);
		theta = obj.subtract(theta, finals);
		data[0] = obj.computeJ(theta, x, y);

		for(int i = 0; i < iteration; i++)
		{
			multiples = obj.multiply(theta, x);
			substracts = obj.subtract(multiples, y);
			trans = obj.transpose(x);
			finals = obj.multiply(substracts, trans);
			finals = multi((alpha/m), finals);
			theta = obj.subtract(theta, finals);
			data[(i+1)] = obj.computeJ(theta, x, y);
		}

		int d;
	     for(int i = 0; i<data.length;i++)
	     {
	     	String does = ""+data[i]+",\n";

	     	byte b[] = does.getBytes();
	     		fout.write(b);

	     }
	     fout.flush();


		for(int i = 0; i < theta.length; i++)
			out.println("Theta - " + i + " ---> " + theta[i][0]);

		double input[][] = {{1650,3}}; 
		double output[][] = obj.output(theta,input);
		out.println(output[0][0]);
	}
}
