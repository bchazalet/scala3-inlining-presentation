package slide2

// Like inline if, inline matches guarantee that the pattern matching 
// can be statically reduced at compile time and only one branch is kept.

// I also wanted to demonstrate that we could use types / Enums in the conditions
// as they can be known at compile time

enum FirstOrLast:
   case First
   case Last

inline def firstOrLast(choice: FirstOrLast, s: String): Option[Char] =
   inline choice match {
      case FirstOrLast.First => s.headOption
      case FirstOrLast.Last => s.lastOption
   }

@main def firstOrLastMain() = {
   val c = firstOrLast(FirstOrLast.First, "message")
   // this will be rewritten more or less as:
   // val c = "message".headOption
   println(c)
}