--Parker Buszka
--CS 231
--Project 2

import System.Environment
import System.IO

--sets read to readIntger
readIntger :: String -> Int
readIntger = read

--intSquareRoot calls root, which has x equal num, and then
--x decreases by 1 until x is less than or equal to the orignal num, then it returns x
intSquareRoot :: Int -> Int
intSquareRoot num = root num
    where
        root x
            | x^2 > num = root (x - 1)
            | otherwise = x

--isLeg checks to see if two inputed ints form a right triangle
--using pythagorean theorem princples, by calculating the square
--root of a^2 + b^2
isLeg :: Int -> Int -> Bool
isLeg aLeg bLeg = cLeg^2 == aLeg^2 + bLeg^2
    where cLeg = intSquareRoot (aLeg^2 + bLeg^2)

--isHypotenuse checks to see if two inputed ints form a right triangle
--using pythagorean theorem princples, by calculating the square
--root of c^2 - a^2
isHypotenuse :: Int -> Int -> Bool
isHypotenuse aLeg cLeg =  -bLeg^2 == aLeg^2 - cLeg^2
    where bLeg = intSquareRoot (abs(max cLeg aLeg ^2 - min cLeg aLeg ^2))

--findValue finds the missing value in the pair of inputs
--so, if it is given two legs it finds the hypotenuse,
--but, if it is given a hypotenuse and a leg it finds the 
--other leg
findValue :: Int -> Int -> Int
findValue aLeg bLeg
    | isLeg aLeg bLeg = intSquareRoot (aLeg^2 + bLeg^2)
    | isHypotenuse aLeg bLeg 
        = abs (intSquareRoot (bLeg^2 - aLeg^2))
    | otherwise = -1 -- error

--rightTriangleChecker checks if the given inputs form a right triangle, if so
--they give the correct output, else they give an output stating how they are 
--not correct. I also changed the formating so it wouldnt be super long
rightTriangleChecker :: [Int] -> String
rightTriangleChecker (x:y:zs)
    | x <= 0 || y <= 0 = show(x) ++ " " 
            ++ show(y) ++ " does not make a right triangle"
    | x == y = show(x) ++ " " 
            ++ show(y) ++ " does not make a right triangle"
    | x > y = rightTriangleChecker [y, x]
    | isLeg x y = show(x) ++ " "  ++ show(y) ++ 
            " is a right triangle with hypotenuse " ++ show(findValue x y)
    | isHypotenuse x y = show(x)
            ++ " is the hypotenuse of a right triangle with legs " 
            ++  show(y) ++ " " ++ show(findValue x y)
    | otherwise = show(x) ++ " " ++ show(y) ++ " does not make a right triangle"

--wordsToInt converts the strings into ints
wordsToInt :: [String] -> [Int]
wordsToInt input = map readIntger input

--is a title to make the output txt look nice
title = "\t\tParker's Amazingly ok right triangle checker\n\n"

--makeUsable makes the inputed string usable for rightTriangleChecker
--then turns it back into a string for output
makeUsable :: String -> String
makeUsable input = unlines $ map rightTriangleChecker 
        $ map wordsToInt $ map words $ lines input

--main does input and output, reading in arguments for the text file
--names, to read in the input, then output to the output file
main :: IO ()
main = do
    [inFile, outFile] <- getArgs
    input <- readFile inFile
    writeFile outFile $ title++ makeUsable input