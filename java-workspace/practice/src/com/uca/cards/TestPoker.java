package com.uca.cards;

import com.uca.test.Unittest;

public class TestPoker {

	@Unittest()
	public void testPokerHighHand() {
		testPoker("Q0,K0,90,100,J0", PokerHand.HandType.StraightFlush);
		testPoker("Q0,K0,A0,100,J0", PokerHand.HandType.StraightFlush);
		testPoker("K0,20,J0,80,A0", PokerHand.HandType.Flush);
		testPoker("K0,21,20,22,23", PokerHand.HandType.FourofKind);
		testPoker("K0,K1,102,100,101", PokerHand.HandType.FullHouse);
		testPoker("K0,21,J0,100,90", PokerHand.HandType.HighCard);
		testPoker("K0,K1,J0,100,90", PokerHand.HandType.Pair);
		testPoker("K0,Q1,J2,100,90", PokerHand.HandType.Sequence);
		testPoker("K0,Q1,J2,100,A0", PokerHand.HandType.Sequence);
		testPoker("A0,21,32,40,50", PokerHand.HandType.Sequence);
		testPoker("K0,K1,K2,100,90", PokerHand.HandType.ThreeOfKind);
		testPoker("K0,K1,J2,100,J0", PokerHand.HandType.TwoPairs);
		testPoker("K0,K1,102,100,J0", PokerHand.HandType.TwoPairs);
		testPoker("K0,K1,K2,100,101", PokerHand.HandType.FullHouse);
	}

	@Unittest(expected = InvalidGameException.class, msg = "invalid hand - duplicate card found")
	public void testPokerThrowsExceptionWithDuplicateCards() {
		new PokerHand("K0,K0,K2,100,101");
	}

	@Unittest(expected = InvalidGameException.class, msg = "Invalid poker hand", myMessage=Unittest.FailedMessage.class)
	public void testPokerThrowsExceptionWithMoreThan5Cards() {
		new PokerHand("K0,K1,K2,100,101,K3");
	}

	@Unittest(expected = InvalidGameException.class, msg = "invalid suite : K4")
	public void testPokerThrowsExceptionWithInvalidSuit() {
		new PokerHand("K4,K0,K2,100,101");
	}

	@Unittest(expected = InvalidGameException.class, msg = "invalid number on card : 112")
	public void testPokerThrowsExceptionWithInvalidCardNumber() {
		System.out.println(new PokerHand("112,K0,K2,100,101").findHighHand().name());
	}

	@Unittest(expected = IllegalArgumentException.class)
	public void testPokerThrowsExceptionWithInvalidCardNumber2() {
		new PokerHand("B2,K0,K2,100,101");
	}

	private static void testPoker(String input, PokerHand.HandType expected) {
		PokerHand p = new PokerHand(input);
		PokerHand.HandType actual = p.findHighHand();
		if (actual != expected) {
			System.out.println(p.toString());
			throw new AssertionError(String.format("expencted %s for %s but actual is %s", expected, input, actual));
		}
	}
}
