import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SavingsMethodTest {
	
	static MyMoney s1 = new MyMoney("values.txt", "values2.txt", 8, 8);
	
/* Testing futureValueLumpSum */
	
	@Test
	void futureValueLumpSumTest1() {
		assertEquals(0,Math.round(SavingsFormulas.futureValueLumpSum(20000, .07, 10)), "wrong value");
	}
	
	@Test
	void futureValueLumpSumTest2() {
		assertEquals(0,Math.round(SavingsFormulas.futureValueLumpSum(10000, .05, 5)), "wrong value");
	}

/* End of testing futureValueLumpSum */
	
/* Testing futureValueLS_VariableInterest */
	
	@Test
	void futureValueLS_VariableInterestTest1() {
		assertEquals(32110,Math.round(SavingsFormulas.futureValueLS_VariableInterest(20000, s1.getInterestValues())), "wrong value");
	}
	
	@Test
	void futureValueLS_VariableInterestTest2() {
		assertEquals(6422,Math.round(SavingsFormulas.futureValueLS_VariableInterest(4000, s1.getInterestValues())), "wrong value");
	}

/* End of testing futureValueLS_VariableInterest */
	
/* Testing compoundSavingsConstant */
	
	@Test
	void compoundSavingsConstantTest1() {
		assertEquals(276329,Math.round(SavingsFormulas.compoundSavingsConstant(20000, .07, 10)), "wrong value");
	}
	
	@Test
	void compoundSavingsConstantTest2() {
		assertEquals(5526,Math.round(SavingsFormulas.compoundSavingsConstant(1000, .05, 5)), "wrong value");
	}

/* End of testing compoundSavingsConstant */
	
/* Testing compoundSavingsVariable */
	
	@Test
	void compoundSavingsVariableTest1() {
		assertEquals(12823,Math.round(SavingsFormulas.compoundSavingsVariable(new MyMoney("values.txt", 8, 1).getCashValues(), 0.07)), "wrong value");
	}
	
	@Test
	void ccompoundSavingsVariableTest2() {
		assertEquals(14786,Math.round(SavingsFormulas.compoundSavingsVariable(new MyMoney("values.txt", 8, 1).getCashValues(), 0.2)), "wrong value");
	}

/* End of testing compoundSavingsConstant */
	
}
