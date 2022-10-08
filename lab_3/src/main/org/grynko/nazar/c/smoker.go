package main

import (
	"sync"
	"time"
)

type Smoker struct {
	id         int
	item       string
	semaphore  *Semaphore
	tableItems *chan Table
}

func (semaphore Semaphore) release(n int) {
	element := 0
	for i := 0; i < n; i++ {
		semaphore <- element
	}
}

func (semaphore Semaphore) acquire(n int) {
	for i := 0; i < n; i++ {
		<-semaphore
	}
}

const (
	TOBACCO = "tobacco"
	PAPER   = "paper"
	MATCHES = "matches"
)

func smokerExecutor(smoker Smoker, wg *sync.WaitGroup) {
	for true {
		smoker.semaphore.acquire(1)
		time.Sleep(200 * time.Millisecond)

		items := <-*smoker.tableItems
		if items.first != smoker.item && items.second != smoker.item {
			println(smoker.id, "with", smoker.item, "is smoking a cigarette")
		} else {
			println(smoker.id, "with", smoker.item, "does not have suitable item")
		}

		wg.Done()
		*smoker.tableItems <- items
	}
}
