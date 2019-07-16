package com.uca.cards;

public class PokerHand extends Hand {
	public enum HandType {
		StraightFlush, FourofKind, FullHouse, Flush, Sequence, ThreeOfKind, TwoPairs, Pair, HighCard;
	}

	public PokerHand(String hand) {
		super(hand);
		if (super.hand.length != 5)
			throw new InvalidGameException("Invalid poker hand");
	}

	public HandType findHighHand() {
		sortBySuite();
		boolean isFlush = hand[0].getSuit() == hand[4].getSuit();
		sortByNumber();
		int indexOfPair = findFirstPair();

		boolean isStright = checkStraight(indexOfPair);
		if (isStright && isFlush)
			return HandType.StraightFlush;
		if (isStright)
			return HandType.Sequence;
		if (isFlush)
			return HandType.Flush;
		if (indexOfPair == -1)
			return HandType.HighCard;
		return checkForPairs(indexOfPair);
	}

	private HandType checkForPairs(int indexOfPair) {
		int indexofPairFromRight = findFirstPairFromRight();

		if (indexofPairFromRight - indexOfPair == 1)
			return HandType.Pair;

		if (indexofPairFromRight - indexOfPair == 2)
			return HandType.ThreeOfKind;

		if (indexofPairFromRight - indexOfPair == 3) {
			if (hand[indexOfPair].sameNumber(hand[indexofPairFromRight]))
				return HandType.FourofKind;
			return HandType.TwoPairs;
		}

		if (indexofPairFromRight - indexOfPair == 4) {
			if (hand[2].sameNumber(hand[1]) || hand[2].sameNumber(hand[3]))
				return HandType.FullHouse;
			return HandType.TwoPairs;
		}
		return null;
	}

	private int findFirstPairFromRight() {
		for (int i = hand.length - 1; i > 0; i--)
			if (hand[i].sameNumber(hand[i - 1]))
				return i;
		return -1;
	}

	private int findFirstPair() {
		for (int i = 0; i < hand.length - 1; i++)
			if (hand[i].sameNumber(hand[i + 1]))
				return i;
		return -1;
	}

	private boolean checkStraight(int indexofPair) {
		if (indexofPair != -1)
			return false;
		if (hand[4].getNumber() - hand[0].getNumber() == 4)
			return true;
		if (hand[4].getNumber() == 13 && hand[1].getNumber() == 10 && hand[0].getNumber() == 1)
			return true;
		return false;
	}
}
