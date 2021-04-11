package slide1

// from https://docs.scala-lang.org/scala3/guides/macros/inline.html
// Inlining is a common compile-time metaprogramming technique, typically used to achieve performance optimizations.
// As we will see, in Scala 3, the concept of inlining provides us with an entrypoint to programming with macros.

// 1. It introduces inline as a soft keyword.
// 2. It guarantees that inlining actually happens instead of being best-effort.
// 3. It introduces operations that are guaranteed to evaluate at compile-time.

inline val pi = 3.141592653589793

// here the reference pi is inlined. 
// Then an optimization called “constant folding” is applied by the compiler, which computes the resulting value pi2 at compile-time.
inline val pi2 = pi + pi // val pi2 = 6.283185307179586


// Currently, only constant expressions may appear on the right-hand side of an inline value definition. 
// Therefore, the following code is invalid, though the compiler knows that the right-hand side is a compile-time constant value:
val anotherPi = 3.141592653589793
// inline val anotherPi2 = anotherPi + anotherPi