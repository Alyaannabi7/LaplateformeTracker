# Librairies

import json

from src.clear import clear
from src.error import *

# Functions

def write_history(calc_input, result):

    '''

    Function to write history into a dictionnary to convert it in a json.
    
    PARAMETERS -
    calc_input : The function take the input entered by the user for the calculation.
    result : The function take the result of the calculation entered by the user.
    
    '''

    entry = {"input": calc_input, "result": result}

    with open("history.json", "r") as f:
        history = json.load(f)

    history.append(entry)

    with open("history.json", "w") as f:
        json.dump(history, f, indent=4)
    with open("history.json", "r") as f:
        history = json.load(f)

def clear_history():

    '''

    Function to clear the history when the user enter the correct input.
    
    '''

    with open('history.json', 'w') as f:
        json.dump([], f)

def display_history():

    '''

    Function to display the history correctly into the menu.

    RETURN -
    display_input(): Function return itself if there's an error.
    
    '''
    
    with open("history.json", "r") as f:
        history = json.load(f)

    clear()

    if history == []:
        print("There's no history yet. Please try to calculate first.")
        time.sleep(0.2)
        input("Press Enter to continue")

    else:
        
        print("History :\n")

        for e in history:
            print(f"{e["input"]} = {e["result"]}\n")

        try:
            usr_input = int(input("1. Clear History\n0. Exit\nEnter your choice...\n"))
     
        except ValueError:
            clear()
            error()
            clear()
            display_history()

        match usr_input:
            case 1:
                clear_history()
                clear()
                print("History has been cleared !")
                time.sleep(0.2)
                input("Press Enter to continue")
            case 0:
                pass
            case _:
                clear()
                error()
                clear()
                return display_history()
                
                
    clear()


        