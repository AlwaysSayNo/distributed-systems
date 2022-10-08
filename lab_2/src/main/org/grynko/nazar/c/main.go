package main

import (
	"math"
	"math/rand"
)

type rival struct {
	id, energy int
}

func createRivalsEnergies(size int) chan rival {
	rivalsQ := make(chan rival, size)

	for i := 0; i < size; i++ {
		var r rival

		r.id = i + 1
		r.energy = rand.Intn(100) + 1
		rivalsQ <- r

		print(r.id, ": ", r.energy)
		if i != size-1 {
			print(", ")
		}
	}

	return rivalsQ
}

func fight(rivalRead chan rival, rivalWrite chan rival) {
	rival1 := <-rivalRead
	rival2 := <-rivalRead

	var winner rival

	if rival1.energy > rival2.energy {
		winner = rival1
	} else {
		winner = rival2
	}

	rivalWrite <- winner
	println("Rival ", rival1.id, "(", rival1.energy, ")", "vs rival ", rival2.id, "(", rival2.energy, ")", ":", winner.id, "wins")
}

func main() {
	const rivalsAmount = 16
	rounds := int(math.Log2(rivalsAmount))
	fights := rivalsAmount / 2

	rivalsQ := createRivalsEnergies(rivalsAmount)

	print("\n\n")

	for round := 1; round <= rounds; round++ {
		nextRivals := make(chan rival, fights)
		if round == rounds {
			go fight(rivalsQ, nextRivals)
			winner := <-nextRivals
			println("\nWinner of the tournament is:", winner.id, "(", winner.energy, ")")
			break
		}

		for i := 0; i < fights; i++ {
			go fight(rivalsQ, nextRivals)
		}

		fights /= 2
		rivalsQ = nextRivals
	}
}
