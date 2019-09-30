# Triangle traverser would be a more proper name for this project

This program reads a triangle from file and finds the path with the biggest sum from top to bottom.

## How to run it
You can run the program with `./gradlew run` which will read the `source.txt` file. 
If you want to specify your own file or path for file, you should use `./gradlew run --args FILE_NAME_OR_PATH

## Data structure
A triangle is a data structure which consists of rows, where every n-th row consists of n elements.
So the first row consists of 1 element, 2nd of 2 and so on.

for example:

       1
      1 2
     1 2 3
    1 2 3 4
      ...

Every node is connected to max 2 children. To visualize the paths from the earlier triangle it would look like this:
    
          1
         / \
        1   2
       /\   /\
      1   2   3
     / \ / \ / \
    1   2   3   4
         ...
I achieve this by using nodes that can have two parents and two children.
