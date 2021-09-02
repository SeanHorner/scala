package com.rockthejvm

import scala.{+:, :+}

object FunctionalProgramming extends App {

  // Scala is an OO language
  class Person(name: String) {
    def apply(age: Int) = println(s"I have aged $age years")
  }

  val bob = new Person("Bob")
  bob.apply(43)
  bob(43)  // INVOKING bob AS A FUNCTION === bob.apply(43)

  /*
    Scala runs on the JVM
    In Functional Programming, we want functions to be first-class citizens, that is:
      - compose functions
      - pass functions as arguments
      - return functions as results

    Conclusion: FunctionX
   */

  val simpleIncrementer = new Function1[Int, Int] {
    override def apply(arg: Int): Int = arg + 1
  }

  simpleIncrementer.apply(23)  //  24
  simpleIncrementer(23)        // simpleIncrementer.apply(23)
  // We've successfully defined a function.

  // ALL SCALA FUNCTIONS ARE INSTANCES OF THESE FUNCTION_X TYPES

  val stringConcatenator = new Function2[String, String, String] {
    override def apply(arg1: String, arg2: String): String = arg1 + arg2
  }

  stringConcatenator("I love ", "Scala")   //   "I love Scala"

  // syntax sugars
  val doubler = (x: Int) => 2 * x
  doubler(4)   //   8

  /*
    This short hand works great to shrink code base, as it's equivalent to the much longer:

    val doubler =
      new Function1[Int, Int] {
        override def apply(x: Int) = 2 * x
      }

        ...is larger and harder to read than...

    val doubler: Function[Int, Int] = (x: Int) => 2 * x

        ...which is larger and harder to read than the inferred notation...

    val doubler = (x: Int) => 2 * x

    Here the expression is implied to take an Int and return an Int, so it is type-inferred.
   */

  // higher-order functions: take functions as args and/or return functions as results
  val aMappedList: List[Int] = List(1,2,3).map(x => x + 1)  // Higher Order Function
  val aFlatMappedList = List(1,2,3).flatMap(x => List(x, 2 * x))
  val aFilteredList = List(1,2,3,4,5).filter(_ <= 3)  // Equivalent to .filter(x => x <= 3), easier to read
                                                      // treat the underscore in a lambda as "an x for every x"
                                                      // or the place where each list item will be slotted into
                                                      // the filter/map/flatMap expression.

  // all pairs between the numbers 1,2,3 and the letters 'a', 'b', 'c'
  val allPairs = List(1,2,3).flatMap(number => List('a','b','c').map(letter => s"$number-$letter"))
  //    println(allPairs)
  //    # List(1-a, 1-b, 1-c, 2-a,...)

  val alternativePairs = for {
    number <- List(1,2,3)
    letter <- List('a','b','c')
  } yield s"$number-$letter"

  // The for-expression written above is taken by the compiler and reformatted into the map/flatMap chain above.
  // These two expressions are identical to the compiler, with the internal syntax being the allPairs expression.

  /**
   * Collections
   */

  // lists
  val aList = List(1,2,3,4,5)
  val firstElement = aList.head
  val listRemainder = aList.tail

  val aPrependedList = 0 :: aList // List(0,1,2,3,4,5)
  val anExtendedList = 0 +: alist :+ 6  //  List(0,1,2,3,4,5,6)

  // sequences
  val aSequence: Seq[Int] = Seq(1,2,3)
  val accessedElement = aSequence(1)  //  the element at index 1 = 2

  // vectors: fast Seq implementation
  val aVector = Vector(1,2,3,4,5)

  // sets = no duplicates
  val aSet = Set(1,2,3,4,1,2,3)  //  Set(1,2,3,4)
  val setHas5 = aSet.contains(5)  //  false
  val anAddedSet = aSet + 5  //  Set(1,2,3,4,5)
  val aRemovedSet = aSet - 3  //  Set(1,2,4)

  // ranges
  val aRange = 1 to 1000
  val twoByTwo = aRange.map(_ * 2).toList  //  List(2,4,6,8,...,2000)

  // tuples = groups of values under the same value
  val aTuple = ("Bon Jovi", "Rock", 1982)

  // maps
  val aPhoneBook: Map[String, Int] = Map(
    ("Daniel", 49812938),
    "Jane" -> 327285       // equivalent to ("Jane", 327385)
  )

}
