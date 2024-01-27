import Data.Char (toLower)
import Data.List (sort)
import Data.Maybe (fromJust)

-- A queue data structure
data Queue a = Queue [a] [a]

-- Create an empty queue
empty :: Queue a
empty = Queue [] []

-- Check if the queue is empty
isEmpty :: Queue a -> Bool
isEmpty (Queue [] []) = True
isEmpty _ = False

-- Enqueue an element to the queue
enqueue :: Queue a -> a -> Queue a
enqueue (Queue xs ys) x = Queue xs (x:ys)

-- Dequeue an element from the queue
dequeue :: Queue a -> Maybe (a, Queue a)
dequeue (Queue [] []) = Nothing
dequeue (Queue xs (y:ys)) = Just (y, Queue xs ys)
dequeue (Queue xs []) = dequeue (Queue [] (reverse xs))

-- A binary search tree data structure
data Tree a = Empty | Node a (Tree a) (Tree a)

-- Build a binary search tree from a list of elements
buildTree :: Ord a => [a] -> Tree a
buildTree [] = Empty
buildTree (x:xs) = Node x (buildTree (filter (<x) xs)) (buildTree (filter (>x) xs))

-- Find the shortest path between two words using BFS (breadth-first search)
findPath :: Tree String -> String -> String -> Maybe [String]
findPath t start end = bfs [(start, [])] []
  where
    bfs [] _ = Nothing
    bfs ((word,path):xs) visited
      | word == end = Just (reverse (word:path))
      | word `elem` visited = bfs xs visited
      | otherwise =
        let adj = [x | x <- treeToList t, x /= word, isAdjacent x word, x `notElem` visited]
            visited' = word : visited
            xs' = xs ++ [(x, word:path) | x <- adj]
        in bfs xs' visited'

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
treeToList :: Tree a -> [a]
treeToList Empty = []
treeToList (Node x left right) = treeToList left ++ [x] ++ treeToList right

-- Main function
main :: IO ()
main = do
  putStrLn "Enter the start word:"
  start <- fmap toLowerString getLine
  putStrLn "Enter the end word:"
  end <- fmap toLowerString getLine
  contents <- readFile "dictionary.txt"
  let words = filter (\w -> length w == length start) (map toLowerString (lines contents))
      tree = buildTree words
  case findPath tree start end of
    Just path -> putStrLn ("The shortest path between " ++ start ++ " and " ++ end ++ " is " ++ unwords path)
    Nothing -> putStrLn ("There is no path between " ++ start ++ " and " ++ end)
