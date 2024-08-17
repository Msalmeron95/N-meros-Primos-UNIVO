// Interface for base number operations
interface IBaseNumber {
    val value: Int
    fun printValue()
}

// Class for prime numbers
class PrimeNumber(override val value: Int) : IBaseNumber {
    override fun printValue() {
        println("Prime Number: $value")
    }
}

// Class for odd numbers
class OddNumber(override val value: Int, val divisors: List<Int>) : IBaseNumber {
    override fun printValue() {
        println("Odd Number: $value, Divisors: $divisors")
    }
}

// Class for even numbers
class EvenNumber(override val value: Int, val divisors: List<Int>) : IBaseNumber {
    override fun printValue() {
        println("Even Number: $value, Divisors: $divisors")
    }
}

// Enumeration for number types
enum class NumberType {
    PRIME, ODD, EVEN
}

// Class for processing and classifying numbers
class PrimeNumberProcessor(private val range: IntRange) {

    // Method to validate and classify numbers
    private fun validateNumber(number: Int): NumberType {
        if (isPrime(number)) return NumberType.PRIME
        if (number % 2 == 0) return NumberType.EVEN
        return NumberType.ODD
    }

    // Method to check if a number is prime
    private fun isPrime(number: Int): Boolean {
        if (number < 2) return false
        for (i in 2..number / 2) {
            if (number % i == 0) return false
        }
        return true
    }

    // Method to process the range of numbers and classify them
    fun processNumbers(): EvaluationResult {
        val primes = mutableListOf<PrimeNumber>()
        val odds = mutableListOf<OddNumber>()
        val evens = mutableListOf<EvenNumber>()

        for (number in range) {
            when (validateNumber(number)) {
                NumberType.PRIME -> primes.add(PrimeNumber(number))
                NumberType.ODD -> odds.add(OddNumber(number, getDivisors(number)))
                NumberType.EVEN -> evens.add(EvenNumber(number, getDivisors(number)))
            }
        }
        return EvaluationResult(primes, odds, evens)
    }

    // Method to get divisors of a number
    private fun getDivisors(number: Int): List<Int> {
        val divisors = mutableListOf<Int>()
        for (i in 1..number) {
            if (number % i == 0) divisors.add(i)
        }
        return divisors
    }
}

// Data class to hold the evaluation result
data class EvaluationResult(
    val primes: List<PrimeNumber>,
    val odds: List<OddNumber>,
    val evens: List<EvenNumber>
)

// Main function to execute the program
fun main() {
    val processor = PrimeNumberProcessor(1..100)
    val result = processor.processNumbers()

    result.primes.forEach { it.printValue() }
    result.odds.forEach { it.printValue() }
    result.evens.forEach { it.printValue() }
}