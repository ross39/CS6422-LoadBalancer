# CS6422-LoadBalancer
A load balancer for CS6422

This load balancer can distribute client requests to different servers.

To simulate the environment. 
- We use a text file as input, where each line represents a request from one customer, like: Tom, Marry, Jack.., etc. By default, we suppose the website is all the same, like www.google.com.
- We would create an empty file (like server1.txt) to act as a server that starts listening to the request from the client. The "IP address" is actually the specific directory address of this empty file.(like c://dic1/dic2/server1.txt)
- Once the request gets distributed by the load balancer, the request line (that is "Tom" ) would be written into the file acting as a server (that is server1.txt).

# Design
## logic workflow 
I draw the basic logic workflow. The rectangular in red is the main process. Each lane is the class name. If we want to use their method, use class.method() to get result. The specific classes and each's methods, attributes need to be further considered.


<img src="./pic/design_v1.png" alt="design_v1" />


