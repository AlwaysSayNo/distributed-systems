package main

import (
	"Lab_4/src/lab_5/controller"
	"Lab_4/src/lab_5/thread"
	"strconv"
	"time"
)

const threadAmount = 3
const arrLen = 10

func main() {
	ctrl := controller.Init(3)

	for i := 0; i < threadAmount; i++ {
		th := thread.Init(arrLen, strconv.Itoa(i+1), ctrl)
		time.Sleep(200 * time.Millisecond)
		go th.Run()
	}

	time.Sleep(60 * time.Minute)
}
