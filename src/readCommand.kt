fun readCommand(): Command {
    print("> ")
    val input = readLine()?.trim() ?: return HelpCommand
    val parts = input.split(" ")

    return when (parts[0].lowercase()) {
        "add" -> if (parts.size >= 4) {
            AddCommand(parts[1], parts[2], parts.subList(3, parts.size).joinToString(" "))
        } else {
            HelpCommand
        }
        "exit" -> ExitCommand
        "help" -> HelpCommand
        "show" -> ShowCommand
        else -> HelpCommand
    }
}