package bus

import (
	"Lab_3/module_test/bus_stop"
	"math/rand"
	"time"
)

const (
	BACKWARD = iota
	FORWARD
)

type Bus struct {
	stops     []bus_stop.BusStop
	currIndex int
	direct    int
	name      string
}

func Init(stops []bus_stop.BusStop, name string) *Bus {
	bus := new(Bus)

	bus.stops = stops
	bus.currIndex = 0
	bus.direct = FORWARD
	bus.name = name

	return bus
}

func (bus *Bus) Run() {
	busStop := bus.stops[bus.currIndex]

	for true {
		busStop.Stop(bus.name)

		time.Sleep(time.Duration(rand.Intn(200)+300) * time.Millisecond)
		busStop = bus.getNextBusStop()
	}
}

func (bus *Bus) nextIndex() int {
	stopAmount := len(bus.stops)
	nextIndex := bus.currIndex

	if bus.currIndex == stopAmount-1 {
		nextIndex -= 1
		bus.direct = BACKWARD
	} else if bus.currIndex == 0 {
		nextIndex++
		bus.direct = FORWARD
	} else {
		switch bus.direct {
		case FORWARD:
			{
				nextIndex++
				break
			}
		case BACKWARD:
			{
				nextIndex--
				break
			}
		}
	}

	return nextIndex
}

func (bus *Bus) getNextBusStop() bus_stop.BusStop {
	bus.currIndex = bus.nextIndex()
	return bus.stops[bus.currIndex]
}
