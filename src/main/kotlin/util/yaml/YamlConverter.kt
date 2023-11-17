package util.yaml

class YamlConverter {

    fun convertToYaml(propertiesString: String): String {
        val propertiesMap = HashMap<String, PropertyTree>()

        propertiesString.split("\n").forEach {
            if (it.isBlank() || it.startsWith("#")) {
                return@forEach
            }

            val property = it.split("=")

            val propertyDetail = property[0].split(".")

            val firstDetail = propertyDetail[0].trim()

            if (!propertiesMap.containsKey(firstDetail)) {
                propertiesMap[firstDetail] = PropertyTree(firstDetail)
            }

            propertiesMap[firstDetail]!!.add(
                propertyDetail.listIterator(1),
                extractPropertyValue(property.subList(1, property.size))
            )
        }

        val stringBuilder = StringBuilder()

        propertiesMap.values.forEach {
            stringBuilder
                .append(it.convertToYamlFormat())
                .append("\n")
        }

        return stringBuilder.toString()
    }

    private fun extractPropertyValue(propertyValueList: List<String>): String {
        val stringBuilder = StringBuilder()

        for (i in propertyValueList.indices) {
            stringBuilder.append(propertyValueList[i].trim())

            if (i < propertyValueList.lastIndex) {
                stringBuilder.append("=")
            }
        }

        return stringBuilder.toString()
    }

}
