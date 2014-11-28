import urllib
# import RPi.GPIO as GPIO
import time
import sys

url='http://10.0.25.240:8080/light/'
nodata = "".encode("UTF-8")

# GPIO.setmode(GPIO.BCM)
# GPIO.setup(4,GPIO.IN)

def log(msg):
    sys.stdout.write('.')
    sys.stdout.flush()

while True:
    try:
        # value=str(GPIO.input(4))
        value = "1"
        urllib.urlopen(url + value, data=nodata, timeout=1)
        log(value)
    except Exception as e:
        log('x')
    time.sleep(1)
