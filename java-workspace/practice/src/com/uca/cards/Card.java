package com.uca.cards;

public class Card implements Comparable<Card> {
	private final char cardId;
	private static final char SUITEPAD = 3;

	private static enum FaceCard {
		A(1), K(13), Q(12), J(11);
		FaceCard(int val) {
			this.val = (char) val;
		}
		private char val;
	}

	public Card(String card) {
		char suite = (char) (card.charAt(card.length() - 1) - '0');
		if (suite < 0 || suite > 3)
			throw new InvalidGameException("invalid suite : " + card);

		char number = parseNumber(card);
		this.cardId = (char) ((number << 2) | suite);
	}

	private char parseNumber(String card) {
		if (card.length() == 3) {
			if (card.charAt(0) == '1' && card.charAt(1) == '0')
				return 10;
			throw new InvalidGameException("invalid number on card : " + card);
		}
		if (card.charAt(0) >= '1' && card.charAt(0) <= '9')
			return (char) (card.charAt(0) - '0');
		return FaceCard.valueOf(String.valueOf(card.charAt(0))).val;
	}

	@Override
	public int compareTo(Card o) {
		return this.cardId - o.cardId;
	}

	public char getNumber() {
		return (char) (cardId >> 2);
	}

	public char getSuit() {
		return (char) (cardId & SUITEPAD);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cardId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (cardId != other.cardId)
			return false;
		return true;
	}

	public boolean sameNumber(Card o) {
		return this.getNumber() == o.getNumber();
	}

	public boolean sameSuite(Card o) {
		return this.getSuit() == o.getSuit();
	}

	@Override
	public String toString() {
		return "Card [number=" + (int) getNumber() + ", suite=" + (int) getSuit() + "]";
	}
}
