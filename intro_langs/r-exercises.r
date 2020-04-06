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



## CHAPTER 3 ##

### 1. ###

m <- c(5, 9, 2, 3, 4, 6, 7, 0, 8, 12, 2, 9);

m[2];
// Takes the element at index 2 (1-indexed): 9

m[2:4];
// Takes the range of values from 2 to 4: 9 2 3

m[c(2,3,6)];
// List of the values at indices 2, 3, and 6: 9 2 6

m[c(1:5,10:12)];
// List of the values between indices 1 and 5, 10 and 12: 5 9 2 3 4 12 2 9

m[-(10:12)];
// List copying m except for the indices between 10 and 12: 5 9 2 3 6 6 7 0 8



### 2. ###
/*
 The data y<-c(33,44,29,16,25,45,33,19,54,22,21,49,11,24,56) contain sales of milk in litres for 5 days in three different shops (the first 3 values are for shops 1,2 and 3 on Monday, etc.) Produce a statistical summary of the sales for each day of the week and also for each shop.
 */

summary(n[1:3]);   # monday
summary(n[4:6]);   # tuesday
summary(n[7:9]);   # wednesday
summary(n[10:12]); # thursday
summary(n[13:15]); # friday

summary(c(n[1], n[4], n[7], n[10], n[13]);   // first shop
summary(c(n[2], n[5], n[8], n[11], n[14]);   // second shop
summary(c(n[3], n[6], n[9], n[12], n[15]);   // third shop



## CHAPTER 4 ##

### 1. ###

a <- matrix(c(3, -1, 2, 1), nrow=2);
// creates the matrix
//
//  3   2
//  -1  1
//

b <- matrix(c(1, 0, 4, 1, 0, -1), nrow=2);
// creates the matrix
//
//  1   4   0
//  0   1   -1
//

2 * a;
// multiples every member of a by 2:
//  6   4
//  -2  2

a * a;
// multiples every member of a by itself:
//  9   4
//  1   1

a %*% a;
// does traditional matrix multiplication between two matrices of a:
//  7   8
//  -4  -1

a %*% b;
// does traditional matrix multiplication between matrices a and b:
//  3   14  -2
//  -1  -3  -1

t(b);
// flips the rows and columns of the given matrix (matrix transposition):
//  1   0
//  4   1
//  0   -1

solve(a);
// finds the inverses of each of the members of a:
//  0.2 0.4
//  0.2 0.6



### 2. ###

a[1,];
// produces a list of the members of the first row of a: 3 2

a[2,];
// produces a list of the members of the second row of a: -1 1

a[,2];
// produces a list of the members of the second column of a: 2 1

b[1,2];
// the value of the first row and second column of b: 4

b[,2:3];
// a slice of the original matrix that includes the second and third rows:
//  4   0
//  1   -1
