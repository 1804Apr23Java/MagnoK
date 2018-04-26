
import java.util.Scanner;
public class Caculadora {
	
	private static Scanner sc;
	public static void main(String[] args)
	{
		sc = new Scanner(System.in);
		
		//Declaration
		String inputType;
		String operaciones;
		
		//Input Scanner
		System.out.println("Calculator: ");
		
		System.out.println("Please your desired input type.");
		System.out.println("Type 'double' for double");
		System.out.println("Type 'integer' for integer");
		System.out.print("Input Type: ");
		inputType = sc.next();
		
		switch (inputType) {
		case "integer":
			
		case "double":
			break;
		default:
			System.err.println("Invalid. Try again.");
			System.exit(0);
		}
		
		System.out.println("Please type 'add' to add");
		System.out.println("Please type 'subtract' to subtract");
		System.out.println("Please type 'multiply' to multiply");
		System.out.println("Please type 'divide' to divide");
		System.out.print("Choose operators: ");
		operaciones = sc.next();
		
		switch (operaciones) {
		case "add":
			
		case "subtract":
		
		case "multiply":
			
		case "divide":
			break;
		default:
			System.err.println("Invalid. Try again.");
			System.exit(0);
		}
		
		
		//Methods
		gettingOperaciones(operaciones, inputType);
		
		
	}
	
	public static void gettingOperaciones(String operaciones, String inputType)
	
	{
		//Declarations
		Double num1 = 0.0;
		Double num2 = 0.0;
		Integer numero1 = 0;
		Integer numero2 = 0;
		String number1, number2;
				
		//Input and output
		System.out.println("Type the two numbers you would like to use.");
		System.out.print("Number #1: ");
		number1 = sc.next();
		
		System.out.println();
		System.out.print("Number #2: ");
		number2 = sc.next();
		
		//Conversions
		try {
			numero1 =Integer.parseInt(number1);
			numero2 = Integer.parseInt(number2);
			num1 = Double.parseDouble(number1);
			num2 = Double.parseDouble(number2);

		} catch (NumberFormatException e) {
			System.err.println("Does that look like a number to you? Please try again.");
			System.exit(0);
		}
		
		//Switch and IFs
		switch(inputType) {
		case "integer":
			
			if(operaciones.equals("add"))
			{
				doAddInt(numero1, numero2);
			}
			else 
				if(operaciones.equals("subtract"))	
			{
				doSubtractInt(numero1, numero2);
			}
			else if(operaciones.equals("multiply"))	
			{
				doMultiplyInt(numero1, numero2);
			}
			else if(operaciones.equals("divide"))
			{
				if (num2 == 0 || numero2 == 0) {
					System.err.println("Cannot divide by 0. Please try again.");
					System.exit(0);
				}
				doDivisionInt(numero1, numero2);
			}
			break;
		case "double":
			if(operaciones.equals("add"))
			{
				doAddDou(num1, num2);
			}
			else 
				if(operaciones.equals("subtract"))	
			{
				doSubtractDou(num1, num2);
			}
			else if(operaciones.equals("multiply"))	
			{
				doMultiplyDou(num1, num2);
			}
			else if(operaciones.equals("divide"))
			{
				if (num2 == 0 || numero2 == 0) {
					System.err.println("Cannot divide by 0. Please try again.");
					System.exit(0);
				}
				doDivisionDou(num1, num2);
			}
			break;
		}
		
		
		


	
	}
	
	public static void doAddInt(int numero1, int numero2)
	{
		int result;
		
		
		result = numero1 + numero2;
		
		//Output result
		System.out.println("Addition: ");
		System.out.println(numero1 + " + " + numero2 + " = " + result);
	}
	
	public static void doSubtractInt(int numero1, int numero2)
	{
		
		int result;
		
		
		result = numero1 - numero2;
		

		//Output result
		System.out.println("Subtraction: ");
		System.out.println(numero1 + " - " + numero2 + " = " + result);
	}

	public static void doMultiplyInt(int numero1, int numero2)
	{
		int result;
		
		
		result = numero1*numero2;
		

		//Output result
		System.out.println("Multiplication: ");
		System.out.println(numero1 + " X " + numero2 + " = " + result);
	}

	public static void doDivisionInt(int numero1, int numero2)
	{
		
		int result;
		
		
		
		result = numero1/numero2;
		

		//Output result
		System.out.println("Division: ");
		System.out.println(numero1 + " / " + numero2 + " = " + result);
	}
	
	public static void doAddDou(double num1, double num2)
	{
		double result;
		
		
		result = num1 + num2;
		

		//Output result
		System.out.println("Addition: ");
		System.out.println(num1 + " + " + num2 + " = " + result);
	}
	
	public static void doSubtractDou(double num1, double num2)
	{
		
		double result;
		
		
		result = num1 - num2;
		

		//Output result
		System.out.println("Subtraction: ");
		System.out.println(num1 + " - " + num2 + " = " + result);
	}

	public static void doMultiplyDou(double num1, double num2)
	{
		double result;
		
		
		result = num1*num2;
		

		//Output result
		System.out.println("Multiplication: ");
		System.out.println(num1 + " X " + num2 + " = " + result);
	}

	public static void doDivisionDou(double num1, double num2)
	{
		
		double result;
		
		
		
		result = num1/num2;
		

		//Output result
		System.out.println("Division: ");
		System.out.println(num1 + " / " + num2 + " = " + result);
	}


}