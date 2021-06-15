package software.sigma.concepts

import java.time.LocalDate
import scala.math.{Pi, cos, exp, sqrt}

object HigherOrderFunctionDemo extends App {
  // takes function and returns it's first derivative
  def centeredDerivative(func: Double => Double): (Double, Double) => Double = {
    (x: Double, step: Double) => (func(x + step) - func(x - step)) / 2 * step
  }

  val firstDerivativeCos = centeredDerivative(cos)
  println(firstDerivativeCos(2 * Pi, 0.1))

  def getStringDate(year: Int, month: Int, day: Int): String = LocalDate.of(year, month, day).toString


  case class AttendanceRecord(date: String, place: String, totalAttendees: Int)

  val dates = List(getStringDate(2000, 12, 11),
    getStringDate(2021, 12, 11))

  val places = List("Kiev", "Mariupol", "Odessa")
  val attendeesList = List(120, 350, 323)

  val records: List[AttendanceRecord] =
    for {
      date <- dates
      place <- places
      attendees <- attendeesList
    } yield AttendanceRecord(date, place, attendees)

  // map, groupBy, mapValues are higher-order functions
  val groupedAttendeesAmountByDates = records.groupBy(_.date)
    .view.mapValues(_.map(_.totalAttendees).sum)
    .toMap

  println(groupedAttendeesAmountByDates)

  // function composition
  def compose[A,B,C] (f: B => C, g: A => B): A => C = {
    a => f(g(a))
  }

  def f(arg: Double): Double = sqrt(arg).toInt
  def g(arg: Double): Double = exp(arg).toInt

  println(compose(f,g)(23))
}
