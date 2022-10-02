package main

import (
	"context"
	"flag"
	"fmt"
	"log"
	"net"
	"net/http"
	"net/http/httputil"
	"net/url"
	"strings"
	"sync"
	"sync/atomic"
	"time"
	"container/heap"
)

type backendServer struct {
	//Define a structure for our backend servers
	//Essentially an abstract class of what we want the backend to be
	Active bool
	//Package URL for parsing url's 
	URL *url.URL
	// Syncing lock
	lock sync.RWMutex
	//No point reinventing the wheel, use go reverseproxy
	ReverseProxy *httputil.ReverseProxy
	//We need a way to add weights for our weighted least connection algo
	//Easiest would be 1 - 5 where 1 weakest and 5 is strongest
	Weight int
}

//Need a helper function to set a backendServer to active
func (b *backendServer) setActive(active bool){
	b.lock.Lock()
	b.Active = active
	b.lock.Unlock()
}

func main(){
    /*	We want to get a list of clients as input to our program 
		Store these clients in a heap/priority queue so we can sort
		and apply weighted least connections algorithm to route clients
		For testing we want to read in a file containing a million requests
		for 3 dummy websites hosted locally and see how long our loadbalancer
		takes to route the dummy traffic
	*/ 
	
}

