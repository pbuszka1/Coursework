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

data BstTree a = Empty | Node a (BstTree a) (BstTree a)

-- Build a binary search tree from a list of elements
makeTree :: Ord a => [a] -> BstTree a
makeTree [] = Empty
makeTree [x] = Node x Empty Empty
makeTree (x:xs) = foldl (insertNode) (Node x Empty Empty) xs

insertNode :: Ord a => BstTree a -> a -> BstTree a
insertNode Empty y = Node y Empty Empty
insertNode (Node v left right) x
    | x == v = Node v left right
    | x < v = Node v (insertNode left x) right
    | otherwise = Node v left (insertNode right x)

-- Convert a string to lowercase
toLowerString :: String -> String
toLowerString = map toLower

-- Check if two words are adjacent
isAdjacent :: String -> String -> Bool
isAdjacent w1 w2 = isAdjacent' w1 w2 0
  where
    isAdjacent' :: String -> String -> Int -> Bool
    isAdjacent' [] [] 1 = True
    isAdjacent' (c1:cs1) (c2:cs2) diffs
      | c1 /= c2 = isAdjacent' cs1 cs2 (diffs+1)
      | otherwise = isAdjacent' cs1 cs2 diffs
    isAdjacent' _ _ _ = False

-- Convert a binary search tree to a list
treeToList :: BstTree a -> [a]
treeToList Empty = []
treeToList (Node x left right) = treeToList left ++ [x] ++ treeToList right

-- Find the shortest path between two words using breathFristSearch
findPath :: BstTree String -> String -> String -> Maybe [String]
findPath t start end = breathFristSearch [[start]] []
  where
    breathFristSearch [] _ = Nothing
    breathFristSearch (p:ps) visited
      | head p == end = Just (reverse p)
      | head p `elem` visited = breathFristSearch ps visited
      | otherwise =
        let adj = [x | x <- treeToList t, x /= head p, isAdjacent x (head p), x `notElem` visited]
            visited' = head p : visited
            ps' = ps ++ map (:p) adj
        in breathFristSearch ps' visited'

-- Main function
main :: IO ()
main = do
  [inFile, startWord, endWord] <- getArgs
  contents <- readFile inFile
  let words = [wrd | wrd <- map toLowerString (lines contents), length wrd == length startWord]
      bstTree = makeTree words
  if (length startWord) /= (length endWord) then
    putStrLn ("There is no ladder")
  else
    case findPath bstTree startWord endWord of
      Just path -> putStrLn ("The shortest ladder between " ++ startWord ++ " and " ++ endWord ++ " is " ++ unwords path)
      Nothing -> putStrLn ("There is no ladder between " ++ startWord ++ " and " ++ endWord)
