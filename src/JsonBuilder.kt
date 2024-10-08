class JsonBuilder {
    private val elements = mutableListOf<String>()

    fun addField(key: String, value: String) {
        elements.add(""""$key": "$value"""")
    }

    fun addArrayField(key: String, values: List<String>) {
        val arrayValues = values.joinToString(",") { """"$it"""" }
        elements.add(""""$key": [$arrayValues]""")
    }

    fun build(): String {
        return elements.joinToString(",", "{", "}")
    }
}

fun json(init: JsonBuilder.() -> Unit): String {
    val builder = JsonBuilder()
    builder.init()
    return builder.build()
}