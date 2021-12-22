
public class SavingsFormulas {
	public static double futureValueLumpSum(double cash, double interest, int 
			years) {
		double futureValue = 0;
		double result = 1;
		
		for(int i = 1; i <= years; i++) {
			result = result * (1 + interest);
		}
		futureValue = cash*result;
		
		return futureValue;
	}
	public static double futureValueLS_VariableInterest(double cash, double 
			values[]) {
		
		int n = values.length;
		
		for(int i = 0; i < n; i++) {
			cash = cash*(1+values[i]);
		}
		return cash;
	}
	public static double compoundSavingsConstant(double cash, double 
			interest, int years) {
		
		double FutureValue = 0;
		double result = 1;
		
		for(int i = 1; i <= years; i++) {
			result = result * (1 + interest);
		}
		
		double finalValue = result - 1;
		
		FutureValue = cash * (finalValue/interest);
		return FutureValue;
	}
	public static double compoundSavingsVariable(double values[], double 
			interest) {
		
		int n = values.length;
		
		double j = values[0];
		for(int i = 1; i < n; i++) {
			j += (j*interest) + values[i];
		}
		return j;
	}
}
