from urllib.request import urlopen
from urllib.error import HTTPError
import RPi.GPIO as GPIO
import time
import sys

url='http://10.0.25.240:8080/light/'
nodata = "".encode("UTF-8")

GPIO.setmode(GPIO.BCM)
GPIO.setup(4,GPIO.IN)

while True:
    try:
        value=GPIO.input(4)
        urlopen(url + str(value), data=nodata, timeout=1)
        print(value, end="", flush=True)
    except Exception as e:
        print('x', end="", flush=True)
    time.sleep(1)
