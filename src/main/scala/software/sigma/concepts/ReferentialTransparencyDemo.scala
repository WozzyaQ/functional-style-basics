package software.sigma.concepts

import scala.math.sqrt

object ReferentialTransparencyDemo extends App{
  // expression is said to be referential transparent
  // if it can be replaced by its value and not change the programs behaviour

  case class Complex(re: Double, im: Double) {
    // totally referential transparent because
    // the result of function can be substituted by its resulting value
    def referentialTransparentDistance(): Double = sqrt(re + im)

    // not referential transparent
    // additionally to calculating the distance
    // we also writing in STDOUT => if we substitute the function call with only its value
    // we won't get message in STDOUT
    def nonReferentialTransparentDistance(): Double = {
      val distance = referentialTransparentDistance()
      println(s"The distance is $distance")

      distance
    }
  }

  val complex: Complex = Complex(1, 2)
  val theDistance: Double = complex.referentialTransparentDistance()
  val theDistanceWithSideEffect: Double = complex.nonReferentialTransparentDistance()
}
