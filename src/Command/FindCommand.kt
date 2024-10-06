data class FindCommand(val value: String) : Command {
    override fun isValid(): Boolean {
        return value.isNotBlank()
    }
}