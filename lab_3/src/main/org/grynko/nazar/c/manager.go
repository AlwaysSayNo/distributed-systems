package main

import (
	"math/rand"
	"sync"
	"time"
)

type Manager struct {
	tableItems       *chan Table
	smokerSemaphores [3]*Semaphore
}

type Table struct {
	first  string
	second string
}

func getTableItems() Table {
	rand.Seed(time.Now().UTC().UnixNano())
	table := Table{}

	switch rand.Intn(3) {
	case 0:
		table.first = TOBACCO
		table.second = PAPER
	case 1:
		table.first = TOBACCO
		table.second = MATCHES
	case 2:
		table.first = PAPER
		table.second = MATCHES
	}
	return table
}

func managerExecutor(manager *Manager, wg *sync.WaitGroup) {
	for true {
		wg.Wait()
		time.Sleep(200 * time.Millisecond)

		if len(*manager.tableItems) != 0 {
			<-*manager.tableItems
		}

		newTableItems := getTableItems()
		println("\nManager putted", newTableItems.first, "and", newTableItems.second, "on the table:")

		*manager.tableItems <- newTableItems

		for _, semaphore := range manager.smokerSemaphores {
			wg.Add(1)
			semaphore.release(1)
		}
	}
}
