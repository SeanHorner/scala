package com.rockthejvm

object ObjectOrientation extends App {

  // java equivalent at this point --> public static void main(String[] args)

  // class and instance
  class Animal {
    // can define class fields
    val age: Int = 0
    // can define class methods
    def eat() = println("I'm eating")
  }
  val anAnimal = new Animal

  // inheritance
  class Dog(val name: String, breed: String) extends Animal // constructor definition
  val aDog = new Dog("Lassie", "Retriever")

  // constructor arguemnts are NOT fields, to make them accessible they need to be declared with a val or var.
  // Thusly aDog.name IS accessible, while aDog.breed IS NOT accessible.

  // subtype polymorphism
  val aDeclaredAnimal: Animal = new Dog("Hachi", "Shibu-Inu")
  aDeclaredAnimal.eat()     // the most derived method will be called at runtime, here the Dog class implementation
                            // of eat(), if the Dog class overrides eat().

  abstract class WalkingAnimal {
    val hasLegs = true  // by default all members and methods are public,
                        // can be restricted by specifying protected or private
    def walk(): Unit
  }

  // "interface" = ultimate abstract type
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  trait Philosopher {
    def ?!(thought: String): Unit  // Scala is permissive in method naming, this name wouldn't fly in many languages
  }

  class Crocodile extends Animal with Carnivore with Philosopher {
    override def eat(animal: Animal): Unit = println(s"I am eating some $animal!")

    override def ?!(thought: String): Unit = println(s"I was thinking: $thought")
  }

  val aCroc = new Crocodile
  aCroc.eat(aDog)
  aCroc eat aDog    // infix notation --> object method argument     only available for methods with ONE argument

  aCroc ?! "What if we could fly?"

  // operators in Scala are actually methods
  val basicMath = 1 + 2
  val anotherBasicMath = 1.+(2) // equivalent

  // anonymous classes
  val dinosaur = new Carnivore {
    override def eat(animal: Animal): Unit = println("I am a dinosaur so I can eat pretty much anything")
  }

  /*
    Functionally equivalent to:
    class Carnivore_Anonymous_35728 extends Carnivore {
      override def eat(animal: Animal): Unit = println("I am a dinosaur so I can eat pretty much anything")
    }

    val dinosaur = new Carnivore_Anonymous_35728
   */

  // Singleton Object
  object MySingleton { // the only instance of the MySingleton type
    val mySpectialValue = 53278
    def mySpecialMethod(): Int = 53278
    def apply(x: Int): Int = x + 1
  }

  MySingleton.mySpecialMethod()
  MySingleton.apply(65)
  MySingleton(65)     // equivalent to MySingletion.apply(65)

  object Animal { // companions - companion object
    // companions can access each other's private fields/methods
    // singleton Animal and instances of Animal are different things
    val canLiveIndefinitely = false
  }

  val animalsCanLiveForever = Animal.canLiveIndefinitely  // "static" fields/methods

  /*
  case classes = lightweight data structures with some boilerplate code
  - sensible equals and hash code
  - serialization
  - companion with apply
  - pattern matching
   */
  case class Person(name: String, age: Int)

  val bob = Person("Bob", 54)  // same as: Person.apply("Bob", 54)

  // exceptions
  try {
    // code that can throw
    val x: String = null
    x.length
  } catch {   // Comparable to Java: catch(Exception e) {...}
    case e: Exception => "some faulty error message"
  } finally {
    // execute some code no matter what
    // like closing connections or cleaning up objects.
  }

  // generics
  abstract class MyList[T] {  // This is a generic class that can take any value definition
    def head: T
    def tail: MyList[T]
  }

  // using a generic with a concrete type : List[T] + Int
  val aList: List[Int] = List(1,2,3) // List.apply(1,2,3)
  val first = aList.head  // here returns an Int, since [T] = Int
  val rest = aList.tail

  // Templates can use type inference, for example, this List[T] + String
  val aStringList = List("hello", "Scala")
  val firstString = aStringList.head  // here returns a String, since [T] = String

  // Point #1: in Scala we usually operate with IMMUTABLE values/objects
  // Any modification to an object must return ANOTHER object
  /*
    Benefits:
      1) works miracles is multithreaded/distributed environments
      2) helps making sense of the code ("reasoning about")
   */
  val reversedList = aList.reverse // Returns a NEW list to a new variable

  // Point #2: Scala is closest to the Object-Oriented ideal

}
