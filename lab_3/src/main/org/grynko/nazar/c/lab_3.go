package main

import (
	"sync"
	"time"
)

type Semaphore chan int

func main() {
	tableItems := make(chan Table, 1)

	semaphore1 := make(Semaphore, 1)
	semaphore2 := make(Semaphore, 1)
	semaphore3 := make(Semaphore, 1)
	smokerSemaphores := [3]*Semaphore{&semaphore1, &semaphore2, &semaphore3}

	smokers := [...]Smoker{
		{1, TOBACCO, smokerSemaphores[0], &tableItems},
		{2, PAPER, smokerSemaphores[1], &tableItems},
		{3, MATCHES, smokerSemaphores[2], &tableItems},
	}

	manager := Manager{&tableItems, smokerSemaphores}

	var wg sync.WaitGroup

	go managerExecutor(&manager, &wg)
	for _, smoker := range smokers {
		go smokerExecutor(smoker, &wg)
	}

	time.Sleep(60 * time.Minute)
}
