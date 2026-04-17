class Operations:
    def add(num1, num2): 
        return num1 + num2

    def substract(num1, num2):
        return num1 - num2

    def multiply(num1, num2):
        return num1 * num2

    def divide(num1, num2): 
        return num1 / num2

    def power(num1, num2):
        if num2 == 0:
            return 1

        res = num1
        pwr = 1
        while pwr < num2:
            res *= num1
            pwr += 1

        return res
