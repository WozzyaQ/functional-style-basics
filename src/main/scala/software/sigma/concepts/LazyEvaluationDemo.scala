package software.sigma.concepts

import scala.annotation.tailrec

object LazyEvaluationDemo extends App {

  def factorial(n : Int): Int = {
    println(s"evaluating $n!")
    @tailrec
    def iter(n: Int, acc: Int): Int = n match {
        case 0 | 1 =>  acc
        case _ => iter(n - 1, acc * n)
      }
    iter(n, 1)
  }
  val sumOfFactorials = factorial(10) // <- evaluates immediately

  def inc(n: Int): Int = n + 1
  val incrementedFactorialValue = inc(factorial(1)) // <- also evaluates immediately

  def multiplyBlocks(block: => Int): Int = {
    block * block
  }
  val factorialMultiplication = multiplyBlocks({factorial(1) + factorial(1)}) // <- evaluates block two times



  // simulating some heavy operations
  def doSideEffectHeavyCalculations(): String = {
    println("Starting doing calculations")
    Thread.sleep(1000)
    println("Finished doing calculations")

    "output_value"
  }

  def eagerMediator(accepted: Boolean, operation: String): Option[String] = {
    println("Eager mediator")
    if (accepted) Some(operation)
    else None
  }

  def lazyMediator(accepted: Boolean, operation: => String): Option[String] = {
    println("Lazy mediator")
    if (accepted) Some(operation)
    else None
  }

  // even if we are not falling in if block
  // eagerMediator still calculates the value
  eagerMediator(accepted = false, doSideEffectHeavyCalculations)

  // here we only compute the value when it's needed
  lazyMediator(accepted = false, doSideEffectHeavyCalculations)

  // evaluates on first use
  lazy val lazyList = List(1,2,3)
  val properLazyList: LazyList[Int] = LazyList(1,2,3)

}
