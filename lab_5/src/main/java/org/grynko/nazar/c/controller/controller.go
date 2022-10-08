package controller

import (
	"fmt"
	"sync"
)

type Controller struct {
	wg           sync.WaitGroup
	received     []int
	cursor       int
	mu           sync.Mutex
	threadAmount int
	isFinished   bool
}

func Init(threadAmount int) *Controller {
	c := new(Controller)

	c.wg.Add(threadAmount)
	c.received = make([]int, threadAmount)
	c.cursor = 0
	c.mu = sync.Mutex{}
	c.threadAmount = threadAmount
	c.isFinished = false

	return c
}

func (c *Controller) Send(sum int, name string) {
	if c.isFinished {
		return
	}

	c.mu.Lock()

	println(fmt.Sprintf("Thread-%s: cursor = %d, sum = %d", name, c.cursor, sum))

	c.received[c.cursor] = sum
	c.cursor++

	if c.check() {
		c.isFinished = true
	}

	c.mu.Unlock()

	c.wg.Done()
	if c.cursor < c.threadAmount {
		c.wg.Wait()
	}

	c.cursor--

	if c.cursor == 0 {
		c.wg.Add(c.threadAmount)
	}

}

func (c *Controller) check() bool {
	if c.cursor < c.threadAmount {
		return false
	}

	checkNum := c.received[0]
	for _, num := range c.received {
		if checkNum != num {
			println("==> Amounts are not equal")

			return false
		}
	}

	println("==> Amounts are equal")
	return true
}

func (c *Controller) IsFinished() bool {
	return c.isFinished
}
