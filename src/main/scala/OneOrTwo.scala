import scala.compiletime.{ error, codeOf }

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

    inline def inlinedFrom1(s: String): OneOrTwo = {
        inline s match {
            case "one" => One
            case "two" => Two
            case _ => error("can't make a OneOrTwo out of " + codeOf(s))
        } 
    }

    val test1 = OneOrTwo.inlinedFrom1("one") // compiles 
    // val test12 = OneOrTwo.inlinedFrom1("three") // doesn't compile as expected -> can't make a OneOrTwo out of "three"

    inline def inlinedFrom2(s: String): OneOrTwo = {
        from(s).getOrElse(error("can't make a OneOrTwo out of " + codeOf(s)))
    }

    inline def inlinedFrom3(s: String): OneOrTwo = {
        inline s match {
            case One.code => One
            case Two.code => Two
            case _ => error("can't make a OneOrTwo out of " + codeOf(s))
        }
    }

    
    // val test2 = OneOrTwo.inlinedFrom2("one") // doesn't compile -> can't make a OneOrTwo out of "one"
    val test3 = OneOrTwo.inlinedFrom3("one") // doesn't compile -> can't make a OneOrTwo out of "one"

}