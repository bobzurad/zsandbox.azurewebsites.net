module Print2 where

main :: IO()
main = do
  putStrLn "Count to 4 for me:"
  putStr "one, two, "
  putStr "three, "
  putStrLn "four!"
