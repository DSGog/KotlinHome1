fun getCommand(): Command {
    print("> ")
    val input = readLine()?.trim() ?: return HelpCommand
    val parts = input.split(" ")

    return when (parts[0].lowercase()) {
        "addphone" -> if (parts.size == 3) {
            AddPhoneCommand(parts[1], parts[2])
        } else {
            HelpCommand
        }
        "addemail" -> if (parts.size == 3) {
            AddEmailCommand(parts[1], parts[2])
        } else {
            HelpCommand
        }
        "show" -> if (parts.size == 2) {
            ShowCommand(parts[1])
        } else {
            HelpCommand
        }
        "find" -> if (parts.size == 2) {
            FindCommand(parts[1])
        } else {
            HelpCommand
        }
        "export" -> if (parts.size == 2) {
            ExportCommand(parts[1])
        } else {
            HelpCommand
        }
        "exit" -> ExitCommand
        "help" -> HelpCommand
        else -> HelpCommand
    }
}