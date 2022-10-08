package thread

import (
	"Lab_4/src/lab_5/controller"
	"fmt"
	"math/rand"
	"time"
)

const UpperBound int = 10

type MyThread struct {
	arr  []int
	name string
	ctrl *controller.Controller
}

func Init(arrLen int, name string, ctrl *controller.Controller) *MyThread {
	t := new(MyThread)

	t.arr = randomSlice(arrLen)
	t.name = name
	t.ctrl = ctrl

	return t
}

func randomSlice(arrLen int) []int {
	arr := make([]int, arrLen)
	rand.Seed(time.Now().UnixNano())

	for i := 0; i < arrLen; i++ {
		elem := rand.Intn(UpperBound) + 1
		arr[i] = elem
	}

	return arr
}

func (t *MyThread) Run() {
	println(fmt.Sprintf("Thread-%s: start arr = %v", t.name, t.arr))

	for true {
		var sum int
		for _, numb := range t.arr {
			sum += numb
		}

		t.ctrl.Send(sum, t.name)

		if t.ctrl.IsFinished() {
			break
		}

		t.changeArr()
		println(fmt.Sprintf("Thread-%s: changed arr = %v", t.name, t.arr))
		time.Sleep(time.Duration(rand.Intn(100)) * time.Millisecond)
	}
}

func (t *MyThread) changeArr() {
	rand.Seed(time.Now().UnixNano())

	for i := 0; i < len(t.arr); i++ {
		var opp int
		randOpp := rand.Intn(2) % 2

		if t.arr[i] == 1 {
			opp = 1
		} else if t.arr[i] == UpperBound {
			opp = -1
		} else {
			if randOpp == 0 {
				opp = 1
			} else {
				opp = -1
			}
		}

		t.arr[i] += opp
	}
}

func (t *MyThread) getName() string {
	return t.name
}
