-- chapter2.hs

--declare the name of a module so it can be imported by name in a project
module Chapter2 where

  x = 10 * 5 + y

  myResult = x * 5

  y = 10

  foo x =
    let y = x * 2
        z = x ^ 2
    in 2 * y * z

  --let vs where (printInc and printInc2 return the same values)
  printInc n = print plusTwo
    where plusTwo = n + 2

  printInc2 n = let plusTwo = n + 2
                in print plusTwo


  -- the following expression:
  --   let x = 5; y = 6 in x * y
  -- can be rewritten as:

  mult1 = x * y
    where x = 5
          y = 6

  --more examples
  --let x = 3; y = 1000 in x * 3 + y
  ex1 = x * 3 + y
    where x = 3
          y = 1000

  --let y = 10; x = 10 * 5 + y in x * 5
  ex2 = x * 5
    where y = 10
          x = 10 * 5 + y

  --let x = 7; y = negate x; z = y * 10 in z / x + y
  ex3 = z / x + y
    where x = 7
          y = negate x
          z = y * 10




  --2.11 Chapter Exercises
