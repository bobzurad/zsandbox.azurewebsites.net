module TopOrLocal where

  topLevelFunction :: Integer -> Integer
  topLevelFunction x = x + woot + topLevelValue
    where woot :: Integer     --woot is a local definition, not a top level definition
          woot = 10

  topLevelValue :: Integer
  topLevelValue = 5
