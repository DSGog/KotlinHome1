import java.io.File

class PhoneBook {
    private val phoneBook = mutableMapOf<String, Person>()

    fun addPhone(command: AddPhoneCommand) {
        val person = phoneBook.getOrPut(command.name) { Person(command.name) }
        person.phones.add(command.phone)
    }

    fun addEmail(command: AddEmailCommand) {
        val person = phoneBook.getOrPut(command.name) { Person(command.name) }
        person.emails.add(command.email)
    }

    fun show(name: String) {
        val person = phoneBook[name]
        if (person != null) {
            println("Имя: ${person.name}")
            if (person.phones.isNotEmpty()) {
                println("Телефоны: ${person.phones.joinToString(", ")}")
            } else {
                println("Телефонов нет.")
            }
            if (person.emails.isNotEmpty()) {
                println("Email адреса: ${person.emails.joinToString(", ")}")
            } else {
                println("Email адресов нет.")
            }
        } else {
            println("Запись для $name не найдена.")
        }
    }

    fun find(value: String) {
        val foundPeople = phoneBook.values.filter {
            it.phones.contains(value) || it.emails.contains(value)
        }
        if (foundPeople.isNotEmpty()) {
            println("Найдены следующие записи:")
            for (person in foundPeople) {
                println("${person.name}: ${person.phones.joinToString(", ")}, ${person.emails.joinToString(", ")}")
            }
        } else {
            println("Записи с таким номером телефона или email не найдены.")
        }
    }
    fun getPhoneBook(): Map<String, Person> {
        return phoneBook
    }
    fun exportPhoneBook(phoneBook: PhoneBook, filePath: String) {
        val json = phoneBook.getPhoneBook().values.joinToString(prefix = "[", postfix = "]") { person ->
            json {
                addField("name", person.name)
                addArrayField("phones", person.phones)
                addArrayField("emails", person.emails)
            }
        }
        try {
            val file = File(filePath)
            file.writeText(json)
            println("Данные экспортированы в файл $filePath")
        } catch (e: Exception) {
            println("Ошибка при записи в файл: ${e.message}")
        }
    }
}



