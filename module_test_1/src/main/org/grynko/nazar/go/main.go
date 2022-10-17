package main

import (
	"Lab_3/module_test/bus"
	"Lab_3/module_test/bus_stop"
	"fmt"
	"math/rand"
	"time"
)

func main() {
	stopNumber := 5
	busNumber := 7
	busStopBorder := 5

	stops := make([]bus_stop.BusStop, stopNumber)

	for i := 0; i < stopNumber; i++ {
		randomBorder := rand.Intn(busStopBorder) + 1
		name := fmt.Sprintf("Stop-%d", i+1)
		busStop := bus_stop.Init(randomBorder, name)

		stops[i] = *busStop
	}

	for i := 0; i < busNumber; i++ {
		name := fmt.Sprintf("Bus-%d", i+1)
		busImp := bus.Init(stops, name)

		go busImp.Run()
	}
	
	time.Sleep(60 * time.Minute)
}
