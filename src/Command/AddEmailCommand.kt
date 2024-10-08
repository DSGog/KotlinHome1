data class AddEmailCommand(val name: String, val email: String) : Command
{
    override fun isValid(): Boolean {
        return email.matches(Regex("""^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$"""))
    }
}
