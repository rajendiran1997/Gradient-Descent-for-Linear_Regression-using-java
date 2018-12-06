package ML;

import static java.lang.System.out;

public class ComputeJ
{
	static double[] mean;
	static double[] std; 
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

	public double[] mean(double x[][])
	{
		double means[] = new double[x[0].length];
		double data = 0;
		for(int j = 1; j < x[0].length; j++)
		{
			for(int i = 0; i < x.length; i++)
			{
				data += x[i][j];
			}
			means[j-1] = (data/x.length);
		}

		return means;
	}

	public static double meanSquare(double data1, double data2)
	{
		double data = data1 - data2;
		double square = data * data;
		return square;
	}

	public double[] std(double x[][], double mean[])
	{
		double stde[] = new double[x[0].length];
		double dx[][] = new double[x.length][(x[0].length-1)];
		double sum = 0;
		int row = x.length, col = x[0].length;
		for(int j = 1; j < col; j++)
		{
			for(int i = 0; i < row; i++)
			{
				dx[i][(j-1)] = Math.pow((x[i][j] - mean[(j-1)]),2);
			}

			for(int k = 0; k < x.length; k++)
			{
				sum += dx[k][(j-1)];
			}
			// sum = (sum/x.length);
			stde[(j-1)] = Math.sqrt((sum/(row-1)));
		}
		return stde;
	}

	public double[][] X_norm(double[][] x, double[] mean, double[] std)
	{
		for(int j = 1; j < x[0].length; j++)
		{
			for(int i = 0; i < x.length; i++)
			{
				x[i][j] = ((x[i][j] - mean[j-1])/std[j-1]);
			}
		}
		return x;
	}

	public double[][] output(double[][] theta, double input[][])
	{
		ComputeJ ob = new ComputeJ();
		double done[][] = new double[1][theta.length];
		done[0][0] = 1.0;
		for(int i = 0; i < input[0].length; i++)
		{
			done[0][(i+1)] = ((input[0][i] - mean[i])/std[i]);
		}
		double data[][] = ob.multiply(theta, done);
		return data;
	}

	public double[][] x_norm(double x[][])
	{
		mean = mean(x);
		std = std(x, mean);
		x = X_norm(x, mean, std);
		return x;
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
