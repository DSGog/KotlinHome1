fun main() {
    val phoneBook = mutableMapOf<String, MutableMap<String, String>>()

    while (true) {
        print("> ")
        val input = readLine()?.trim() ?: continue
        val parts = input.split(" ")

        when (parts[0].lowercase()) {
            "exit" -> {
                println("Программа завершена.")
                break
            }
            "help" -> {
                println("Доступные команды:")
                println("1. add <Имя> phone <Номер телефона> - Добавить телефон для имени")
                println("2. add <Имя> email <Адрес электронной почты> - Добавить email для имени")
                println("3. exit - Выйти из программы")
                println("4. help - Показать доступные команды")
            }
            "add" -> {
                if (parts.size >= 4) {
                    val name = parts[1]
                    val type = parts[2].lowercase()
                    val value = parts.subList(3, parts.size).joinToString(" ")

                    when (type) {
                        "phone" -> {
                            if (isValidPhoneNumber(value)) {
                                phoneBook.getOrPut(name) { mutableMapOf() }["phone"] = value
                                println("Телефон для $name добавлен: $value")
                            } else {
                                println("Ошибка: Неверный формат номера телефона.")
                            }
                        }
                        "email" -> {
                            if (isValidEmail(value)) {
                                phoneBook.getOrPut(name) { mutableMapOf() }["email"] = value
                                println("Email для $name добавлен: $value")
                            } else {
                                println("Ошибка: Неверный формат адреса электронной почты.")
                            }
                        }
                        else -> println("Неверный формат команды. Введите 'help' для получения списка команд.")
                    }
                } else {
                    println("Неверный формат команды. Введите 'help' для получения списка команд.")
                }
            }
            else -> println("Неизвестная команда. Введите 'help' для получения списка команд.")
        }
    }
}

fun isValidPhoneNumber(phoneNumber: String): Boolean {
    return phoneNumber.matches(Regex("""^\+\d+$"""))
}

fun isValidEmail(email: String): Boolean {
    return email.matches(Regex("""^[a-zA-Z]+@[a-zA-Z]+\.[a-zA-Z]+$"""))
}
