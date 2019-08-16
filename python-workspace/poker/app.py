from flask import Flask,render_template,redirect, request, make_response
from flask_sqlalchemy  import SQLAlchemy
from sqlalchemy import desc
from Poker import hand_rank, build_best_hand, getWinner
from logging.handlers import RotatingFileHandler
from time import strftime
import logging
import traceback
import ast 
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
    center_card = []
    for i in range(0,4,2):
        user_cards.append(deck[i])
    for i in range(1,4,2):
        computer_cards.append(deck[i])
    for i in range(4,9,1):
        center_card.append(deck[i])
    user_cards.sort()
    computer_cards.sort()
    center_card.sort()
    return user_cards,computer_cards,center_card

@app.route('/')
def home():
   return render_template("home.html") 

@app.route('/deal')
def deal_cards():
    user_cards,computer_cards,center_cards = shuffle_cards()
    game_won, total_game = get_score()
    user_balance= update_balance('user',-10.0)
    user_balance=get_balance('user')
    bid_total = 10.0
    h = History()
    h.user_cards=str(user_cards)
    h.computer_cards = str(computer_cards)
    h.center_cards = str(center_cards)
    h.winner = "pending"
    db.session.add(h)
    db.session.commit()
    return render_template("deal.html", h_id = h.id, stage=0,bid_total=bid_total, center_cards=center_cards,user_balance=user_balance, cards = user_cards, other_card=computer_cards, total=total_game, win=game_won)

@app.route('/raise', methods=['POST'])
def raise_bid():
    update_balance('user',-20)
    user_balance=get_balance('user')
    bid_total = float(request.form.get("bid_total")) + 20
    stage = int(request.form.get("stage")) + 1
    game_won, total_game = get_score()
    user_cards = ast.literal_eval(request.form.get("user_cards")) 
    computer_cards = ast.literal_eval(request.form.get("computer_cards")) 
    center_cards = ast.literal_eval(request.form.get("center_cards")) 
    return render_template("deal.html", h_id = request.form.get("h_id"), stage=stage,bid_total=bid_total, center_cards=center_cards,user_balance=user_balance, cards = user_cards, other_card=computer_cards, total=total_game, win=game_won)

@app.route('/fold', methods=['POST'])
def fold():
    user_cards = ast.literal_eval(request.form.get("user_cards")) 
    computer_cards = ast.literal_eval(request.form.get("computer_cards")) 
    center_cards = ast.literal_eval(request.form.get("center_cards")) 
    user_best_card = build_best_hand(user_cards,center_cards)
    computer_best_card = build_best_hand(computer_cards, center_cards)
    update_history_last(request.form.get("h_id"), "Computer-Fold",user_best_card,computer_best_card)
    return redirect("/deal")

def update_history(id, status):
    h = db.session.query(History).filter(History.id==id).first()
    h.winner = status
    db.session.commit()

def update_history_last(id, status, user_best_card, computer_best_card):
    h = db.session.query(History).filter(History.id==id).first()
    h.winner = status
    h.user_best_cards = str(user_best_card)
    h.computer_best_cards = str(computer_best_card)
    db.session.commit()

@app.route('/pass', methods=['POST'])
def pass_bid():
    user_balance=get_balance('user')
    bid_total = float(request.form.get("bid_total"))
    stage = int(request.form.get("stage")) + 1
    game_won, total_game = get_score()
    user_cards = ast.literal_eval(request.form.get("user_cards")) 
    computer_cards = ast.literal_eval(request.form.get("computer_cards")) 
    center_cards = ast.literal_eval(request.form.get("center_cards")) 
    return render_template("deal.html", h_id = request.form.get("h_id"), stage=stage,bid_total=bid_total, center_cards=center_cards,user_balance=user_balance, cards = user_cards, other_card=computer_cards, total=total_game, win=game_won)

@app.route('/show', methods=['POST'])
def show_card():
    user_cards = ast.literal_eval(request.form.get("user_cards")) 
    computer_cards = ast.literal_eval(request.form.get("computer_cards")) 
    center_cards = ast.literal_eval(request.form.get("center_cards")) 
    user_best_card = build_best_hand(user_cards,center_cards)
    computer_best_card = build_best_hand(computer_cards, center_cards)
    user_rank = hand_rank(user_best_card)
    computer_rank = hand_rank(computer_best_card)
    result = getWinner(user_rank,computer_rank)
    game_won, total_game = increment_score(result)
    update_history_last(request.form.get("h_id"), result, user_best_card, computer_best_card)
    user_balance = get_balance('user')
    bid_total = float(request.form.get("bid_total"))
    if result == 'user':
        user_balance= update_balance('user',2*bid_total)
    if result == 'Draw':
        user_balance = update_balance('user', bid_total) 
    return render_template("show.html",center_cards=center_cards,user_balance=user_balance, cards = user_cards, other_card=computer_cards, result=result, user_desc=m[user_rank[0]], comp_desc=m[computer_rank[0]], total=total_game, win=game_won)

def get_balance(name):
    s = db.session.query(Balance).filter(Balance.user_name==name).first()
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

class Score(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    games_won = db.Column(db.Integer)
    total_games = db.Column(db.Integer)

class History(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    user_cards = db.Column(db.String)
    computer_cards = db.Column(db.String)
    center_cards = db.Column(db.String)
    user_best_cards = db.Column(db.String)
    computer_best_cards = db.Column(db.String)
    winner = db.Column(db.String)

class Balance(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    user_balance = db.Column(db.Float)
    user_name = db.Column(db.String)

@app.after_request
def after_request(response):
    """ Logging after every request. """
    if response.status_code not in [500,200,304] :
        ts = strftime('[%Y-%b-%d %H:%M]')
        logger.error('%s %s %s %s %s %s',
                      ts,
                      request.remote_addr,
                      request.method,
                      request.scheme,
                      request.full_path,
                      response.status)
    return response


@app.errorhandler(Exception)
def exceptions(e):
    """ Logging after every Exception. """
    ts = strftime('[%Y-%b-%d %H:%M]')
    tb = traceback.format_exc()
    logger.error('%s %s %s %s %s 5xx INTERNAL SERVER ERROR\n%s',
                  ts,
                  request.remote_addr,
                  request.method,
                  request.scheme,
                  request.full_path,
                  tb)
    return "Internal Server Error", 500

@app.route('/log')
def content():
	text = open('poker_app.log', 'r')
	content = text.read()
	text.close()
	return render_template('log.html', text=content)

@app.route("/history")
def print_history():
    h = History.query.order_by(desc(History.id)).limit(10).all()
    return render_template('history.html', history=h)

if __name__ == '__main__':
    #app.config['DEBUG'] = True
    #app.config['SQLALCHEMY_DATABASE_URI'] = 'postgresql://postgres:password@localhost/poker'
    app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///test.db'
    #print(app.config)
    db.create_all()
    handler = RotatingFileHandler('poker_app.log', maxBytes=10000, backupCount=3)        
    logger = logging.getLogger(__name__)
    logger.setLevel(logging.ERROR)
    logger.addHandler(handler)
    logger.error('%s server started\n',strftime('[%Y-%b-%d %H:%M]'))
    app.run(host='0.0.0.0')
