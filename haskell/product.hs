main = 
  print ("MULTPLY LIST WITH PATTERN MATCHING") >>
  print (prodList [12,3,44,89]) >>
  print (prodList [1,2,3,4,5]) >>
  print (prodList [19,22,34,41,52]) >>
  print ("MULTIPLY LIST WITH FOLDL") >> 
  print (prodListWithFold [12,3,44,89]) >>
  print (prodListWithFold [1,2,3,4,5]) >>
  print (prodListWithFold [19,22,34,41,52])

prodList :: [Int] -> Int
prodList [] = 1
prodList (x:xs) = x * prodList xs

prodListWithFold :: [Int] -> Int
prodListWithFold arr = foldl (*) 1 arr