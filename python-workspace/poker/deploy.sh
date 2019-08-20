kill -9 `cat pid.txt`
git pull
python3 app.py &
echo $! > pid.txt
