import java.lang.Exception
import java.lang.IllegalArgumentException
import kotlin.math.pow
import kotlin.random.Random
import kotlin.random.nextInt

//1
val systolic = Random.nextInt(1..200)
val diastolic = Random.nextInt(1..150)
println("Systolic: $systolic\nDiastolic: $diastolic")
 println (when { // no argument

    systolic < 120 && diastolic < 80 -> "Blood pressure is normal."
    systolic in 121..129 && diastolic < 80 -> "Blood pressure is elevated."
    systolic in 131..139 || diastolic in 81..89 -> "Hypertension stage 1."
    systolic >= 140 || diastolic >= 90 -> "Hypertension stage 2."
    systolic > 180 || diastolic > 120 -> "Hypertensive crisis. Go to your doctor please."
    else -> "Please try again."
})


//2
abstract class Card(accName: String, var balance: Double) {
    init {
        if (!accName.chars().allMatch(Character::isLetter)) {
            println("Invalid Name")
        }
        if (balance < 0) {
            println("New accounts can't have negative balance")
        }
    }

    abstract fun pay(amount: Int): String
    open fun deposit(amount: Int) {
        balance += amount
    }

    abstract fun withdraw(amount: Int): String
}

class DebitCard(name: String, balance: Double) : Card(name, balance) {

    override fun pay(amount: Int): String {
        if (checkBalance())
            return if (balance >= amount) {
                balance -= amount
                "Succes"
            } else "Failure"
        else return "Debit cards work if balance > 0"
    }

    override fun deposit(amount: Int) {
        if (checkBalance())
            balance += amount
    }

    override fun withdraw(amount: Int): String {
        if (checkBalance())
            return if (balance >= amount) {
                balance -= amount
                println("New balance: $balance")
                "Succes"
            } else "Failure"
        else return "Debit cards work if balance > 0"
    }

    fun checkBalance(): Boolean {
        return balance > 0
    }
}

class CreditCard(name: String, balance: Double, private var creditLimit: Int) :
    Card(name, balance) {
    val bonus = 2

    override fun pay(amount: Int): String {
        return if (balance + creditLimit >= amount) {
            balance -= amount + amount * (2 / 100.0)
            "Succes"
        } else "Failure"
    }

    override fun deposit(amount: Int) {
        balance += amount
    }

    override fun withdraw(amount: Int): String {
        return if (balance + creditLimit >= amount) {
            balance -= amount
            println("New balance: $balance")
            "Succes"
        } else "Failure"
    }
}


//3
fun factorial(num: Int) = if (num > 0) (2..num).fold(1, Int::times) else throw IllegalArgumentException()


//4
data class Rectangle(val x: Int, val y: Int, val w: Int, val h: Int)

class Paint {
    var color: Long = 1
    var strokedWidth: Int = 5
    fun drawRectangle(rect: Rectangle) {
        println("Drawing $rect color : $color stroke: $strokedWidth")
    }
}

fun render(paint: Paint?, rectangles: List<Rectangle?>) {
    paint?.apply {
        color = 2
        rectangles.forEach{ it?.let { drawRectangle(it) }}
    }
}

val rect = listOf(Rectangle(1,2,3,4), Rectangle(1,2,3,6))
val paint = Paint()
render(paint, rect)

//5

data class HeartRateEntry(val date: Long, val value: Int)

fun populateData(vararg dataPair: Pair<Long, Int>): List<HeartRateEntry> =
    dataPair.map { HeartRateEntry(it.first, it.second) }

val data = populateData(
    1612310400L to 76,
    1612310400L to 89,
    1612310400L to 44,
    1612224000L to 47,
    1612224000L to 115,
    1612224000L to 76,
    1612224000L to 87,
    1612137600L to 90,
    1612137600L to 167
)

//1
data.maxBy { it.value }
//2
data.map { it.value }.average();
//3
data.filter { it.value > 100 }.forEach { println(it.value) }
//data.forEach{if(it.value > 100) println(it.value)}
//4
val dataMap = data.groupBy(HeartRateEntry::date, HeartRateEntry::value)

println(dataMap.keys + "\n"+ dataMap.values)
//5
dataMap.forEach{println(it.value.max())}