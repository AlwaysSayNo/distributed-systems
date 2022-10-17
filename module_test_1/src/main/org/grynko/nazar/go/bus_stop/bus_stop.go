package bus_stop

import (
	"Lab_3/module_test/semaphore"
	"fmt"
	"math/rand"
	"time"
)

type BusStop struct {
	sem  semaphore.Semaphore
	name string
}

func Init(permit int, name string) *BusStop {
	bs := new(BusStop)

	bs.sem = semaphore.New(permit)
	bs.name = name

	return bs
}

func (busStop *BusStop) Stop(busName string) {
	busStop.sem.Acquire()

	println(fmt.Sprintf("%s has stopped at the bus stop '%s'.", busStop.name, busName))
	time.Sleep(time.Duration(rand.Intn(200)+300) * time.Millisecond)
	println(fmt.Sprintf("%s has left the bus stop '%s'", busStop.name, busName))

	busStop.sem.Release()
}
