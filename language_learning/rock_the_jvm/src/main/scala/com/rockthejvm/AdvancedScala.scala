package com.rockthejvm

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}

object AdvancedScala extends App {

  // lazy evaluation - an expression is not evaluated until it is first used.
  lazy val aLazyValue = 2
  lazy val lazyValueWithSideEffect = {
    println("I am so very lazy!")
    43
  }
  // If everything above this point is run, nothin prints to console, because the lazy keyword makes it
  // so that lazyValueWithSideEffect isn't processed (which we would notice by the string being printed to the console)
  // It isn't processed until the value is called again, but it can be in any expression, for example:

  val eagerValue = lazyValueWithSideEffect + 1

  // When lazyValueWithSideEffect is encountered by the compiler here, it jumps back and evaluates the expression
  // now, which triggers the writing to the console.

  // useful in infinite collections

  // "pseudo-collections": Option, Try
  def methodWhichCanReturnNull(): String = "hello, Scala"
  if (methodWhichCanReturnNull() == null) {
    // defensive code against null
  }
  // The above is an example of how to guard against null values as implemented in most languages
  // But in Scala we have the option to use the Option class, e.g.:
  val anOption = Option(methodWhichCanReturnNull())  //  Some("hello, Scala")
  // option = "collection" which contains at most one element: Some(value) or None

  val stringProcessing = anOption match {
    case Some(string) => s"I have obtained a valid string: $string"
    case None => "I have obtained nothing."
  }
  // The paradigm above allows for error-creating nulls to be filtered out of code operation by writing a simple
  // switch statement consisting of two cases: if something was found -- Some(value) -- or if nothing was found -- None.

  def methodWhichCanThrowException(): String = throw new RuntimeException

  // This is how try blocks are used in most other languages, and Scala has the paradigm for this too.
  //  try {
  //    methodWhichCanThrowException()
  //  } catch {
  //    case e: Exception => s"defend against evil exception $e.message"
  //  }
  // But with multiple layers of code which need to be fortified against exceptions, this paradigm can become intensely
  // cumbersome. So instead Scala offers a pseudo-collection called Try, which is used like so:
  val aTry = Try(methodWhichCanThrowException())  //  This Try will either contain an expected value or an exception if
                                                  //  the code threw an exception.
  val anotherStringProcessing = aTry match {
    case Success(validValue) => s"I have obtained a valid String: $validValue"
    case Failure(ex) => s"I have obtained an exception: $ex"
  }

  /**
   * How to Evaluate Something on Another Thread
   * (aka asynchronous programming)
   */
  val aFuture = Future {    // Future.apply
    println("Loading...")
    Thread.sleep(1000)
    println("I have computed a value.")
    67
  }

  // future is a "collection" which contains a value when it's evaluated
  // future is composable with map, flatMap, and filter.

  // Monads - the "pseudo-collections" of Option, Try, and Future are all examples of Monads

  /**
   * Implicits basics
   */
  // Use case #1: implicit arguments
  def aMethodWithImplicitArgs(implicit arg: Int) = arg + 1
  implicit val myImlicitInt = 46
  println(aMethodWithImplicitArgs)  // Same as the call: aMethodWithImplicitArgs(myImplicitInt)
                                    // the implicit keyword tells the compiler to make a guess on which value, e.g. a
                                    // nearby integer, to use in the calling of the method.

  // #2: implicit conversions
  implicit class MyRichInteger(n: Int) {
    def isEven() = (n % 2 == 0)   // Test the truth value of n%2 == 0, the test for evenness.
  }

  println(23.isEven())  // The compiler will look for any implicit classes that have a .isEven() method and if it
                        // finds any, which it does, then it trys to build a new, compatible object, i.e.:
                        // new MyRichInteger(23).isEven().
  // Use implicits very carefully!!!

}
