--Parker Buszka
--CS 231
--Project 1

module DocTransformer where
import Data.List
import Data.Char

--this checks if the inputed char is a vowel
is_vowel :: Char -> Bool
is_vowel c = elem (toLower c) "aeiou"

--switches the case to lower if the char is a vowerl, switchs case to upper if it is not
--this function looks through each char of the string to see if the char that make up the
--list is a vowel or not
switch_case :: String -> String
switch_case [] =[]
switch_case(x:xs)
    | is_vowel x = toLower x : switch_case xs
    | otherwise = toUpper x : switch_case xs

--checks if it is an nonalphabetical character or not
non_alpha :: String -> String
non_alpha [] = []
non_alpha (x:xs)
    | x == ' ' = '\n' : non_alpha  xs
    | not(isAlpha x) = '\n' : non_alpha  xs
    | otherwise = x : non_alpha xs

--gets rid of a 'A's and 'a' by replacing them with '@'s
rid_a :: String -> String
rid_a [] = []
rid_a (x:xs)
    | x == toLower 'a' = '@' : rid_a  xs
    | otherwise = x : rid_a xs

--gets rid of repetitive '\n' characters
get_rid_newline :: String -> String
get_rid_newline [] = []
get_rid_newline [x] = [x]
get_rid_newline (x:y:xs)
    | x == '\n' && x == y = get_rid_newline(y:xs)
    | otherwise = x : get_rid_newline(y:xs)

--deletes string if it is the same size as the inputed number
delete_same_size :: String -> Int -> String
delete_same_size input_str numb = unlines (filter (\w -> length w /= numb) (words input_str))

--transformDoc function needs a comment
transformDoc :: String -> Int -> String
transformDoc input_str num =  get_rid_newline(rid_a(switch_case(delete_same_size (non_alpha(reverse  input_str)) num))) -- reverses the list of chars and the string