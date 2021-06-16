package software.sigma.concepts

import java.io.StringWriter

object MonadAndFunctorDemo extends App {

  // functor - any data type that defines how map applies to it
  val doubledList = List(1,2,3).map(_ * 2) // <- applying map function with supplied lambda

  val someOption: Option[Int] = Some(232)
  val mappedOption: Option[Int] = someOption.map(_ * 2)

  trait Functor[C[_]] {
    def map[A, B](container: C[A])(f: A => B): C[B]
  }

  implicit val listFunctor: Functor[List] = new Functor[List] {
    override def map[A, B](container: List[A])(f: A => B): List[B] = container.map(f)
  }

  implicit val optionFunctor: Functor[Option] = new Functor[Option] {
    override def map[A, B](container: Option[A])(f: A => B): Option[B] = container.map(f)
  }

  // functors allow us to generalize and stabilize our API
  // for a new container we can define new functor
  // that will be injected with implicit syntax
  def doubleContainer[C[_]](container: C[Int])(implicit functor: Functor[C]): C[Int] = {
    functor.map(container)(_ * 2)
  }

  println(doubleContainer(doubledList))
  println(doubleContainer(mappedOption))

  ///////////////// Monads
  // Monads allow us to cast away boilerplate code
  // Monad has two basic procedures w/ :
  //            1) To wrap and extract a value from the Monad type (monadic value)
  //            2) Function that is capable of returning monadic values (monadic function)

  // a naive example of such StringWrapper monad is shown below

  //in fp constructor is called unit
  class StringWrapper(wrappedString: String) {
    def get: String = wrappedString

    // bind or flatMap method
    def transform(transform: String => StringWrapper): StringWrapper = {
      transform(wrappedString)
    }
  }

  def externalApiStringWrapperGenerator(s: String): StringWrapper = new StringWrapper(s)

  val stringWrapper = externalApiStringWrapperGenerator("some string")
  // Extract
  val stringValue = stringWrapper.get
  // Transform
  val transformedString= "(" + stringValue + ")"
  // Wrap back
  val wrappedTransformed = new StringWrapper(transformedString)

  // instead of writing those 3 lines above
  val wrappedTransformedOneLiner = stringWrapper.transform(s => new StringWrapper("(" + s + ")"))
}
