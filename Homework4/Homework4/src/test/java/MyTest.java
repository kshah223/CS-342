import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyTest {

	static cardPayAdapter cardPayAdapter = new cardPayAdapter();
	String cardNum, cardOwnerName, amount;
	
	@BeforeEach
	void setTest() {
		cardNum = "1234";
		cardOwnerName = "Kalp";
		amount = "01";
		cardPayAdapter.setCardNum(cardNum);
		cardPayAdapter.setCardOwnerName(cardOwnerName);
		cardPayAdapter.setAmount(amount);
	}
	
	@Test
	void test1() {
		cardNum = "1234 5678 9101 1121";
		cardPayAdapter.setCardNum(cardNum);
		assertEquals(cardNum,cardPayAdapter.getCardNum(), "NOT SAME!");
	}
	
	@Test
	void test2() {
		cardNum = "0000 0000 0000 0000";
		cardPayAdapter.setCardNum(cardNum);
		assertEquals(cardNum,cardPayAdapter.getCardNum(), "NOT SAME!");
	}
	
	@Test
	void test3() {
		cardNum = "9999 9999 9999 9999 9999";
		cardPayAdapter.setCardNum(cardNum);
		assertEquals(cardNum,cardPayAdapter.getCardNum(), "NOT SAME!");
	}
	
	@Test
	void test4() {
		cardOwnerName = "Kalpkumar Shah";
		cardPayAdapter.setCardOwnerName(cardOwnerName);
		assertEquals(cardOwnerName,cardPayAdapter.getCardOwnerName(), "NOT SAME!");
	}
	
	@Test
	void test5() {
		cardOwnerName = "Kalp Shah";
		cardPayAdapter.setCardOwnerName(cardOwnerName);
		assertEquals(cardOwnerName,cardPayAdapter.getCardOwnerName(), "NOT SAME!");
	}
	
	@Test
	void test6() {
		cardOwnerName = "Shah Kalp";
		cardPayAdapter.setCardOwnerName(cardOwnerName);
		assertEquals(cardOwnerName,cardPayAdapter.getCardOwnerName(), "NOT SAME!");
	}
	
	@Test
	void test7() {
		amount = "4554";
		cardPayAdapter.setAmount(amount);
		assertEquals(amount,cardPayAdapter.getAmount(), "NOT SAME!");
	}
	
	@Test
	void test8() {
		amount = "123456789";
		cardPayAdapter.setAmount(amount);
		assertEquals(amount,cardPayAdapter.getAmount(), "NOT SAME!");
	}
	
	@Test
	void test9() {
		amount = "0";
		cardPayAdapter.setAmount(amount);
		assertEquals(amount,cardPayAdapter.getAmount(), "NOT SAME!");
	}
	
	@Test
	void test10() {
		assertEquals(cardNum,cardPayAdapter.getCardNum(), "NOT SAME!");
		assertEquals(cardOwnerName,cardPayAdapter.getCardOwnerName(), "NOT SAME!");
		assertEquals(amount,cardPayAdapter.getAmount(), "NOT SAME!");
	}
}
