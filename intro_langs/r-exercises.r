# http://www.sci.csueastbay.edu/~sfan/SubPages/CSUteach/st6205/lecture%20notes/BestFirstRTutorial.pdf

## CHAPTER 2 ##

### 1. ###

x <- c(4,2,6);
y <- c(1,0,-1);
# both produce a list of values (a vector) of scalar values based on params

length(x);
# The number of elements in the list x: 3

sum(x);
# The sum of all the elements in x: 12

sum(x^2);
# The sum of all the elements in x after having been squared: 56

x + y;
# The dot product of the two vectors: 5 2 5

x * y;
# Each member of the vector multiplied by the member in the corresponding position of the next vector: 4 0 -6

x - 2;
# Subtract 2 from every member of x: 2 0 4

x^2;
# The square of every member of x: 16 4 36



### 2. ###

7:11;
# A list of integers from 7 to 11: 7 8 9 10 11

seq(2, 9);
# A list of integers from 2 to 9: 2 3 4 5 6 7 8 9

seq(4, 10, by=2);
# A list of integers from 4 to 10 with intervals of 2: 4 6 8 10

seq(3, 30, length = 10);
# A list of integers from 3 to 30 with intervals such that there are 10 values

seq(6, -4, by=-2);
# A list of integers from 6 to -4 with intervals of -2: 6 4 2 0 -2 -4



### 3. ###

rep(2,4);
# A list of length 4 where every value = 2: 2 2 2 2

rep(c(1,2), 4);
# A list that concatenates the lists produced by the first arg 4 times: 1 2 1 2 1 2 1 2

rep(1:4, 4);
# A list that concatenates the list of integers between 1 to 4, 4 times: 1 2 3 4 1 2 3 4 1 2 3 4 1 2 3 4

rep(1:4, rep(3,4));
# 1 1 1 2 2 2 3 3 3 4 4 4
# First param: 1 2 3 4
# Second param: 3 3 3 3
# Repeats every element the number of times matching its corresponding digit and concatenates those repetitions into a single list 



### 4. ###

rep(6,6);
# 6 6 6 6 6 6

rep(c(5,8), 4);
# 5 8 5 8 5 8 5 8

rep(c(5,8), rep(4,2));
# 5 5 5 5 8 8 8 8
