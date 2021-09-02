package com.rockthejvm

object ContextualAbstractions {

  /*
    1 - Context Parameters/Arguments
   */
  val aList = List(2,1,3,4)
  val anOrderedList = aList.sorted  // contextual argument: (descendingOrdering)

  // Ordering
  given descendingOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _)  //  (a,b) => a > b

  // analogous to an implicit val

  trait Combinator[A] {    // mathematically known as a monoid
    def combine(x: A, y: A) : A
  }

  def combineAll[A](list: List[A])(using combinator: Combinator[A]): A =
    list.reduce((a,b) => combinator.combine(a,b))

  given intCombinator: Combinator[Int] = new Combinator[Int] {
    override def combine(x: Int, y: Int) = x + y
  }

  val theSum = combineAll(aList)  // (intCombinator) is guessed because it is nearby and applicable.

  /*
    Given places:
      - local scope
      - imported scope (e.g. import myPackage.given)
      - the companions of all the types involved in the method call
        - In the example above:
          - Companion of List
          - the companion of Int
   */

  def combineAll_v2[A](list: List[A])(using Combinator[A]): A = ???
  def combineAll_v3[A : Combinator](list: List[A]): A = ???
  // [A : Combinator] is a type restriction indicating that this template can only be used with types that have an
  // available Combinator in scope.

  /*
    Where context args are useful:
    - Type classes
    - dependency injection
    - context-dependent functionality
    - type-level programming
   */


  /*
    2 - Extension Methods
   */

  case class Person(name: String) {
    def greet(): String = s"Hi, my name is $name, and I love Scala!"
  }

  extension (string:String)
    def greet(): String = new Person(string).greet()

  val danielsGreeting = "Daniel".greet()  //  "type enrichment"
  // Above is a kind of functional loop-de-loop where the string library (in the scope of this project) has a new method
  // added to it called .greet() which takes the string the method is called from, creates a Person object named after
  // the string, and calling the Person.greet() method on that constructed person.

  // POWER
  extension [A] (list: List[A])
    def combineAllValues(using combinator: Combinator[A]): A =
      list.reduce(combinator.combine)

  val theSum_v2 = aList.combineAllValues

  def main(args: Array[String]): Unit = {
    println(anOrderedList)
    println(theSum)
    println(theSum_v2)
  }

}
