sub fib { 
	local $n = @_[0];
	if ( $n  <  0 ) {
   		return "Invalid input.\n";
	} elsif ( $n ==  0 ) {
   		return 0; 
	} elsif ( $n == 1 ) {
		return 1; # returns this value no matter what  
	} else {
   		return fib($n-1) + fib($n-2); 
	}
}

$result = fib(10); 
print("10th value in the Fibonacci sequence is " . $result . "\n");