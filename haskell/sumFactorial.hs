doOp :: (Int -> Int -> Int) -> [Int] -> Int 
doOp op [a] = a
doOp op (x:xs) = op x (doOp op xs) 

summation :: [Int] -> Int 
summation n = doOp (+) n

factorial :: Int -> Int 
factorial n = doOp (*) [1..n]

main = 
    print ("SUMMATION") >>
    print (summation [1,2,3,4]) >>
    print (summation [3,5,6,8]) >>
    print ("FACTORIAL") >>
    print (factorial 10) >>
    print (factorial 5)