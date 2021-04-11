package slide2

inline def logged[T](level: Int, message: => String)(inline op: T): T =
    println(s"[$level]Computing $message")
    val res = op
    println(s"[$level]Result of $message: $res")
    res

// When an inline method like logged is called, 
// its body will be expanded at the call-site at compile time!

// the following:
@main def main() =
    // prep work
    val logLevel = 1
    def computeSomething() = 3*6
    def getMessage() = "three times six"

    // the following:
    logged(logLevel, getMessage()) {
        computeSomething()
    }

// will actually be expanded to:
@main def expandedMain() =
     // prep work
    val logLevel = 1
    def computeSomething() = 3*6
    def getMessage() = "three times six"

    // will actually be expanded to:
    val level   = logLevel
    def message = getMessage()

    println(s"[$level]Computing $message")
    val res = computeSomething()
    println(s"[$level]Result of $message: $res")
    res