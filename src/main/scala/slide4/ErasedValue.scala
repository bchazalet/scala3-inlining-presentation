package slide4

import scala.compiletime.{ error, erasedValue }

// this is useful one you don't a value to work with, but only a type

transparent inline def defaultValue[T] =
   inline erasedValue[T] match
      case _: Byte    => Some(0: Byte)
      case _: Char    => Some(0: Char)
      case _: Short   => Some(0: Short)
      case _: Int     => Some(0)
      case _: Long    => Some(0L)
      case _: Float   => Some(0.0f)
      case _: Double  => Some(0.0d)
      case _: Boolean => Some(false)
      case _: Unit    => Some(())
      case _          => None


@main def defaultValueMain() = 
    val dInt: Some[Int] = defaultValue[Int]
    println(dInt)
    val dDouble: Some[Double] = defaultValue[Double]
    println(dDouble)
    val dBoolean: Some[Boolean] = defaultValue[Boolean]
    println(dBoolean)
    val dAny: None.type = defaultValue[Any]
    println(dAny)


// or we could choose to error instead of defaulting to None
// let's see another example

inline def sizeOf[T]: Int =
    inline erasedValue[T] match {
        case _: Byte => 1
        case _: Short => 2
        case _: Int => 4
        case _: Long => 8
        case _: Float => 4
        case _: Double => 8
        case _ => error("I don't know the size of your type")
    }

@main def sizeOfMain() = 
    val size1 = sizeOf[Int]
    // val size2 = sizeOf[String] // doesn't compile but shows my error message