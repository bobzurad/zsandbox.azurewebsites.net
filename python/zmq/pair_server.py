import zmq
import random
import sys
import time
import threading

context = zmq.Context()
socket1 = context.socket(zmq.PAIR)   # pylint: ignore E1101
socket2 = context.socket(zmq.PAIR)
socket1.connect("tcp://127.0.0.1:11001")
socket2.connect("tcp://127.0.0.1:11002")

def listen_for_message_from_client1():
    while True:
        msg = socket1.recv()
        print(msg)
        socket1.send_string("Server message to client1")
        time.sleep(1)

def listen_for_message_from_client2():
    while True:
        msg = socket2.recv()
        print(msg)
        socket2.send_string("Server message to client2")
        time.sleep(1)        

recv_thread1 = threading.Thread(target=listen_for_message_from_client1)
recv_thread1.isDaemon = True
recv_thread1.start()

recv_thread2 = threading.Thread(target=listen_for_message_from_client2)
recv_thread2.isDaemon = True
recv_thread2.start()

