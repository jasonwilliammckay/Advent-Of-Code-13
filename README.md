# Advent-Of-Code-13

Done in java.

This assignment was a lot like #9, so I decided to see how difficult it would be to refactor a copy of #9 to work for this question. Very briefly, the purpose of #9 was, when given a list of cities and distances between them, to calculate the longest and shortest routes to tour them all. #13 was a table of people with varying states of like and dislike of sitting next to one another, and the objective was to calculate which seating plan results in the greatest like overall between them. So the two questions are much alike, except that now the 'trip' is circular (the last person sits by the first), the values can be negative (unlike distances which are zero at lowest), and the values between each person aren't going to be the same (jim might like bob, but this doesn't mean bob likes jim). 

Refactoring  only took a modest amount of effort, though many of the variable names ('cities' 'distances' etc) look a little silly in the current context. If I had planned on recycling this code in the first place, I'd have made them more generic.

The following changes were made:

Main Program:
- Modified the input string split to handle a different input format
- Also modified the input to handle negative values
- Because this is a round table, a 'trip' between three people is no longer 1-2-3, but 1-2-3-1. Added an extra iteration of the summation to account for this last-to-first item.

CityManager:
- Relaxed input validations to allow negative values (still disallows duplicates)
- Inputs no longer crosslink: two cities automatically had the same distance between one another, so setDistances() would set the distance from A->B == B->A. But each person now has their own unique like of every other person. Therefore setDistance() only sets A->B and does not assume anything about B->A

CityManagerTest:
- Replaced crosslink test with two tests that verifies A->B and B->A can check the like of one another, and that negative values work
