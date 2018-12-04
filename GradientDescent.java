package ML;

import static java.lang.System.out;
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

	public static void main(String args[])
	{
		ComputeJ obj = new ComputeJ();

		double theta[][] = {{0}, {0}};
		double x[][] = {{1,1}, {1,2}, {1,3}};
		double y[][] = {{1}, {2}, {3}};
		int m = y.length;
		double data = obj.computeJ(theta, x, y);
		
		double alpha = 0.1;
		int iteration = 400;

		//-----------------------------------

		double multiples[][] = obj.multiply(theta, x);
		double substracts[][] = obj.subtract(multiples, y);
		double trans[][] = obj.transpose(x);
		double finals[][] = obj.multiply(substracts, trans);
		finals = multi((alpha/m), finals);
		theta = obj.subtract(theta, finals);


		for(int i = 0; i < iteration; i++)
		{
			multiples = obj.multiply(theta, x);
			substracts = obj.subtract(multiples, y);
			trans = obj.transpose(x);
			finals = obj.multiply(substracts, trans);
			finals = multi((alpha/m), finals);
			theta = obj.subtract(theta, finals);
		}



		for(int i = 0; i < theta.length; i++)
			out.println("Theta - " + i + " ---> " + theta[i][0]);

		out.println("Rounding of the theta");

		for(int i = 0; i < theta.length; i++)
			out.println("Theta - " + i + " ---> " + Math.round(theta[i][0]));
	}
}