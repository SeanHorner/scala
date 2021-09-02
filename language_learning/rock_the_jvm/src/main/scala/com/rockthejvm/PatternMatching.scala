package com.rockthejvm

object PatternMatching extends App {

  // switch expression
  val anInteger = 55
  val order = anInteger match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => anInteger + "th"
  }

  // Pattern Matching is an EXPRESSION which we can equate to a variable/value.

  case class Person(name: String, age: Int)
  val bob = Person("Bob", 43)  //  Person.apply("Bob", 43)

  val personGreeting = bob match {
    case Person(n, a) => s"Hi my name is ${n} and I am $a years old."
    case _ => "Something else"
  }
  /**
   *    > println(personGreeting)
   *    #
   *    # "Hi my name is Bob and I am 43 years old."
   */

  // deconstructing tuples
  val aTuple = ("Bon Jovi", "Rock")
  val bandDescription = aTuple match {
    case (band, genre) => s"$band belongs to the genre $genre ."
    case _ => "I don't know what you're talking about."
  }

  // decomposing lists
  val aList = List(1,2,3)
  val listDescription = aList match {
    case List(_, 2, _) => "List containing 2 on its second position" // List must be 3 items large, the first and third
                                                                     // numbers don't matter, just if List[1] == 2
    case _ => "unknown list"
  }
}
