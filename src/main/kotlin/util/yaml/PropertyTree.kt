package util.yaml

data class PropertyTree(
    val name: String,
    var value: String? = null
) {

    private val childMap = HashMap<String, PropertyTree>()

    fun add(propertyIterator: ListIterator<String>, propertyValue: String) {
        val currentProperty = propertyIterator.next().trim()

        if (!this.childMap.containsKey(currentProperty)) {
            this.childMap[currentProperty] = PropertyTree(currentProperty)
        }

        if (!propertyIterator.hasNext()) {
            this.childMap[currentProperty]!!.value = propertyValue
            return
        }

        this.childMap[currentProperty]!!.add(propertyIterator, propertyValue)
    }

    fun convertToYamlFormat() = convertToYamlFormat(1)

    private fun convertToYamlFormat(depth: Int): String {
        val stringBuilder = StringBuilder("$name: ")

        if (!this.hasChild()) {
            stringBuilder
                .append(this.value)
                .append("\n")
        } else {
            stringBuilder.append("\n")

            this.childMap.values.forEach { property ->
                repeat(depth) {
                    stringBuilder.append("   ")
                }

                stringBuilder.append(property.convertToYamlFormat(depth + 1))
            }
        }

        return stringBuilder.toString()
    }

    private fun hasChild() = this.childMap.isNotEmpty()

}
