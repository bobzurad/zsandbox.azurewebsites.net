import zmq
import random
import sys
import time

context = zmq.Context()
socket = context.socket(zmq.PAIR)   # pylint: ignore E1101
socket.bind("tcp://127.0.0.1:11001")

while True:
    socket.send_string("client1 message to server")
    msg = socket.recv()
    print(msg)
    time.sleep(1)