package ML;

import static java.lang.System.out;

public class ComputeJ
{

	public double[][] transpose(double x[][])
	{
		double data[][] = new double[x[0].length][x.length];
		for(int i = 0; i < data.length; i++)
		{
			for(int j = 0; j < data[0].length; j++)
			{
				data[i][j] = x[j][i];
			}
		}
		return data;
	}

	public double[][] multiply(double theta[][], double x[][])
	{
		double data[][] = new double[x.length][1];

		for(int i = 0; i < data.length; i++)
		{
				data[i][0] = 0;

		}


		for(int i = 0; i < x.length; i++)
		{
			for(int j = 0; j < x[0].length; j++)
			{
				data[i][0] += x[i][j] *  theta[j][0];
			}
		}

		return data;
	}

	public double[][] subtract(double hx[][], double y[][])
	{
		int m = y.length;
		double jval[][] = new double[m][1];
		for(int i = 0; i < m; i++)
		{
			jval[i][0] = hx[i][0] - y[i][0];
		}
		return jval;
	}

	public double SquareAndSome(double jval[][])
	{
		int j = 0;
		for(int i = 0; i < jval.length; i++)
		{
			jval[i][0] = jval[i][0] * jval[i][0];
		}

		for(int i = 0; i < jval.length; i++)
		{
			j += jval[i][0];
		}
		return j;
	}

	public double computeJ(double theta[][], double x[][], double y[][])
	{
		// int tx[][] = transpose(x);
		double hx[][] = multiply(theta, x);
		double jval[][] = subtract(hx,y);
		double squaredandsome = SquareAndSome(jval);

		double finaled = squaredandsome/(2 * (y.length));
		return finaled;
	}

}