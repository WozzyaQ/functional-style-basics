package software.sigma.concepts

import scala.math.exp
import scala.math.abs
import scala.math.sqrt

object FirstClassFunctionDemo extends App {
  // Store function as value
  val sinh: Function1[Double, Double] = new Function1[Double, Double] {
    override def apply(x: Double): Double = (exp(x) - exp(-x)) / 2
  }
  val cosh: Double => Double = (x: Double) => (exp(x) + exp(-x)) / 2
  val moduloFunction = (x: Double) =>  abs(x - 3) + abs(x + 3)


  // Use function as other's function argument
  val integrateRectangleRule = (func: (Double) => Double, a: Double, b: Double) => (b - a) * func((a + b) / 2)
  println(integrateRectangleRule(sinh, 1.1, 3.5))

  // Return function from other function
  val incrementerConstructor: Int => (Int) => Int = (incRate: Int) => {
    (oldValue: Int) => incRate + oldValue
  }
  val incrementBySix = incrementerConstructor(6)
  val incrementByHundred = incrementerConstructor(100)
  println(incrementBySix(6))
  println(incrementByHundred(250))

  val compositeFunction = (x: Double,
                           func1 : (Double, Double => Double) => Double,
                           func2 : Double => Double) => {
    sqrt(func1(x, func2))
  }

  // sqrt(e^x + x) | x = 23.5
  val result = compositeFunction(23.5, (x, f) => f(x) - x,x => exp(x))
  println(result)
}
