import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MyMoneyTest {
	
	static MyMoney s1;
	static MyMoney s2;
	
	private static int cashIter, interestIter;
	private static double[] cashArr, interestArr;
	
	@BeforeAll
	static void setup() {
		cashIter = 0;
		interestIter = 0;
		s1 = new MyMoney("values.txt", 8, 1);
		s2 = new MyMoney("values.txt", "values2.txt", 8, 8);
		
		cashArr = new double[] {4000, 5500, 15000, 18000, 24000, 9000, 11000, 12000};
		interestArr = new double[] {.055, .075,	.045, .09, .10,	.065, .035,	.025};
	}
	
	double[] arr1 = s1.getCashValues();
	double[] arr2 = s2.getInterestValues();
	
/*For Parameterized Testing*/
	
	@ParameterizedTest
	@ValueSource(doubles = {4000, 5500, 15000, 18000, 24000, 9000, 11000, 12000})
	
	void checkCashArray(double value) {
		assertEquals(arr1[cashIter], value, "Not Same!");
		cashIter++;
	}
	
	@ParameterizedTest
	@ValueSource(doubles = {.055, .075,	.045, .09, .10,	.065, .035,	.025})
	
	void checkInterstArray(double value) {
		assertEquals(arr2[interestIter], value, "Not Same!");
		interestIter++;
	}
	
/*End of testing parameterized testing*/
	
/*For Assertion Testing*/
	
	@Test
	void checkCashArrayAssretion() {
		assertArrayEquals(cashArr, arr1, "oops...wrong values");
	}
	
	@Test
	void checkInterestArrayAssretion() {
		assertArrayEquals(interestArr, arr2, "oops...wrong values");
	}
	
/*End of testing Assertion testing*/
	
/*Testing lumpSum_ConstantRate*/

	@Test
	void lumpSum_ConstantRateTest1() {
		assertEquals(0, Math.round(new MyMoney("values.txt", 8, 1).lumpSum_ConstantRate(1400, 0.07, 10)), "wrong value");
	}
	
	@Test
	void lumpSum_ConstantRateTest2() {
		assertEquals(0, Math.round(new MyMoney("values.txt", 8, 1).lumpSum_ConstantRate(7000, 0.08, 5)), "wrong value");
	}
	
/*End of testing lumpSum_ConstantRate*/
	
/*Testing lumpSum_VariableRate*/

	@Test
	void lumpSum_VariableRateTest1() {
		assertEquals(16055, Math.round(s2.lumpSum_VariableRate(10000)), "wrong value");
	}
	
	@Test
	void lumpSum_VariableRateTest2() {
		assertEquals(120412, Math.round(s2.lumpSum_VariableRate(75000)), "wrong value");
	}
	
/*End of testing lumpSum_VariableRate*/

/*Testing compoundSavings_sameContribution*/

	@Test
	void compoundSavings_sameContributionTest1() {
		assertEquals(19343, Math.round(new MyMoney("values.txt", 8, 1).compoundSavings_sameContribution(1400, 0.07, 10)), "wrong value");
	}
	
	@Test
	void compoundSavings_sameContributionTest2() {
		assertEquals(41066, Math.round(new MyMoney("values.txt", 8, 1).compoundSavings_sameContribution(7000, 0.08, 5)), "wrong value");
	}
	
/*End of testing compoundSavings_sameContribution*/

/*Testing compoundSavings_variableContribution*/

	@Test
	void compoundSavings_variableContributionTest1() {
		assertEquals(12823, Math.round(new MyMoney("values.txt", 8, 1).compoundSavings_variableContribution(0.07)), "wrong value");
	}
	
	@Test
	void compoundSavings_variableContributionTest2() {
		assertEquals(12951, Math.round(new MyMoney("values.txt", 8, 1).compoundSavings_variableContribution(0.08)), "wrong value");
	}
	
/*End of testing compoundSavings_sameContribution*/

}
