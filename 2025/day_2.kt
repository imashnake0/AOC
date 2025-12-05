import kotlin.math.pow

fun solve(ranges: String): Long {
    val rangeStringList = ranges.split(",")

    val rangeList = mutableListOf<LongRange>()

    rangeStringList.forEach {
        val intList = it.split("-")
        rangeList.add(intList[0].toLong()..intList[1].toLong())
    }

    var sum = 0L

    rangeList.forEach {
        for (long in it) {
            if (isSymmetric(long)) {
                println(long)
                sum += long
            }
        }
    }

    return sum
}

private fun isSymmetric(number: Long): Boolean {
    val numDigits = digits(number)

    (1..(numDigits - 1)).forEach { segmentLength ->
        if (numDigits % segmentLength != 0) {
            return@forEach
        } else {
            val list = mutableListOf<Long>()
            val numSegments = numDigits / segmentLength

            var tempNumber = number
            repeat(numSegments) {
                val power = 10f.pow(segmentLength).toInt()
                val req = tempNumber % power

                list.add(req)

                if (req != list.firstOrNull()) {
                    return@forEach
                }

                tempNumber = (tempNumber - req) / power
            }

            return list.toSet().size == 1
        }
    }

    return false

    // if (numDigits % 2 != 0) {
    //     return false
    // } else {
    //     for () {
    //         val somePower = 10f.pow((numDigits / 2)).toInt()
    //         val firstHalf = number.floorDiv(somePower)
    //         val secondHalf = number % somePower
    //
    //         return firstHalf == secondHalf
    //     }
    // }
}

private fun digits(number: Long): Int {
    // this doesn't work well for longs
    // if (number == 0L) return 1
    // var divideBy = 1
    // var digits = 0
    //
    // while (number / divideBy > 0) {
    //     digits++
    //     divideBy *= 10
    // }
    //
    // return digits
    return number.toString().length
}

private val sample = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124"

private val input = "16100064-16192119,2117697596-2117933551,1-21,9999936269-10000072423,1770-2452,389429-427594,46633-66991,877764826-877930156,880869-991984,18943-26512,7216-9427,825-1162,581490-647864,2736-3909,39327886-39455605,430759-454012,1178-1741,219779-244138,77641-97923,1975994465-1976192503,3486612-3602532,277-378,418-690,74704280-74781349,3915-5717,665312-740273,69386294-69487574,2176846-2268755,26-45,372340114-372408052,7996502103-7996658803,7762107-7787125,48-64,4432420-4462711,130854-178173,87-115,244511-360206,69-86"

solve(input)
