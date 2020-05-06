doOp :: (Int -> Int -> Int) -> [Int] -> Int 
doOp op [a] = a
doOp op (x:xs) = op x (doOp op xs) 

main = 
  print (doOp (*) [1,2,3,4]) >> 
  print (doOp (+) [1,2,3,4])