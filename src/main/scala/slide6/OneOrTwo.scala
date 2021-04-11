package slide6

import scala.compiletime.{ error, codeOf }

// I want to have compile-time validation for my sealed trait but it doesn't quite work.
// See https://stackoverflow.com/questions/67032401/scala-3-inlining-fails-a-very-simple-example

sealed trait OneOrTwo {
  val code: String
}

object OneOrTwo {

    case object One extends OneOrTwo {
      override val code : "one" = "one" 
    }

    case object Two extends OneOrTwo {
      override val code : "two" = "two"
    }

    def from(s: String): Option[OneOrTwo] = 
        s match {
            case One.code => Some(One)
            case Two.code => Some(Two)
            case _ => None
        }

    // better but not ideal:
    inline def inlinedFrom3(s: String): OneOrTwo = {
        inline s match {
            case One.code => One
            case Two.code => Two
            case _ => error("can't make a OneOrTwo out of " + codeOf(s))
        }
    }

    // would be ideal but doesn't work:
    inline def inlinedFrom2(s: String): OneOrTwo = {
        from(s).getOrElse(error("can't make a OneOrTwo out of " + codeOf(s)))
    }
   
    val test3 = OneOrTwo.inlinedFrom3("one") 
    // val test4 = OneOrTwo.inlinedFrom3("three") // doesn't compile as expected

    // and I can't make this work
    // val test2 = OneOrTwo.inlinedFrom2("one") // doesn't compile -> can't make a OneOrTwo out of "one"

}