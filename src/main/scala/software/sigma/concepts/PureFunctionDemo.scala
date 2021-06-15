package software.sigma.concepts


import java.io.{File, PrintWriter}
import scala.math.{Pi, pow}

object PureFunctionDemo extends App{

  // deterministic (knowing R we know result in advance)
  def calculateSphereVolume(radius: Double): Double = 4/3 * Pi * pow(radius, 3)
  class Sphere(val radius: Double, var stateId: Int)

  val sphere: Sphere = new Sphere(12, 0)
  val sphereVolume = calculateSphereVolume(sphere.radius)

  // println is impure :)
  println(sphereVolume)

  def calculateSphereAreaWithSideEffects(sphere: Sphere): Double = {
    val area = 4/3 * Pi * sphere.radius * sphere.radius
    // side effect - writing to STDOUT
    println(s"We've got sphere which area is $area")

    // mutate state of the object
    val stateId = 333
    sphere.stateId = if(area > 1000) stateId else stateId + 23

    // io operation, also side effect
    new PrintWriter("temp.txt") {println(area); close()}

    area
  }

  val sphereArea = calculateSphereAreaWithSideEffects(sphere)
}
