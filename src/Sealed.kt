sealed interface Command {
    fun isValid(): Boolean
}

data class AddCommand(val name: String, val type: String, val value: String) : Command {
    override fun isValid(): Boolean {
        return when (type) {
            "phone" -> value.matches(Regex("""^\+\d+$"""))
            "email" -> value.matches(Regex("""^[a-zA-Z]+@[a-zA-Z]+\.[a-zA-Z]+$"""))
            else -> false
        }
    }
}

object HelpCommand : Command {
    override fun isValid() = true
}

object ExitCommand : Command {
    override fun isValid() = true
}

object ShowCommand : Command {
    override fun isValid() = true
}
