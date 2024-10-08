import java.io.File

fun main() {
    val phoneBook = PhoneBook()
    println("Введите 'help' для получения списка доступных команд.")

    while (true) {
        val command = getCommand()
        if (!command.isValid()) {
            println("Неверный формат команды. Введите 'help' для получения списка команд.")
            continue
        }

        when (command) {
            is AddPhoneCommand -> {
                phoneBook.addPhone(command)
                println("Телефон для ${command.name} добавлен: ${command.phone}")
            }
            is AddEmailCommand -> {
                phoneBook.addEmail(command)
                println("Email для ${command.name} добавлен: ${command.email}")
            }
            is ShowCommand -> {
                phoneBook.show(command.name)
            }
            is ExportCommand -> {
                exportPhoneBook(phoneBook, command.path)
                println("Данные экспортированы в файл ${command.path}")
            }
            is FindCommand -> {
                phoneBook.find(command.value)
            }
            is HelpCommand -> {
                println("Доступные команды:")
                println("1. addphone <Имя> <Номер телефона> Например: addphone Andrew +789654")
                println("2. addemail <Имя> <Адрес электронной почты> Например: addemail Andrew gb@gb.ru")
                println("3. show <Имя>")
                println("4. find <Телефон/Email>")
                println("5. exit")
                println("6. help")
                println("7. export Например: export AndrewGB")
            }
            is ExitCommand -> {
                println("Программа завершена.")
                return
            }
            else -> {
                println("Неизвестная команда.")
            }
        }
    }
}

fun exportPhoneBook(phoneBook: PhoneBook, filePath: String) {
    val json = json {
        phoneBook.getPhoneBook().values.forEach { person ->
            addField("name", person.name)
            addArrayField("phones", person.phones)
            addArrayField("emails", person.emails)
        }
    }

    try {
        val file = File(filePath)
        file.writeText(json)
        println("Данные успешно экспортированы в файл $filePath")
    } catch (e: Exception) {
        println("Ошибка при записи в файл: ${e.message}")
    }
}