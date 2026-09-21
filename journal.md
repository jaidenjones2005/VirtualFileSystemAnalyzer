# Phase 1: Composite Hierarchy and Recursive File Counter

**Journal Prompt:** Identify the Base Case and General Case in your `countFilesRecursive` method. Applying the Smaller-Caller rule, what guarantees that each recursive call operates on a strictly smaller sub-problem and will eventually terminate?

**Response:**

The base case is when the method reaches a FileItem. It returns 1 because it has found a single file and does not need to search any further. The general case is when the method reaches a Folder. It loops through the folder's contents and recursively counts the files inside each item.

The Smaller-Caller rule applies because each recursive call moves down into a child item instead of searching the same folder again. Since the file system has a finite number of items and no circular folder references, the method will eventually reach individual files or empty folders and return the total count.

This phase helped me understand how recursion can search through multiple levels of folders without needing several nested loops.
## Phase 2: Recursive Storage Calculation and Extremum Search

**Journal Prompt:** Trace how `findLargestFileRecursive` handles a folder containing only empty subfolders. Why is returning `null` an effective way to represent "no file found," and how does your general case handle comparisons without throwing a `NullPointerException`?

**Response:**

When `findLargestFileRecursive` searches a folder containing only empty subfolders, each recursive call returns `null` because there are no files inside them. Since no valid file is found, the `largest` variable stays `null` and gets returned to the previous call.

Returning `null` is useful because it tells the program that there is no file to compare in that branch. The method checks whether `childMax` is `null` before comparing file sizes. This prevents a `NullPointerException` because the program only calls `getSizeInKB()` when a valid file exists.

In this phase, I learned how recursion can calculate the total storage size and find the largest file across multiple folders. I also learned why checking for `null` is important when working with empty folders.
## Phase 3: Removing Recursion (Iterative Traversal)

**Journal Prompt:** Compare your recursive implementation from Phase 1 with the iterative stack implementation from Phase 3 using Overhead Time, Overhead Space, and Code Clarity. Which solution do you find more intuitive to write and maintain, and why?

**Response:**

The recursive method was easier for me to understand because it automatically searches through each folder by calling itself. The iterative method required me to create a Stack and use a while loop to keep track of the items that still needed to be checked.

In terms of overhead time, both methods visit each item in the file system, but recursion has additional method-call overhead. For overhead space, recursion uses the runtime call stack, while iteration uses an explicit Stack to store items that still need processing. The iterative method can also avoid stack overflow problems caused by very deep recursion, although its explicit stack still uses memory.

I find recursion more intuitive because the code is shorter and follows the folder structure naturally. However, I can see why iteration could be useful when working with very deep directory structures.
