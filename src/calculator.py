# Libraries

import time
import re

from src.operations import *
from src.clear import clear
from src.error import *
from src.history import *

# Functions

def calc_choice(result):

    '''
    The computer split the user input in a list to be calculated
    
    PARAMETERS -
    result : Function take the result to know if there is one or not
    
    RETURNS -
    calc_input: return the calcul entered by the user for the result display in the menu
    result: return the result of the calcul.
    '''

    if result == None:
        print("Current operators taken into accounts : ")
        print("+  |  -")
        print("*  or  x")
        print("/  or  :")
        calc_input = input("Write your calculation here...\n").strip().replace(",", ".")
        if not calc_input:
            calc_error()
            clear()
            return calc_choice(result)
        
        if not (calc_input[0].isdigit() or calc_input[0] == "("):
            error()
            clear()
            return calc_choice(result)

        if not (calc_input[-1].isdigit() or calc_input[-1] == ")"):
            error()
            clear()
            return calc_choice(result)
        
        calc_split = re.findall(r'\d+\.\d+|\d+|[()+\-*/x:^]', calc_input)
        # Check for syntax like *(-1)
        i = 0
        while i < len(calc_split):
            if calc_split[i] == "-" and i > 0 and calc_split[i-1] == "(":
                if i+1 < len(calc_split) and re.fullmatch(r'\d+(\.\d+)?', calc_split[i+1]):
                    calc_split[i:i+2] = [f"-{calc_split[i+1]}"]
            i += 1

        if check_calc_syntax(calc_split):
            result = operation_prio_calc(calc_split, result)
            clear()
            return calc_input, result
        else:
            clear()
            return calc_choice(result)

    else:
        calc_input = input(f"Continue calculation : {result} ...\n")

        if not calc_input: 
            calc_error()
            clear()
            return calc_choice(result)
        
        elif calc_input[0] not in ["+", "*", "x", ":", "/", "-","^"]:
            calc_error()
            clear()
            return calc_choice(result)
        
        if not (calc_input[-1].isdigit() or calc_input[-1] == ")"):
            error()
            clear()
            return calc_choice(result)

        calc_split = re.findall(r'\d+\.\d+|\d+|[()+\-*/x:^]', calc_input)
        # Check for syntax like *(-1)
        i = 0
        while i < len(calc_split):
            if calc_split[i] == "-" and i > 0 and calc_split[i-1] == "(":
                if i+1 < len(calc_split) and re.fullmatch(r'\d+(\.\d+)?', calc_split[i+1]):
                    calc_split[i:i+2] = [f"-{calc_split[i+1]}"]
            i += 1

        calc_split.insert(0, str(result))
        if check_calc_syntax(calc_split):
            result = operation_prio_calc(calc_split, result)
            clear()
            return calc_input, result
        else:
            clear()
            return calc_choice(result)

def operation_prio_calc(calc_split:list, result):

    '''
    Function to manage and calcul with the operations prio
    
    PARAMETERS -
    calc_split : the list that contain all the numbers and the operators
    
    RETURNS -
    result: return the result of the calcul.
    '''

    calc_split = resolve_parentheses(calc_split)

    if calc_split == []:
        return None
    
    i = 0
    while i < len(calc_split):
        if calc_split[i] == "^":
            num1 = float(calc_split[i-1])
            num2 = int(float(calc_split[i+1]))

            op_result = Operations.power(num1, num2)

            calc_split[i-1:i+2] = [str(op_result)]
            i -= 1
        else:
            i += 1
    i = 0
    
    while i < len(calc_split):
        if calc_split[i] in ["x", "*", ":", "/"]:
            num1 = float(calc_split[i-1])
            num2 = float(calc_split[i+1])
            
            if calc_split[i] in ["x", "*"]:
                op_result = Operations.multiply(num1, num2)
            else:
                # Check for division by zero
                try :
                    op_result = Operations.divide(num1, num2)
                except:
                    clear()
                    calc_divive_zero_error()
                    clear()
                    return None
                    
                
            calc_split[i-1 : i+2] = [str(op_result)]
            i -= 1 
        else:
            i += 1

    i = 0
    while i < len(calc_split):
        if calc_split[i] in ["+", "-"]:
            num1 = float(calc_split[i-1])
            num2 = float(calc_split[i+1])
            
            if calc_split[i] in ["+"]:
                op_result = Operations.add(num1, num2)
            else:
                op_result = Operations.substract(num1, num2)
            
            calc_split[i-1 : i+2] = [str(op_result)]
            i -= 1 
        else:
            i += 1
                
    result = calc_split[0]

    return result

def resolve_parentheses(calc_split: list):

    """
    todo
    """

    # Check for brackets
    stack = []
    for token in calc_split:
        if token == "(":
            stack.append("(")
        elif token == ")":
            if not stack:
                error_bracket_not_open()
                return []
            stack.pop()

    if stack:
       error_bracket_not_closed()
       return []
   
    calc = calc_split[:]  

    while "(" in calc:

        open_index = len(calc) - 1 - calc[::-1].index("(")
        close_index = calc.index(")", open_index)

        sub_expr = calc[open_index + 1 : close_index]

        if not sub_expr:
            error_bracket_empty()
            return []

        sub_result = operation_prio_calc(sub_expr, None)

        if sub_result is None:
            error_bracket_calculation()
            return []

        calc[open_index : close_index + 1] = [str(sub_result)]

    return calc

def calc_result(calc_input, result):

    '''
    Function that take the result of the calcul and do the result message for the menu
    
    PARAMETERS -
    calc_input : the user input entered in the calcul
    result : the result of the calcul 
    
    RETURNS -
    result: return the result of the calcul for the menu to keep it in memory.
    '''

    if result == None:

        calc_input, result = calc_choice(result)

        if result != None:
                
            if (float(result) % 1) == 0.0:
                result = int(float(result))
            else :
                result = float(result)
            
            print(f"{calc_input} is equal to {round(result, 10)}")
            time.sleep(0.2)
            input("Press Enter to continue..")
            clear()

        write_history(calc_input, result)

        return result
    else:

        previous_result = result

        calc_input, new_result = calc_choice(result)

        if new_result != None:

            if (float(new_result) % 1) == 0.0:
                new_result = int(float(new_result))
            else :
                new_result = float(new_result)

            if (float(new_result) % 1) == 0.0:
                print(f"{previous_result} {calc_input} is equal to {int(float(new_result))}")
            else:
                print(f"{previous_result} {calc_input} is equal to {round(float(new_result), 10)}")
            time.sleep(0.2)
            input("Press Enter to continue..")
            clear()

        result = new_result

        write_history(f"{previous_result} {calc_input}", result)

        return result


def erase_result(result):

    '''
    Function that erase the result memory to go back to a new calcul
    
    PARAMETERS -
    result : the result of the calcul 
    
    RETURNS -
    result: return the new result (None) for the menu to keep it in memory and reset.
    '''

    result = None

    print("The result has been reset !")
    time.sleep(0.2)
    input("Press Enter to continue..")
    clear()
    return result