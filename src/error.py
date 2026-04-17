# Libraries

import time

# Functions

def error():

    '''
    Function to print an error message when it happens.
    '''

    print("Choice not recognized.")
    time.sleep(0.2)
    input("Press Enter to try again.")

def calc_error():

    '''
    Function to print an error message for the calculator when it happens.
    '''

    print("An error has appeared. Please enter an operator first.")
    time.sleep(0.2)
    input("Press Enter to try again.")

def calc_divive_zero_error():

    '''
    Function to print an error message for the divisions when divided by zero.
    '''
    
    print("Illegal operation. Cannot divide by zero.")
    time.sleep(0.2)
    input("Press Enter to try again.")

def check_calc_syntax(calc_list):

    '''
    
    Function to check an error if there is two operators or two different numbers next to each other in the list.
    
    '''
    
    check_value_int = True
    for e in calc_list:
        if e =="(":
            check_value_int = True
            continue
        if e == ")":
            check_value_int = False
            continue
        if check_value_int:
            try:
                float(e)
                check_value_int = False
            except:
                print("Syntax error. Multiple operators next to each other detected.")
                time.sleep(0.2)
                input("Press Enter to try again.")
                return False
        else:
            if e in ["+", "*", "x", ":", "/", "-","^"]:
                check_value_int = True
            else:
                print("Syntax error. Multiple numbers next to each other detected.")
                time.sleep(0.2)
                input("Press Enter to try again.")
                return False
    return True

def error_bracket_not_open():

    '''
    Function to print an error message if the user enter a closing bracket without entering an open one.
    '''

    print("Bracket input error. Bracket is closed but never opened.")
    time.sleep(0.2)
    input("Press Enter to try again.")

def error_bracket_not_closed():

    '''
    Function to print an error message if the user enter a opening bracket without closing it.
    '''

    print("Bracket input error. Bracked is opened but never closed.")
    time.sleep(0.2)
    input("Press Enter to try again.")


def error_bracket_empty():

    '''
    Function to print an error message if the user enter brackets but let it with nothing.
    '''
    
    print("Bracket input error. Backet is empty.")
    time.sleep(0.2)
    input("Press Enter to try again.")
    
def error_bracket_calculation():
    
    '''
    Function to print an error message if the user enter an incorrect calculation into the bracket.
    '''
    
    print("Bracket input error. Calculation cannot be done.")
    time.sleep(0.2)
    input("Press Enter to try again.")
    