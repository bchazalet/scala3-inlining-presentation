package slide2

// Inline methods can be recursive. For instance, when called with a constant exponent n,
// the following method for power will be implemented by straight inline code
// without any *loop* or *recursion*.

inline def power(x: Double, n: Int): Double =
   if n == 0 then 1.0
   else if n == 1 then x
   else
      val y = power(x, n / 2)
      if n % 2 == 0 then y * y else y * y * x


@main def powerMain() = 

    val expr: Double = ???

    power(expr, 10)

    // translates to
    //
    //    val x = expr
    //    val y1 = x * x   // ^2
    //    val y2 = y1 * y1 // ^4
    //    val y3 = y2 * x  // ^5
    //    y3 * y3          // ^10
