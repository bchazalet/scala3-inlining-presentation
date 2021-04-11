package slide2

// If the condition of an if is a known constant (true or false),
// possibly after inlining and constant folding, 
// then the conditional is partially evaluated and only one branch will be kept.

inline def assert1(cond: Boolean, msg: String) =
    if !cond then
        throw new Exception(msg)

@main def mainAssert1() =
    // prep work
    // x is not a known constant (would be the same with a non-inlined val)
    var x = true

    assert1(x, "exploding?")
    // this gets inlined as:
    val cond = x
    if !cond then
        throw new Exception("exploding?")


    assert1(false, "exploding!")
    // here `false` is a known constant so it gets re-written to:
    throw new Exception("exploding!")


    assert1(true, "not exploding!")
    // here `true` is a known constant so it gets re-written to:
    ()
    

// we can mark the if as `inline` to enforce the condition to be a known constant
// i.e. to force a branch to be choosen

inline def assert2(cond: Boolean, msg: String) =
    inline if !cond then
        throw new Exception(msg)

@main def mainAssert2() =
    // prep work
    // x is not a known constant
    var x = true

    // this doesn't not compile anymore:
    // assert2(x, "exploding?")

    // but these below still work

    assert2(false, "exploding!")
    // here `false` is a known constant so it gets re-written to:
    throw new Exception("exploding!")


    assert2(true, "not exploding!")
    // here `true` is a known constant so it gets re-written to:
    ()