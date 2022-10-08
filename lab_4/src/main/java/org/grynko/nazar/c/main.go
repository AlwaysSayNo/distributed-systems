package main

import (
	"sync"
	"time"
)

func main() {
	var RWLock sync.RWMutex

	cities := makeCities()
	graph := makeGraph()

	go changePrice(graph, &RWLock)
	go checkRoute(graph, cities, &RWLock)
	go changeRoute(graph, cities, &RWLock)
	go changeCity(graph, &RWLock)

	time.Sleep(1 * time.Hour)
}
