package com.rockthejvm

object Basics extends App {
  // defining a value
  val meaningOfLife: Int = 42    // same as C++: const int meaningOfLife = 42;  It's immutable.

  val aBoolean = false          // the compiler can auto-guess type (most of the time), so no need to be explicit.
  // Many types exist in Scala: Int, Boolean, Char, Double, Float, String

  val aString = "I love Scala"
  val aComposedString = "I " + "love" + " Scala"
  val anInterpolatedString = s"The meaning of life is $meaningOfLife"

  // expressions -> structures that can be reduced to a value
  val anExpression = 2 + 3

  // if-expression
  val ifExpression = if (meaningOfLife > 43) 56 else 999   // In other languages: meaningOfLife > 43 ? 56 : 999
  val chainedIfExpression =
    if (meaningOfLife > 43) 56
    else if (meaningOfLife < 0) -2
    else if (meaningOfLife > 999) 78
    else 0

  //code blocks
  val aCodeBlock = {
    //definitions
    val aLocalValue = 67

    // The value of the code block is whatever the last line equates to.
    aLocalValue + 3
  }

  // define a function
  def myFunction(x: Int, y: String): String = {
    y + " " + x
  }

  // recursive functions
  def factorial(n: Int) : Int =
    if(n <= 1) 1
    else n * factorial(n - 1)

  /*
    [Step 1] factorial(5) = 5 * factorial(4)   --> [Step 9] 5 * 24  = 120   --> [Step 10] returns 120
    [Step 2] factorial(4) = 4 * factorial(3)   --> [Step 8] 4 * 6   = 24
    [Step 3] factorial(3) = 3 * factorial(2)   --> [Step 7] 3 * 2   = 6
    [Step 4] factorial(2) = 2 * factorial(1)   --> [Step 6] 2 * 1   = 2
    [Step 5] factorial(1) = 1

    The program steps down over and over again until it hits the base case (factorial(1) = 1) and then moves back
    up through the stack applying the lower-step's value to the next calculation.
   */

  // In Scala we don't use loops or iterations, we use RECURSION!

  // the Unit type = no meaningful value == "void" in other languages
  // type of SIDE EFFECTS
  println("I love Scala") // Same as: System.out.println, printf, print, console.log

  def myUnitReturningFunction(): Unit = {
    println("I don't love returning Unit")
  }

  // unit type is considered the same as an empty Set
  val theUnit = ()
}
