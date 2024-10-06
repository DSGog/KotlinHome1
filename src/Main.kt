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
            is FindCommand -> {
                phoneBook.find(command.value)
            }
            is HelpCommand -> {
                println("Доступные команды:")
                println("1. addphone <Имя> <Номер телефона> - Например: addphone Andrew +123")
                println("2. addemail <Имя> <Адрес электронной почты> - Например: addemail Andrew 123@gmail.com")
                println("3. show <Имя> - Показать информацию о человеке")
                println("4. find <Телефон/Email> - Найти людей по номеру телефона или email")
                println("5. exit - Выйти из программы")
                println("6. help - Показать доступные команды")
            }
            is ExitCommand -> {
                println("Программа завершена.")
                return
            }
            else -> {
                println("Неизвестная команда. Введите 'help' для получения списка доступных команд.")
            }
        }
    }
}
