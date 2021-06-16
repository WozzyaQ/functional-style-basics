package software.sigma.concepts

import scala.annotation.tailrec

object RecursionDemo extends App{

  implicit val maxRepeats: Int = 100

  def measureExecTime[R](proc: => R, name: String)(implicit repeatBound: Int): R = {
    var totalTimeNanos: Long = 0

    for(i <- 0 to repeatBound) {
      val startTime = System.nanoTime()
      proc
      val endTime = System.nanoTime()
      totalTimeNanos += endTime - startTime
    }

    println(s"Elapsed [$name]: ${totalTimeNanos/(1_000_000.0 * maxRepeats)} ms")
    proc
  }

  // naive recursive sum implementation
  // fail with stackoverflow on large vectors
  def naiveSumOfVectorRecursive(vec: Vector[Int], pos: Int = 0): Long = {
    if(pos == vec.size) 0
    else vec(pos) + naiveSumOfVectorRecursive(vec, pos + 1)
  }

  //using tail recursion
  def sumOfVectorTailRecursion(vec: Vector[Int], pos: Int = 0): Long = {
    @tailrec
    def nextStep(v: Seq[Int], p: Int, acc: Long): Long = {
      if(p == v.size) acc
      else nextStep(v, p + 1, acc + v(p))
    }
    nextStep(vec, pos, 0)
  }

  // using awful for-loop
  def sumOfVectorForLoop(vec: Seq[Int]): Long = {
    var acc = 0
    for (elem <- vec) {
      acc += elem
    }
    acc
  }

  val numsSmallVec: Vector[Int] = (0 to 1_000).toVector
  val numsLargeVec: Vector[Int] = (0 to 1_000_000).toVector

  // avg exec time ~0.1 ms
  val naiveSum = measureExecTime(naiveSumOfVectorRecursive(numsSmallVec), "naive recursive")
  // avg exec time ~0.14 ms
  val tailRecSum = measureExecTime(sumOfVectorTailRecursion(numsSmallVec), "w/ tail recursion")
  // avg exec time ~9.8 ms
  val tailRecSumLarge = measureExecTime(sumOfVectorTailRecursion(numsLargeVec), "large w/ tail recursion")
  // avg exec time ~19.3 ms
  val defaultSum = measureExecTime(numsLargeVec.sum, "large default sum")
  // avg exec time ~4.7 ms
  val sumForLoop = measureExecTime(sumOfVectorForLoop(numsLargeVec), "large for loop")

  // avg exec time ~5.3 ms
  var total = 0
  val sumForEach = measureExecTime(numsLargeVec.foreach(total += _), "large for-each loop")
}
