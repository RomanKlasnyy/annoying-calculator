msg_0 = "Enter an equation"
msg_1 = "Do you even know what numbers are? Stay focused!"
msg_2 = "Yes ... an interesting math operation. You've slept through all classes, haven't you?"
msg_3 = "Yeah... division by zero. Smart move..."
msg_4 = "Do you want to store the result? (y / n):"
msg_5 = "Do you want to continue calculations? (y / n):"
msg_6 = " ... lazy"
msg_7 = " ... very lazy"
msg_8 = " ... very, very lazy"
msg_9 = "You are"
msg_10 = "Are you sure? It is only one digit! (y / n)"
msg_11 = "Don't be silly! It's just one number! Add to the memory? (y / n)"
msg_12 = "Last chance! Do you really want to embarrass yourself? (y / n)"

memory = 0


def check(v1, v2, v3):
    msg = ''
    if is_one_digit(v1) and is_one_digit(v2):
        msg = msg + msg_6
    if (v1 == 1 or v2 == 1) and v3 == '*':
        msg = msg + msg_7
    if (v1 == 0 or v2 == 0) and v3 in '*+-':
        msg = msg + msg_8
    if msg != '':
        msg = msg_9 + msg
        print(msg)


def is_one_digit(v):
    if -10 < v < 10 and v == int(v):
        return True
    else:
        return False


while True:
    print(msg_0)
    x, oper, y = input().split()
    try:
        if x == 'M':
            x = memory
        else:
            x = float(x)
        if y == 'M':
            y = memory
        else:
            y = float(y)

        if oper not in '+-*/':
            print(msg_2)
            continue
        check(x, y, oper)
        if oper == '/' and y == 0:
            print(msg_3)
        else:
            if oper == '+':
                result = x + y
            elif oper == '-':
                result = x - y
            elif oper == '*':
                result = x * y
            elif oper == '/':
                result = x / y
            print(result)

            print(msg_4)
            answer = input()
            if answer == 'y':
                if not is_one_digit(result):
                    memory = result
                else:
                    msg_index = 10
                    while True:
                        if msg_index == 13:
                            memory = result
                            break
                        elif msg_index == 10:
                            print(msg_10)
                        elif msg_index == 11:
                            print(msg_11)
                        elif msg_index == 12:
                            print(msg_12)
                        answer = input()
                        if answer == 'y':
                            msg_index += 1
                            continue
                        break

            else:
                memory = 0
            print(msg_5)
            answer = input()
            if answer == 'n':
                break
    except ValueError:
        print(msg_1)
