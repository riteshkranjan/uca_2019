from flask import Flask,render_template,redirect, request, make_response
from flask_sqlalchemy  import SQLAlchemy
from Poker import hand_rank
import os
import random 
app = Flask(__name__)
db = SQLAlchemy(app)
app.config['image_folder'] = os.path.join("static", "images")
m = {8:"Straight Flush",7:"Four of a kind",6:"Full house",5:"Flush",4:"Straight",3:"Three of a kind",2:"Two pairs",1:"Pair",0:"High Card"}
def shuffle_cards():
    cardNumbers = '23456789TJQKA'
    cardSuits = '0123'
    deck = []
    for i in cardNumbers:
        for j in cardSuits:
            deck.append("{}{}".format(i,j))
    random.shuffle(deck)
    user_cards = []
    computer_cards = []
    for i in range(0,10,2):
        user_cards.append(deck[i])
    for i in range(1,10,2):
        computer_cards.append(deck[i])
    user_cards.sort()
    computer_cards.sort()
    return user_cards,computer_cards

@app.route('/')
def home():
   return render_template("home.html") 

@app.route('/deal')
def deal_cards():
    user_cards,computer_cards = shuffle_cards()
    game_won, total_game = get_score()
    user_balance= update_balance('user',-10.0)
    user_balance=get_balance('user')
    return render_template("deal.html", user_balance=user_balance, cards = user_cards, other_card=computer_cards, total=total_game, win=game_won)

@app.route('/show', methods=['POST'])
def show_card():
    import ast 
    user_cards = ast.literal_eval(request.form.get("user_cards")) 
    computer_cards = ast.literal_eval(request.form.get("computer_cards")) 
    user_rank = hand_rank(user_cards)
    computer_rank = hand_rank(computer_cards)
    result = getWinner(user_rank,computer_rank)
    game_won, total_game = increment_score(result)
    h = History()
    h.user_cards=str(user_cards)
    h.computer_cared = str(computer_cards)
    h.winner = result
    db.session.add(h)
    db.session.commit()
    user_balance = 0.0
    if result == 'user':
        user_balance= update_balance('user',60.0)
    if result == 'computer':
        user_balance = update_balance('user',-50.0)   
    return render_template("show.html",user_balance=user_balance, cards = user_cards, other_card=computer_cards, result=result, user_desc=m[user_rank[0]], comp_desc=m[computer_rank[0]], total=total_game, win=game_won)

def get_balance(name):
    s = db.session.query(Balance).filter(Balance.user_name==name).first()
    print(s)
    print(type(s))
    if s is None:
        return 0
    return s.user_balance

def update_balance(name, amount):
    s = db.session.query(Balance).filter(Balance.user_name==name).first()
    if s is None:
        s = Balance()
        s.user_name=name
        s.user_balance=amount
        db.session.add(s)
        db.session.commit()
    else:
        s.user_balance += amount
        db.session.add(s)
        db.session.commit()
    return s.user_balance

def reset_balance(name):
    s = db.session.query(Balance).filter(Balance.user_name==name).first()
    if s is None:
        s = Balance()
        s.user_name=name
        s.user_balance=1000.0
        db.session.add(s)
        db.session.commit()
    else:
        s.user_balance=1000.0
        db.session.add(s)
        db.session.commit()
    return s.user_balance

def reset_score():
    s = db.session.query(Score).all()
    if len(s) == 0:
        s = Score()
        s.games_won = 0
        s.total_games = 0
        db.session.add(s)
        db.session.commit()
    else:
        score = s[0]
        score.games_won = 0
        score.total_games = 0
        db.session.add(score)
        db.session.commit()
def increment_score(winner):
    s = db.session.query(Score).all()
    score = None
    if len(s) == 0:
        score = Score()
        if winner == 'user':
            score.games_won = 1
        score.total_games = 1
        db.session.add(score)
        db.session.commit()
    else:
        score = s[0]
        if winner == 'user':
            score.games_won += 1
        score.total_games += 1
        db.session.add(score)
        db.session.commit()
    return score.games_won, score.total_games
def get_score():
    s = db.session.query(Score).all()
    if len(s) == 0:
        return 0,0
    return s[0].games_won, s[0].total_games

@app.route('/reset')
def reset():
    reset_score()
    reset_balance('user')
    return redirect("/")

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
            return "Draw"
    return "I don't know"

class Score(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    games_won = db.Column(db.Integer)
    total_games = db.Column(db.Integer)

class History(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    user_cards = db.Column(db.String)
    computer_cared = db.Column(db.String)
    winner = db.Column(db.String)

class Balance(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    user_balance = db.Column(db.Float)
    user_name = db.Column(db.String)

if __name__ == '__main__':
    app.config['DEBUG'] = True
    app.config['SQLALCHEMY_DATABASE_URI'] = 'postgresql://postgres:password@localhost:5433/poker'
    #print(app.config)
    db.create_all()
    app.run(host='0.0.0.0')
