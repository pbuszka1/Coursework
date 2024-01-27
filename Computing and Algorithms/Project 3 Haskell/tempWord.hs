import System.Environment
import System.IO

addQueue :: a -> [a] -> [a]
addQueue word queue = word:queue -- was word:queue queue ++ [word]

remFromQueue :: [a] -> a
remFromQueue queue = last queue

resultQueue :: [a] -> [a]
resultQueue queue = tail queue

emptyTree = MTnode

data BSTnode = MTnode | Node BSTnode String BSTnode deriving (Show)

-- make this code below work correctly
insert_BST :: BSTnode -> String -> BSTnode
insert_BST MTnode x = Node MTnode (reverse x) MTnode
insert_BST (Node t1 v t2) x
  | rev < v = Node (insert_BST t1 rev) v t2
  | rev == v = Node t1 v t2
  | otherwise = Node t1 v (insert_BST t2 rev)
    where rev = reverse x

makeTree :: [String] -> BSTnode -> BSTnode
makeTree [] bstTree = bstTree
makeTree (x:xs) bstTree = makeTree xs (insert_BST bstTree x) 

search_BST :: BSTnode -> String -> Bool
search_BST MTnode str = False
search_BST (Node left val right) wrd
    | val == wrd = True
    | val < wrd = search_BST right wrd
    | otherwise = search_BST left wrd

generateStr :: String -> [String]
generateStr str = [take i str ++ [rep] ++ drop (i+1) str | i <- [0..(length str)-1], rep <- ['a'..'z']]

-- this is supposed to make a list of strings that are in the dictonary and returns the list
listOfPossWords :: BSTnode -> [String] -> [String] -> [[String]]-> [[String]]
listOfPossWords _ [] _ queue = queue
listOfPossWords node (x:xs) ladder queue -- queue is queue of ladder, need the ladder which is used to to make the queue
    | search_BST node x = listOfPossWords node xs ladder (addQueue (x:ladder) queue)
    | otherwise = listOfPossWords node (xs) ladder queue

-- we want to use listOfPossWords, and our current ladder, and generateStr, 
makingLadders :: [String] -> [String]
makingLadders x:xs = makingLadders xs (generateStr x)

findLadder :: [[String]] -> String -> BSTnode -> [[String]]
findLadder [] _ _ = []
findLadder queue goalWord bstTree
    | head (remFromQueue queue) == goalWord = [remFromQueue queue]
    | search_BST bstTree (head (remFromQueue queue)) = addQueue (remFromQueue queue) queue
    | otherwise = findLadder (resultQueue queue) goalWord bstTree

main :: IO ()
main = do
    [inFile, startWord, endWord] <- getArgs
    input <- readFile inFile
    let theQueue = addQueue [startWord] []
    let bstTree = makeTree (words input) bstTree-- need a recursive function to insert each word from the input dict
    let ladder = findLadder theQueue endWord bstTree
    if ladder == [] then
        putStrLn "No word ladder exists"
        else 
            putStrLn $ unwords (reverse $ head ladder) --ladder is list of ladders