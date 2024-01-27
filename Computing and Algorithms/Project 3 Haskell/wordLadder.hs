--Parker Buszka
--CS 231
--Project 3

-- needed imports, IO for input output, System.Environment
-- for getting arguments 
import System.IO
import System.Environment
import Data.Char

-- constructor for the binary search tree
data BSTnode = MTnode | Node BSTnode String BSTnode deriving (Show)

-- this inserts nodes into the binary search tree, this is reversed
-- so it will not be a linear list
insertBST :: BSTnode -> String -> BSTnode
insertBST MTnode x = Node MTnode (reverse x) MTnode
insertBST (Node t1 v t2) x
   | rev < v = Node (insertBST t1 x) v t2
   | rev == v = Node t1 v t2 
   | otherwise = Node t1 v (insertBST t2 x)
   where rev = reverse x

-- this inserts a list of used words into a binary search tree. 
-- This is for updating the usedTree, so we can keep track of what
-- words we have used
insertBSTList :: BSTnode -> [String] -> BSTnode
insertBSTList node [] = node
insertBSTList node (x:xs) = insertBSTList (insertBST node x) xs

-- this is used to add the dictonary words into a tree
-- it takes in a list of word strings and returns the tree
makeTree :: [String] -> BSTnode -> BSTnode
makeTree [] tree = tree
makeTree (x:xs) tree = makeTree xs (insertBST tree x)

-- searches the tree for element, returns a bool if word is in the tree or not
-- reverses the input because all words in the tree are reversed
searchBST :: BSTnode -> String -> Bool
searchBST MTnode str = False
searchBST (Node left val right) str
   | val == wrd = True
   | val < wrd = searchBST right str
   | otherwise = searchBST left str
   where wrd = reverse str

-- adds words to queue
addQueue :: a -> [a] -> [a]
addQueue word queue = queue ++ [word]

-- evaluates to all but the head of the queue
updateQueue :: [a] -> [a]
updateQueue queue = tail queue

-- evaluates to the head of the queue
peekFromQueue :: [a] -> a
peekFromQueue queue = head queue

-- generates all possible strings, and returns a list of strings that are in the dictonary
generateStr :: BSTnode -> String -> [String]
generateStr bst str = 
    filter (searchBST bst) [take i str ++ [replace] ++ drop (i+1) str | i <- [0..(length str)-1], replace <- ['a'..'z']]

-- updates the word ladders and puts them at the end of the queue, evaluates to the updated queue
updateLadders :: BSTnode -> [String] -> [[String]] -> [[String]]
updateLadders usedTree [] queue = updateQueue queue
updateLadders usedTree (x:xs) queue
    | not (searchBST usedTree x) = 
        updateLadders (insertBST usedTree x) xs (addQueue ((peekFromQueue queue) ++ [x]) (queue))
    | otherwise = updateLadders (insertBST usedTree x) xs queue

-- creates all the possible word ladders, and will either evaluate to the
-- shortest word ladder, or, it will evaluate to an empty list, meaning
-- there is not a possible word ladder
generateAllLadders :: BSTnode -> BSTnode -> String ->[[String]] -> [String]
generateAllLadders _ _ _ [] = []
generateAllLadders dictTree usedTree wordEnd queue
  | checkLastWord wordEnd queue = peekFromQueue queue
  | otherwise = 
    generateAllLadders dictTree (insertBSTList usedTree (validWords)) wordEnd updatedQueue
  where
    validWords = generateStr dictTree (last (peekFromQueue queue))
    updatedLadders = 
        updateLadders (insertBST usedTree (last (peekFromQueue queue))) validWords updatedQueue
    updatedQueue = 
        updateLadders (insertBST usedTree (last (peekFromQueue queue))) validWords queue

-- this is used to check and see if the last word on the ladder
-- is the goal word
checkLastWord :: String -> [[String]] -> Bool
checkLastWord wordEnd queue 
  | last (peekFromQueue queue) == wordEnd = True
  | otherwise = False

-- is used to only read in words of the same length as the startWord
-- into the tree for the dictonary
filterWordLen :: [String] -> String -> [String]
filterWordLen dictWords startWord =
  filter (\x -> length x == length startWord) dictWords


-- changes inputed string to lower case, used for the start
-- and end words, as the dictonary is lower case
lowerCase :: String -> String
lowerCase wrd = map toLower wrd


-- this is the main function, which reads in the command line arguments,
-- sets both of the input words to lowercase, then checks if the startWord
-- and the goalWord are the same length, if they arent, the program prints out
-- that the two words are not, but if they are the main creates the dictionary tree
-- the used word tree, the queue, and then generates all ladders, either returning
-- the shortest word ladder or stating that none exists. 
main :: IO()
main = do
    [dictionary, start, end] <- getArgs
    dict <- readFile dictionary
    let startWord = lowerCase start
    let goalWord = lowerCase end
    if length startWord /= length goalWord then
      putStrLn $ startWord ++ " and " ++ goalWord 
      ++ " are not same length"
    else do
      let dictTree = makeTree (filterWordLen (words dict) startWord) MTnode
      if not (searchBST dictTree startWord) then 
        putStrLn $ startWord
        ++ " is not a word in the dictionary"
      else if not (searchBST dictTree goalWord) then 
        putStrLn $ goalWord
        ++ " is not a word in the dictionary"
      else do
        let usedTree = makeTree [startWord] MTnode
        let queue = addQueue [startWord] []
        let ladder = generateAllLadders dictTree usedTree goalWord queue
        if ladder == [] then
          putStrLn $ "No word ladder exists between " ++ startWord
          ++ " and " ++ goalWord
        else 
          --putStrLn $ show (newLadder)
          putStrLn $ "The shortest word ladder from " ++ startWord 
          ++ " to " ++ goalWord ++ " is " ++ unwords ladder