# Functional programming
Functional programming is a declarative programming paradigm. That means we are using functions and theirs combinations applied to values to get the result (*what to do?*) rather than using sequence of steps (imperative statements, *how to do?*).
## First class and higher-order functions
If a function can be stored in a variable, passed as an argument to another function, or could be returned from a function
the function is called `first-class function`. 

`Higher-order function` is a function that is capable of taking one or more
functions as arguments or returning function as a result

The main benefit of such function is that we gain powerful weapon to go with through our code. With first-class functions
and higher-order function we are able to do complex evaluations without imperative writing steps that will lead us to achieving the goals.
## Pure functions
A pure function is an analogue of mathematical function.
It is a function that: 
1. The function causes no side effects
2. The function return same values for same inputs (the function don't peek in other object's states)

Advantages of using pure functions:
* Ease of testing
* Easy to understand (no external dependencies to a function)
* Referential transparency from the box
* Result could be cached (why compute again if we know the results?)

Disadvantages: 
* All I/O operations (writing to file, reading from database) are considered as side effect.  
 There is no way we can build an app without such operations.
* Due to impurity of programming in general it is hard to combine both pure and impure functions  

## Recursion
Because we are not writing imperative commands, we need to use different way of iterating. That were recursion come in handy.
The recursion allow us analogues to imperative for-loops in elegant manner.

There are some disadvantages of using recursion:
* Using usual recursion we are limited in computation depth. Because of storing local variables from each of recursion call of a function
we are going to overflow our stack :)
* Recursion is slower than usual for/while-loops
* And of course the recursion is difficult to understand in some cases (due to imperative-thinking)

Some good news:
* Compilers can optimize recursion (for example, `tail recursion` when we are doing calculation first and then executing recursive call), and we won't get stack-overflow errors
* Elegant solutions for tree-based structures (because of tree nature)

## Eager vs Lazy evaluations
Eager evaluations - expressions are evaluated immediately.
Lazy or delayed evaluations - computed on demand.

* Using lazy evaluations we are getting overhead with keeping some kind of wrappers for delayed values.
* Lazy evaluations could lead us to loss of control (we don't know when exactly some evaluations happened, so we can create a hard-to-catch bug)
* Eager evaluations can be more efficient and controllable.

* Using lazy evaluations we could create an infinite collections (but actually we are not creating this collection, only the view)
* Sometimes lazy evaluation could help us to evade unnecessary computation (if the value is not used, why we need to evaluate it) 

## Referential transparency 
Defines a fact, that an expression may be substituted by its return value and affect no program flow.
