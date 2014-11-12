# -*- coding: cp936 -*-
from socket import *

HOST = 'localhost'
PORT = 20123
BUFSIZ = 128
ADDR = (HOST, PORT)

#创建客户端UDP套接字
udpClient = socket(AF_INET, SOCK_DGRAM)

while True:
    data = raw_input('>')
    if not data:
        break
#向服务器端发送数据
    udpClient.sendto(data,ADDR)
#接收来自服务器端的数据
    data, ADDR = udpClient.recvfrom(BUFSIZ)
    print data
    if not data:
        break

udpClient.close()
