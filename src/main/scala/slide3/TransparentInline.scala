package slide3

import scala.compiletime.error

// Transparent inlines are a simple, yet powerful, extension to inline methods 
// and unlock many metaprogramming usecases. Calls to transparents allow for
// an inline piece of code to refine the return type based on the precise 
// type of the inlined expression.

transparent inline def default(name: String): Any =
  inline if name == "Int" then 0
  else inline if name == "String" then ""
  else error("I didn't not expect this value")

@main def transparentMain() = 
    val n: Int = default("Int")
    val s = default("String") 
    s.indexOf(0) // the compiler knows it's a String!

    // bonus: this doesn't compile and show my error message
    // default("MyOwnType")