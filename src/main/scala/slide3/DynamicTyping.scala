package slide3

// who wants to write Python code?
// note that the caller has no idea about the return type

inline def half(x: Any): Any =
  inline x match
    case x: Int => x / 2
    case x: String => x.substring(0, x.length / 2)

@main def halfMain() = 
    half(6)
    // expands to:
    // val x = 6
    // x / 2

    println(half(6))

    half("hello world")
    // expands to:
    // val x = "hello world"
    // x.substring(0, x.length / 2)

    println(half("hello world"))