# Librairies

import os

# Functions

def clear():
    '''Clear the terminal for a better visibility (and taking care of checking the os of the user)'''
    if os.name == "posix":
        os.system("clear")
    else:
        os.system("cls")