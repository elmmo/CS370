fib(0,0).
fib(1,1).

fib(X,Y) :-
    X > 0,          % input validation
    A is X-1,
    B is X-2,
    fib(A, R1),     % recursion
    fib(B, R2),
    Y is R1 + R2.   % set return statement


/*

Run with:
['fibonacci-prolog.pl'].
fib(10,Result).

*/
