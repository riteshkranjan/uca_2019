pwd
cd uca_2019/python-workspace/poker
pwd
kill -9 `cat pid.txt`
git pull
python3 app.py &
echo $! > pid.txt
