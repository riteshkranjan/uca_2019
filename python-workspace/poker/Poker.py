class CardException(Exception):
    pass	    
def kind(n, ranks):
    "Return the first rank that this hand has exactly n of, None otherwise"
    for r in ranks:
        if ranks.count(r) == n: 
	        return r
    return None

def two_pair(ranks):
    """If there are two pair, return the two ranks as a tuple: (highest, lowest); otherwise return None."""
    highrank = kind(2, ranks)
    lowrank = kind(2, list(reversed(ranks)))
    if lowrank != highrank:
        return (highrank, lowrank)
    return None

def straight(ranks):
  'Return True if the ordered ranks form a 5 card straight'
  if len(set(ranks)) != 5:
    return False
  if (max(ranks) - min(ranks) == 4):
    return True
  if ranks[0]==14 and ranks[1]==5 and ranks[4]==2:
    ranks[0] = 1
    ranks.sort(reverse=True)
    return True
  return False
  
def flush(hand):
    'Return True if all the cards have the sane suit'
    suits = [s for r,s in hand]
    if len(set(suits)) == 1:
        return True
    return False

def card_ranks(cards):
    "Return a list of ranks sorted with higher first"
    for i in range(len(cards)):
	    if len(cards[i]) == 3:
		    cards[i] = 'T{}'.format(cards[i][2])
    ranks = ['--23456789TJQKA'.index(r) for r,s in cards]
    ranks.sort(reverse=True)
    return ranks

def hand_rank(hand):
    if len(hand) != 5:
	    raise CardException('5 cards expected only')
    ranks = card_ranks(hand)
    
    if straight(ranks) and flush(hand):
      return (8, max(ranks))
    if kind(4, ranks):
      return (7, kind(4, ranks), kind(1, ranks))
    if kind(3, ranks) and kind(2, ranks):
      return (6, kind(3, ranks), kind(2, ranks))
    if flush(hand):
      return (5, ranks)
    if straight(ranks):
      return (4, max(ranks))
    if kind(3, ranks):
      return (3, kind(3, ranks), ranks)
    if two_pair(ranks):
      return (2, two_pair(ranks), ranks)
    if kind(2, ranks):
      return (1, kind(2, ranks), ranks)
    else:
      return (0, ranks)

def poker(hands):
    "Return the best hand: poker([hand,...]) => hand"
    return max(hands, key=hand_rank)

def build_best_hand(hands_2, hands_5):
  hands = []
  hands.append(hands_5)
  
  for i in hands_2:
    hand = []
    hand.append(i)
    for j in hands_5:
      for k in hands_5:
        if j != k:
          hand.append(k)
      hands.append(hand.copy())
      hand = []
      hand.append(i)
  hand = [hands_2[0],hands_2[1],0,0,0]
  l = len(hands_5)
  for i in range(l):
    hand[2] = hands_5[i]
    for j in range(i+1,l):
      if i==j or j == l:
        continue
      hand[3] = hands_5[j]
      for k in range(j+1, l):
        if k==j or k==l:
          continue
        hand[4] = hands_5[k]
        hands.append(hand.copy())
  for i in hands:
    i.sort()
  return poker(hands)

def getWinner(user_rank, computer_rank):
    if user_rank[0] > computer_rank[0]:
        return "user"
    if user_rank[0] < computer_rank[0]:
        return "computer"
    if user_rank[0] == computer_rank[0]:
        if user_rank[1] > computer_rank[1]:
            return "user"
        if user_rank[1] < computer_rank[1]:
            return "computer"
        if user_rank[1] == computer_rank[1]:
          if len(user_rank) == 2:
            return "Draw"
          if user_rank[2] > computer_rank[2]:
            return "user"
          if user_rank[2] < computer_rank[2]:
            return "computer"
          if user_rank[2] == computer_rank[2]:
            return "Draw"
    return "I don't know"

def test():
    ubc = build_best_hand("50 60".split(), "Q3 91 Q0 42 T0".split())
    cbc = build_best_hand("53 K0".split(), "Q3 91 Q0 42 T0".split())
    uc = hand_rank(ubc)
    cc = hand_rank(cbc)
    assert uc[0] == 1
    assert uc[1] == 12
    assert uc[2] == [12, 12, 10, 9, 6]
    assert cc[0] == 1
    assert cc[1] == 12
    assert cc[2] == [13, 12, 12, 10, 9]
    assert getWinner(uc,cc) == "computer"
    sf = "2C 3C 4C 5C AC".split()
    fk = "9D 9H 9S 9C 7D".split() # 4 of a kind
    fh = "10D 10C 10H 7C 7D".split() # Full House
    tp = "KC KS TD 9H TH".split() # Two Pair
    assert build_best_hand("KS KD".split(), sf) == sf
    assert build_best_hand("2S 2D".split(),fk) == fk
    assert build_best_hand("KS QD".split(),fh) == fh
    assert build_best_hand("2S 3D".split(),tp) == tp
    assert hand_rank(sf) == (8,5)
    fkranks = card_ranks(fk)
    tpranks = card_ranks(tp)
    print(tpranks)
    assert kind(4, fkranks) == 9
    assert kind(3, fkranks) == None
    assert kind(2, fkranks) == None
    assert kind(1, fkranks) == 7
    
    assert poker([sf, fk, fh]) == sf
    assert poker([fk, fh]) == fk

    pair = "AD KC KD QS 10C".split()
    pairRank = hand_rank(pair)
    assert pairRank[0] == 1
    assert pairRank[1] == 13
    isException = False	
    invalid = "AD KC KD QS".split()
    try:
        hand_rank(invalid)
    except CardException as c:
	    print(c)
	    isException = True
    assert isException == True
    return 'tests pass'

if __name__ == '__main__':  
  print(test())
