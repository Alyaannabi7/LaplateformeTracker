# Libraries

from src.calculator import *
from src.clear import clear
from src.error import error
from src.history import *

# Functions

def menu():
    '''
    Function that print the menu in the terminal, and call other functions to do the logic.
    '''
    result = None
    calc_input = None

    while True:
        
            try:
                if result == None:
                    usr_input = int(input("1. Calculate\n2. History\n0. Exit\nEnter your choice...\n"))
                else:
                    usr_input = int(input("1. Calculate\n2. History\n3. Erase result\n0. Exit\nEnter your choice...\n"))
                clear()
            except ValueError:
                clear()
                error()
                clear()
                continue

            match usr_input:
                case 1:
                    result = calc_result(calc_input, result)
                case 2:
                    display_history()
                case 3:
                    if result != None:
                        result = erase_result(result)
                    else:
                        error()
                        clear()
                case 0:
                    clear()
                    exit()
                case _:
                    error()
                    clear()
