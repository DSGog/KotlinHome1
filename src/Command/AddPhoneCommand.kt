data class AddPhoneCommand(val name: String, val phone: String) : Command {
    override fun isValid(): Boolean {
        return phone.matches(Regex("""^\+[\d\s\-()]+$"""))
    }
}
