typealias IIP = Pair<Int, Int>
typealias int = Int
inline fun iar(size: Int, init: (Int) -> Int = { 0 }) = IntArray(size) { init(it) }

/**
 * generated by kotlincputil@tauros
 */
fun main(args: Array<String>) {
    // https://codeforces.com/problemset/problem/1578/H
    // 用栈模拟一下这个过程
    val str = readln()

    val exp = str.replace("()", "f").replace("->", ">")
    val n = exp.length
    val stack = iar(n)
    var top = -1
    fun order(i: int): IIP {
        val bottom = top
        fun calc(): int {
            var res = stack[top--]
            while (top > bottom) {
                res = maxOf(stack[top--] + 1, res)
            }
            return res
        }
        var j = i
        while (j < n) {
            if (exp[j] == 'f') stack[++top] = 0
            else if (exp[j] == '(') {
                val (s, nj) = order(j + 1)
                stack[++top] = s
                j = nj
            } else if (exp[j] == ')') {
                return calc() to j
            }
            j += 1
        }
        return calc() to j
    }
    val (ans) = order(0)
    println(ans)
}