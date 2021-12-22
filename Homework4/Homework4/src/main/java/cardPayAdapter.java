
public class cardPayAdapter implements cardPay {

	private String cardNum;
	private String cardName;
	private String amount;
	
	@Override
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	@Override
	public String getCardNum() {
		return cardNum;
	}

	@Override
	public void setCardOwnerName(String cardName) {
		this.cardName = cardName;		
	}

	@Override
	public String getCardOwnerName() {
		return cardName;
	}

	@Override
	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String getAmount() {
		return amount;
	}

}
