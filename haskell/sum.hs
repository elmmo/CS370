main = 
  print ("SUM LIST WITH PATTERN MATCHING") >>
  print (sumList [12,3,44,89]) >>
  print (sumList [1,2,3,4,5]) >>
  print (sumList [19,22,34,41,52]) >>
  print ("SUM LIST WITH FOLDL") >> 
  print (sumListWithFold [12,3,44,89]) >>
  print (sumListWithFold [1,2,3,4,5]) >>
  print (sumListWithFold [19,22,34,41,52])

sumList :: [Int] -> Int
sumList [] = 0
sumList (x:xs) = x + sum xs

sumListWithFold :: [Int] -> Int
sumListWithFold arr = foldl (+) 0 arr