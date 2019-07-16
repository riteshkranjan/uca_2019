package com.uca.cards;

import java.util.Arrays;
import java.util.Comparator;

public class Hand {

	protected final Card[] hand;

	public Hand(String hand) {
		String[] cards = hand.split(",");
		this.hand = new Card[cards.length];
		for (int i = 0; i < cards.length; i++) {
			this.hand[i] = new Card(cards[i]);
		}
		sortHands();
		for (int i = 1; i < cards.length; i++) {
			if (this.hand[i].equals(this.hand[i - 1]))
				throw new InvalidGameException("invalid hand - duplicate card found");
		}
	}

	public void sortHands() {
		Arrays.sort(hand);
	}

	public void sortByNumber() {
		Arrays.sort(hand, new Comparator<Card>() {
			@Override
			public int compare(Card o1, Card o2) {
				return o1.getNumber() - o2.getNumber();
			}

		});
	}

	public void sortBySuite() {
		Arrays.sort(hand, new Comparator<Card>() {
			@Override
			public int compare(Card o1, Card o2) {
				return o1.getSuit() - o2.getSuit();
			}

		});
	}

	@Override
	public String toString() {
		return "Hand [count=" + hand.length + ", hand=" + Arrays.toString(hand) + "]";
	}

}
