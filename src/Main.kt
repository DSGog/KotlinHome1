fun main() {
    var lastPerson: Person? = null

    println("Введите 'help' для получения списка доступных команд.")

    while (true) {
        val command = readCommand()
        println(command)

        if (!command.isValid()) {
            println("Неверный формат команды. Введите 'help' для получения списка команд.")
            continue
        }

        when (command) {
            is AddCommand -> {
                if (command.type == "phone") {
                    lastPerson = Person(command.name, command.value, null)
                    println("Телефон для ${command.name} добавлен: ${command.value}")
                } else if (command.type == "email") {
                    lastPerson = Person(command.name, lastPerson?.phone, command.value)
                    println("Email для ${command.name} добавлен: ${command.value}")
                }
            }

            is HelpCommand -> {
                println("Доступные команды:")
                println("1. add <Имя> <phone или email> <номер или значение>. Например: вводимое значение будет выглядеть следующим образом > add Andrey phone +1234567890")
                println("2. exit - Выйти из программы")
                println("3. show - Показать последнюю запись")
                println("4. help - Показать доступные команды")
            }

            is ExitCommand -> {
                println("Программа завершена.")
                break
            }

            is ShowCommand -> {
                if (lastPerson == null) {
                    println("Not initialized")
                } else {
                    println("Последняя запись: $lastPerson")
                }
            }
        }
    }
}
