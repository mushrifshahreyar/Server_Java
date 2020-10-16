import requests 
import random
from multiprocessing import Process

URL = "http://localhost:4040/"
  
file_name = ["page1.html","page2.html","page3.html"]

def fun1():
    for t in range(1000):

        i = random.randint(0,len(file_name)-1)
        temp_url = URL + file_name[i]
        print("Funct 1")
        print(temp_url + "\n")
        r = requests.get(url = temp_url) 
        print(r.status_code)
        print(r.request)

def fun2():
    for t in range(1000):

        i = random.randint(0,len(file_name)-1)
        temp_url = URL + file_name[i]
        print("Funct 2")
        print(temp_url + "\n")
        r = requests.get(url = temp_url) 
        print(r.status_code)
        print(r.request)


if __name__ == "__main__":
    p1 = Process(target=fun1)
    p1.start()
    p2 = Process(target=fun2)
    p2.start()
    p1.join()
    p2.join()
