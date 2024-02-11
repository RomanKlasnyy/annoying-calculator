package main

import (
	"fmt"
	"strconv"
	"strings"
)

var (
	msg_0  = "Enter an equation"
	msg_1  = "Do you even know what numbers are? Stay focused!"
	msg_2  = "Yes ... an interesting math operation. You've slept through all classes, haven't you?"
	msg_3  = "Yeah... division by zero. Smart move..."
	msg_4  = "Do you want to store the result? (y / n):"
	msg_5  = "Do you want to continue calculations? (y / n):"
	msg_6  = " ... lazy"
	msg_7  = " ... very lazy"
	msg_8  = " ... very, very lazy"
	msg_9  = "You are"
	msg_10 = "Are you sure? It is only one digit! (y / n)"
	msg_11 = "Don't be silly! It's just one number! Add to the memory? (y / n)"
	msg_12 = "Last chance! Do you really want to embarrass yourself? (y / n)"

	memory float64 = 0
)

func main() {
	for {
		fmt.Println(msg_0)
		var x, oper, y string
		fmt.Scanln(&x, &oper, &y)

		if x == "M" {
			x = fmt.Sprintf("%f", memory)
		}
		xValue, err := strconv.ParseFloat(x, 64)
		if err != nil {
			fmt.Println(msg_1)
			continue
		}

		if y == "M" {
			y = fmt.Sprintf("%f", memory)
		}
		yValue, err := strconv.ParseFloat(y, 64)
		if err != nil {
			fmt.Println(msg_1)
			continue
		}

		switch oper {
		case "+", "-", "*", "/":
			check(xValue, yValue, oper)
			if oper == "/" && yValue == 0 {
				fmt.Println(msg_3)
			} else {
				var result float64
				switch oper {
				case "+":
					result = xValue + yValue
				case "-":
					result = xValue - yValue
				case "*":
					result = xValue * yValue
				case "/":
					result = xValue / yValue
				}
				fmt.Println(result)

				fmt.Println(msg_4)
				var answer string
				fmt.Scanln(&answer)
				if answer == "y" {
					if !isOneDigit(result) {
						memory = result
					} else {
						msgIndex := 10
						for {
							if msgIndex == 13 {
								memory = result
								break
							} else if msgIndex == 10 {
								fmt.Println(msg_10)
							} else if msgIndex == 11 {
								fmt.Println(msg_11)
							} else if msgIndex == 12 {
								fmt.Println(msg_12)
							}
							fmt.Scanln(&answer)
							if answer == "y" {
								msgIndex++
								continue
							}
							break
						}
					}

				} else {
					memory = 0
				}
				fmt.Println(msg_5)
				fmt.Scanln(&answer)
				if answer == "n" {
					break
				}
			}
		default:
			fmt.Println(msg_2)
		}
	}
}

func check(v1, v2 float64, v3 string) {
	var msg string
	if isOneDigit(v1) && isOneDigit(v2) {
		msg += msg_6
	}
	if (v1 == 1 || v2 == 1) && v3 == "*" {
		msg += msg_7
	}
	if (v1 == 0 || v2 == 0) && strings.ContainsAny(v3, "*+-") {
		msg += msg_8
	}
	if msg != "" {
		fmt.Println(msg_9, msg)
	}
}

func isOneDigit(v float64) bool {
	return -10 < v && v < 10 && v == float64(int(v))
}
