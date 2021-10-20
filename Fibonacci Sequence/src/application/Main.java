package application;

import java.math.BigInteger;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
* The Main program implements an application that
* implements the Fibonacci function in both the 
* iterative and recursive fashions. It logs the 
* runtime of each method and plot that on a line
* chart. 
* 
* @author  Natalie Ayala
* @version 1.0
* @since   2021-10-20 
*/

public class Main extends Application {

	//sets value for the range of fibonacci calculations
	int fibonacciRange = 30;
	
	@Override public void start(Stage stage) {
		
		//sets the value of the property title
		stage.setTitle("Recursive vs Iterative Fibonacci Sequence");
		
		//Create a new instance of an auto-ranging NumberAxis
		final NumberAxis xAxis = new NumberAxis();
		
		//Create a new instance of an auto-ranging NumberAxis
		final NumberAxis yAxis = new NumberAxis();
		
		//sets the value of the x-axis label
		xAxis.setLabel("Input");
		
		//sets the value of the y-axis label
		yAxis.setLabel("Time (nanoseconds)");

		//creates a new instance of a LineChart 
		final LineChart<Number,Number> lineChart = 
				new LineChart<Number,Number>(xAxis,yAxis);

		//sets the value of the property title.
		lineChart.setTitle("Recursive vs Iterative Implementations of the Fibonacci Sequence");

		//series1 stores data for recursive method
		XYChart.Series series1 = new XYChart.Series();
		
		//sets the value of the series name
		series1.setName("Recursive Series");

		//series2 stores data for iterative method
		XYChart.Series series2 = new XYChart.Series();
		
		//sets the value of the series name
		series2.setName("Iterative Series");

		//starting point for logging iterative runtime
		int iterativeStartTime = (int) System.nanoTime();

		//calculates runtime for iterative method of fibonnaci 
		//function. Adds runtime data and corresponding int i 
		//into chart series 
		for (int i = 0; i < fibonacciRange; i++) {
			fibonacciIterative(i);
			int iterativeEndTime   = (int) System.nanoTime();
			int iterativeTotalTime = iterativeEndTime - iterativeStartTime;
			series2.getData().add(new XYChart.Data(i, iterativeTotalTime));
		}  

		//starting point for logging recursive runtime
		int recursiveStartTime = (int) System.nanoTime();

		//calculates runtime for recursive method of fibonnaci 
		//function. Adds runtime data and corresponding int i 
		//into chart series 
		for (int i = 0; i < fibonacciRange; i++) {
			fibRecursive(i);
			int recursiveEndTime   = (int) System.nanoTime();
			int recursiveTotalTime = recursiveEndTime - recursiveStartTime;
			series1.getData().add(new XYChart.Data(i, recursiveTotalTime));
		}  

		//defines chart elements
		Scene scene  = new Scene(lineChart,800,600); 
		
		//adds series data to chart
		lineChart.getData().addAll(series1, series2);

		//specifies the scene to be used on this stage.
		stage.setScene(scene);
		
		//displays chart by setting visibility to true
		stage.show();
	}


	/**
	 * Executes the Fibonacci function using an iterative method 
	 * for a range of positions based on the value of fibonacciRange. 
	 * 
	 * @param 	number An integer that will be used to calculate the fibonacci sequence
	 * @return 	The calculated value of the Fibonacci number
	 */

	public static BigInteger fibonacciIterative(int number) {
		if (number == 1 || number == 2) {
			return BigInteger.ONE;
		}
		BigInteger fibo1 = BigInteger.ONE;
		BigInteger fibo2 = BigInteger.ONE;
		BigInteger fibonacci = BigInteger.ZERO;
		for (int i = 3; i <= number; i++) {
			fibonacci = fibo1.add(fibo2);
			fibo1 = fibo2;
			fibo2 = fibonacci;
		}
		return fibonacci;
	}

	/**
	 * Executes the Fibonacci function using a recursive method 
	 * for a range of positions based on the value of fibonacciRange. 
	 * 
	 * @param n the number input to be used in the Fibonacci calculation
	 * @return the calculated value of the Fibonacci number
	 */

	public static long fibRecursive(long n) {
		if ((n == 0) || (n == 1))
			return n;
		else
			return fibRecursive(n - 1) + fibRecursive(n - 2);
	}


	/**
	 * This is the main method which makes use of addNum method.
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */
	
	public static void main(String[] args) {
		launch(args);
	}
}

