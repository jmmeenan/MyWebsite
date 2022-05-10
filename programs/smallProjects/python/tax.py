def unmarriedTax(income):
    if(income) <= 9700:
        return (income * .1)
    elif(income) <= 39475:
        return (income * .12)
    elif(income) <= 84200:
        return (income * .22)
    elif(income) <= 160725:
        return (income * .24)
    elif(income) <= 204100:
        return (income * .32)
    elif(income) <= 510300:
        return (income * .35)
    else:
        return (income * .37)
#TO DO: IMPLEMENT FUNCTION
# 2 Finish this function
# Calculates the taxes a married person owes for 2019
# Input Parameters: amount of income in USD income
# Return Value: amount taxes owed (USD)
def marriedTax(income):
    if income <= 19400:
        return (income * .1)
    elif income <= 78950:
        return (income * .12)
    elif income <= 168400:
        return (income * .22)
    elif income <= 321450:
        return (income * .24)
    elif income <= 408200:
        return (income * .32)
    elif income <= 612350:
        return (income * .35)
    else:
        return (income * .37)

##Weird how much being married can actually save you

#TO DO: IMPLMEMENT FUNCTION
# Calculates the taxes an individual owes for 2019
# Input Parameters: amount of income in USD income and marital status Boolean

# Return Value: amount taxes owed (USD)
def tax(income, maritalStatus):
    if(maritalStatus) == True:
        return marriedTax(income)
    else:
        return unmarriedTax(income)
############################
#   DATA
############################
UrsalaIncome, UrsalaMarried = 160726, True
KaiserIncome, KaiserMarried = 501000, True
ShilahIncome, ShilahMarried = 20, True
############################
#   TESTS
############################
print("Ursala owes ", tax(UrsalaIncome, UrsalaMarried))
print("Kaiser owes ", tax(KaiserIncome, KaiserMarried))
print("Shilah owes ", tax(ShilahIncome, ShilahMarried))
print()
############################
#   DATA
############################
UrsalaIncome,UrsalaMarried = 204099, False
KaiserIncome, KaiserMarried = 510310, False
ShilahIncome, ShilahMarried = 9400, False
############################
#   TESTS
############################
print("Ursala owes ", tax(UrsalaIncome, UrsalaMarried))
print("Kaiser owes ", tax(KaiserIncome, KaiserMarried))
print("Shilah owes ", tax(ShilahIncome, ShilahMarried))
__name__ == "__main__"


