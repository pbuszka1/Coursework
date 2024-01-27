import Data.Char (toLower)
import Data.Maybe (fromJust)
import System.Environment
import System.IO

-- A queue data structure using a list inside of a list
type Queue a = [[a]]

-- Create an empty queue
empty :: Queue a
empty = []

-- Check if the queue is empty
isEmpty :: Queue a -> Bool
isEmpty [] = True
isEmpty _ = False

-- addQueue an element to the queue
addQueue :: Queue a -> a -> Queue a
addQueue [] x = [[x]]
addQueue (xs:ys) x = (x:xs):ys

-- remQueue an element from the queue
remQueue :: Queue a -> Maybe (a, Queue a)
remQueue [] = Nothing
remQueue (xs:ys) =
  case xs of
    [] -> remQueue ys
    (z:zs) -> Just (z, zs:ys)
    
emptyTree = MTnode

data BSTnode = MTnode | Node BSTnode String BSTnode deriving (Show)

-- Build a binary search tree from a list of elements
insert_BST :: BSTnode -> String -> BSTnode
insert_BST MTnode x = Node MTnode (reverse x) MTnode
insert_BST (Node t1 v t2) x
  | rev < v = Node (insert_BST t1 x) v t2
  | rev == v = Node t1 v t2
  | otherwise = Node t1 v (insert_BST t2 x)
    where rev = reverse x

makeTree :: [String] -> BSTnode -> BSTnode
makeTree [] bstTree = bstTree
makeTree (x:xs) bstTree = makeTree xs (insert_BST bstTree x) 

-- Convert a string to lowercase
toLowerString :: String -> String
toLowerString = map toLower

-- Check if two words are adjacent
isAdjacent :: String -> String -> Bool
isAdjacent w1 w2 =
  let len = length w1
      diff = filter (\(c1, c2) -> c1 /= c2) $ zip w1 w2
  in len == length w2 && length diff == 1

-- Convert a binary search tree to a list
treeToList :: BSTnode -> [String]
treeToList MTnode = []
treeToList (Node left x right) = treeToList left ++ [x] ++ treeToList right

-- Find the shortest path between two words using breathFirstSearch
findPath :: BSTnode -> String -> String -> Maybe [String]
findPath t start end = breathFirstSearch [(start, [start])] []
  where
    breathFirstSearch [] _ = Nothing
    breathFirstSearch ((p,path):ps) visited
      | p == end = Just (reverse path)
      | p `elem` visited = breathFirstSearch ps visited
      | otherwise =
        let adj = [x | x <- treeToList t, x /= p, isAdjacent x p, x `notElem` visited]
            visited' = p : visited
            ps' = ps ++ [(x, x:path) | x <- adj]
        in breathFirstSearch ps' visited'

-- Main function
main :: IO ()
main = do
  [inFile, startWord, endWord] <- getArgs
  contents <- readFile inFile
  let words = filter (\w -> length w == length startWord) (map toLowerString (lines contents))
      tree = makeTree words MTnode
  case findPath tree startWord endWord of
    Just path -> putStrLn ("The shortest path between " ++ startWord ++ " and " ++ endWord ++ " is " ++ unwords path)
    Nothing -> putStrLn ("There is no path between " ++ startWord ++ " and " ++ endWord)
