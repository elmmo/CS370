fib:n	| prevprevnum prevnum current response|	prevprevnum := 0.	prevnum := 0.	current := 1.	1 to: n-1 do:	[ :index |		prevprevnum := prevnum.		prevnum := current.		current := prevprevnum + prevnum.	].	Transcript show: current . ' is the ' . n . '-th member of the Fibonacci sequence'.	Transcript cr.

"Run with Fibonacci new fib:10."